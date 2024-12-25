import 'varioqub_api_pigeon.dart';
import 'varioqub_settings.dart';

class Varioqub {

  static final _varioqub = VarioqubPigeon();

  static Future<String> getId() {
    return _varioqub.getId();
  }

  static Future<void> initVarioqubWithAppMetricaAdapter(VarioqubSettings settings) {
    return _varioqub.initVarioqubWithAppMetricaAdapter(settings.toPigeon());
  }

  static Future<FetchStatus> fetchConfig() {
    return _varioqub.fetchConfig();
  }

  static Future<void> activateConfig() {
    return _varioqub.activateConfig();
  }

  static Future<String> getString(String key, String defaultValue) {
    return _varioqub.getString(key, defaultValue);
  }

  static Future<bool> getBoolean(String key, bool defaultValue) {
    return _varioqub.getBoolean(key, defaultValue);
  }

  static Future<int> getInt(String key, int defaultValue) {
    return _varioqub.getInt(key, defaultValue);
  }

  static Future<double> getDouble(String key, double defaultValue) {
    return _varioqub.getDouble(key, defaultValue);
  }

  static Future<void> putClientFeature(String key, String value) {
    return _varioqub.putClientFeature(key, value);
  }

  static Future<void> clearClientFeatures() {
    return _varioqub.clearClientFeatures();
  }

  static Future<List<String?>> getAllKeys() {
    return _varioqub.getAllKeys();
  }

  static Future<void> setDefaults(Map<String, String> defaults) {
    return _varioqub.setDefaults(defaults);
  }
}
