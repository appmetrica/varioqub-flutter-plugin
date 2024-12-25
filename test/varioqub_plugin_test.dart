import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:mockito/annotations.dart';
import 'package:mockito/mockito.dart';
import 'package:varioqub_plugin/src/varioqub.dart';
import 'package:varioqub_plugin/src/varioqub_settings.dart';

class MockHandler extends Mock {
  Future<ByteData?>? call(ByteData? message);
}

@GenerateMocks([VarioqubSettings])
void main() {
  TestWidgetsFlutterBinding.ensureInitialized();
  
  const codec = StandardMessageCodec();
  
  testWidgets('Test GetId', (WidgetTester tester) async {
    final mock = MockHandler();
    
    when(mock.call(any)).thenAnswer((_) async => Future.value(codec.encodeMessage(["42"])));
    tester.binding.defaultBinaryMessenger.setMockMessageHandler(
        'dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getId', mock);
    expect(await Varioqub.getId(), "42");
  });
}
