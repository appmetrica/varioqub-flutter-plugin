import 'package:pigeon/pigeon.dart';

@ConfigurePigeon(PigeonOptions(
  dartOut: 'lib/src/varioqub_api_pigeon.dart',
  swiftOut: 'ios/varioqub_plugin/Sources/varioqub_plugin/VQFVarioqubPigeon.swift',
  javaOut: 'android/src/main/java/com/yandex/varioqub/flutter/pigeon/Pigeon.java',
  javaOptions: JavaOptions(package: 'com.yandex.varioqub.flutter.pigeon'),
))

class FetchStatus {
  String? error;
  int? status;
}

class VarioqubSettingsPigeon {
  String? clientId;
  String? url;
  int? fetchThrottleIntervalSeconds;
  Map<String?, String?>? clientFeatures;
  bool? logs;
  bool? activateEvent;
}

@HostApi()
abstract class VarioqubPigeon {

  void initVarioqubWithAppMetricaAdapter(VarioqubSettingsPigeon settings);

  @async
  FetchStatus fetchConfig();

  void activateConfig();

  String getString(String key, String defaultValue);

  bool getBoolean(String key, bool defaultValue);

  int getInt(String key, int defaultValue);

  double getDouble(String key, double defaultValue);

  String getId();

  void putClientFeature(String key, String value);

  void clearClientFeatures();

  List<String> getAllKeys();

  void setDefaults(Map<String, String> defaults);
}
