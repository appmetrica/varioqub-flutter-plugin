import 'package:varioqub_plugin/src/varioqub_api_pigeon.dart';

class VarioqubSettings {
  final String clientId;
  final String? url;
  final int? fetchThrottleIntervalSeconds;
  final Map<String, String>? clientFeatures;
  final bool? logs;
  final bool? activateEvent;

  const VarioqubSettings(
    this.clientId, {
    this.url,
    this.fetchThrottleIntervalSeconds,
    this.clientFeatures,
    this.logs,
    this.activateEvent,
  });

  VarioqubSettingsPigeon toPigeon() {
    return VarioqubSettingsPigeon(
      clientId: clientId,
      url: url,
      fetchThrottleIntervalSeconds: fetchThrottleIntervalSeconds,
      clientFeatures: clientFeatures,
      logs: logs,
      activateEvent: activateEvent,
    );
  }
}
