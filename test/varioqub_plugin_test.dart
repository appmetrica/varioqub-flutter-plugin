import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/mockito.dart';
import 'package:varioqub_plugin/src/varioqub.dart';
import 'package:varioqub_plugin/src/varioqub_api_pigeon.dart';
import 'package:varioqub_plugin/src/varioqub_settings.dart';

class MockHandler extends Mock {
  Future<ByteData?>? call(ByteData? message);
}

const String _channelPrefix = 'dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.';

void main() {
  TestWidgetsFlutterBinding.ensureInitialized();

  const MessageCodec<Object?> pigeonCodec = VarioqubPigeon.codec;

  Future<ByteData?> voidReply(ByteData? _) async =>
      pigeonCodec.encodeMessage(<Object?>[]);

  void setHandler(
    WidgetTester tester,
    String method,
    Future<ByteData?>? Function(ByteData? message)? handler,
  ) {
    tester.binding.defaultBinaryMessenger.setMockMessageHandler(
      '$_channelPrefix$method',
      handler,
    );
  }

  group('VarioqubSettings', () {
    test('toPigeon with only clientId', () {
      const VarioqubSettings settings = VarioqubSettings('client-1');
      final VarioqubSettingsPigeon pigeon = settings.toPigeon();

      expect(pigeon.clientId, 'client-1');
      expect(pigeon.url, isNull);
      expect(pigeon.fetchThrottleIntervalSeconds, isNull);
      expect(pigeon.clientFeatures, isNull);
      expect(pigeon.logs, isNull);
      expect(pigeon.activateEvent, isNull);
    });

    test('toPigeon with all fields', () {
      const VarioqubSettings settings = VarioqubSettings(
        'client-full',
        url: 'https://example.test',
        fetchThrottleIntervalSeconds: 42,
        clientFeatures: <String, String>{'f1': 'v1'},
        logs: true,
        activateEvent: false,
      );
      final VarioqubSettingsPigeon pigeon = settings.toPigeon();

      expect(pigeon.clientId, 'client-full');
      expect(pigeon.url, 'https://example.test');
      expect(pigeon.fetchThrottleIntervalSeconds, 42);
      expect(pigeon.clientFeatures, <String?, String?>{'f1': 'v1'});
      expect(pigeon.logs, isTrue);
      expect(pigeon.activateEvent, isFalse);
    });

    test('toPigeon with empty clientFeatures', () {
      const VarioqubSettings settings = VarioqubSettings(
        'client-empty',
        clientFeatures: <String, String>{},
      );
      final VarioqubSettingsPigeon pigeon = settings.toPigeon();

      expect(pigeon.clientId, 'client-empty');
      expect(pigeon.clientFeatures, isEmpty);
    });
  });

  group('Varioqub', () {
    testWidgets('getId', (WidgetTester tester) async {
      setHandler(tester, 'getId', (ByteData? _) async {
        return pigeonCodec.encodeMessage(<Object?>['42']);
      });

      expect(await Varioqub.getId(), '42');
    });

    testWidgets('initVarioqubWithAppMetricaAdapter sends settings', (WidgetTester tester) async {
      final MockHandler mock = MockHandler();
      when(mock.call(any)).thenAnswer((Invocation invocation) async {
        final ByteData? message = invocation.positionalArguments[0] as ByteData?;
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, hasLength(1));
        final VarioqubSettingsPigeon settings = args[0]! as VarioqubSettingsPigeon;
        expect(settings.clientId, 'init-client');
        expect(settings.url, 'https://init.test');
        expect(settings.fetchThrottleIntervalSeconds, 10);
        expect(settings.clientFeatures, <String?, String?>{'k': 'v'});
        expect(settings.logs, isTrue);
        expect(settings.activateEvent, isFalse);
        return pigeonCodec.encodeMessage(<Object?>[]);
      });
      setHandler(tester, 'initVarioqubWithAppMetricaAdapter', mock.call);

      await Varioqub.initVarioqubWithAppMetricaAdapter(
        const VarioqubSettings(
          'init-client',
          url: 'https://init.test',
          fetchThrottleIntervalSeconds: 10,
          clientFeatures: <String, String>{'k': 'v'},
          logs: true,
          activateEvent: false,
        ),
      );

      verify(mock.call(any)).called(1);
    });

    testWidgets('fetchConfig happy path', (WidgetTester tester) async {
      setHandler(tester, 'fetchConfig', (ByteData? _) async {
        return pigeonCodec.encodeMessage(<Object?>[
          FetchStatus(status: 1, error: null),
        ]);
      });

      final FetchStatus status = await Varioqub.fetchConfig();
      expect(status.status, 1);
      expect(status.error, isNull);
    });

    testWidgets('fetchConfig with error', (WidgetTester tester) async {
      setHandler(tester, 'fetchConfig', (ByteData? _) async {
        return pigeonCodec.encodeMessage(<Object?>[
          FetchStatus(status: 0, error: 'network'),
        ]);
      });

      final FetchStatus status = await Varioqub.fetchConfig();
      expect(status.status, 0);
      expect(status.error, 'network');
    });

    testWidgets('activateConfig', (WidgetTester tester) async {
      final MockHandler mock = MockHandler();
      when(mock.call(any)).thenAnswer((_) => voidReply(null));
      setHandler(tester, 'activateConfig', mock.call);

      await Varioqub.activateConfig();
      verify(mock.call(any)).called(1);
    });

    testWidgets('getString sends key and default', (WidgetTester tester) async {
      setHandler(tester, 'getString', (ByteData? message) async {
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, <Object?>['flag', 'fallback']);
        return pigeonCodec.encodeMessage(<Object?>['value']);
      });

      expect(await Varioqub.getString('flag', 'fallback'), 'value');
    });

    testWidgets('getBoolean sends key and default', (WidgetTester tester) async {
      setHandler(tester, 'getBoolean', (ByteData? message) async {
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, <Object?>['enabled', false]);
        return pigeonCodec.encodeMessage(<Object?>[true]);
      });

      expect(await Varioqub.getBoolean('enabled', false), isTrue);
    });

    testWidgets('getInt sends key and default', (WidgetTester tester) async {
      setHandler(tester, 'getInt', (ByteData? message) async {
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, <Object?>['count', 0]);
        return pigeonCodec.encodeMessage(<Object?>[7]);
      });

      expect(await Varioqub.getInt('count', 0), 7);
    });

    testWidgets('getDouble sends key and default', (WidgetTester tester) async {
      setHandler(tester, 'getDouble', (ByteData? message) async {
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, <Object?>['ratio', 1.5]);
        return pigeonCodec.encodeMessage(<Object?>[2.25]);
      });

      expect(await Varioqub.getDouble('ratio', 1.5), 2.25);
    });

    testWidgets('putClientFeature sends key and value', (WidgetTester tester) async {
      final MockHandler mock = MockHandler();
      when(mock.call(any)).thenAnswer((Invocation invocation) async {
        final ByteData? message = invocation.positionalArguments[0] as ByteData?;
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, <Object?>['feature', 'on']);
        return pigeonCodec.encodeMessage(<Object?>[]);
      });
      setHandler(tester, 'putClientFeature', mock.call);

      await Varioqub.putClientFeature('feature', 'on');
      verify(mock.call(any)).called(1);
    });

    testWidgets('clearClientFeatures', (WidgetTester tester) async {
      final MockHandler mock = MockHandler();
      when(mock.call(any)).thenAnswer((_) => voidReply(null));
      setHandler(tester, 'clearClientFeatures', mock.call);

      await Varioqub.clearClientFeatures();
      verify(mock.call(any)).called(1);
    });

    testWidgets('getAllKeys', (WidgetTester tester) async {
      setHandler(tester, 'getAllKeys', (ByteData? _) async {
        return pigeonCodec.encodeMessage(<Object?>[
          <String?>['a', null, 'b'],
        ]);
      });

      expect(await Varioqub.getAllKeys(), <String?>['a', null, 'b']);
    });

    testWidgets('setDefaults sends map', (WidgetTester tester) async {
      final Map<String, String> defaults = <String, String>{'k1': 'v1', 'k2': 'v2'};
      final MockHandler mock = MockHandler();
      when(mock.call(any)).thenAnswer((Invocation invocation) async {
        final ByteData? message = invocation.positionalArguments[0] as ByteData?;
        final List<Object?> args = pigeonCodec.decodeMessage(message)! as List<Object?>;
        expect(args, hasLength(1));
        expect(args[0], defaults);
        return pigeonCodec.encodeMessage(<Object?>[]);
      });
      setHandler(tester, 'setDefaults', mock.call);

      await Varioqub.setDefaults(defaults);
      verify(mock.call(any)).called(1);
    });
  });
}
