# Varioqub SDK for Flutter
A Flutter plugin for Varioqub. Varioqub is an AB tool that works for Android and iOS.

The plugin is available for Android and iOS and includes native Varioqub SDKs:

- Varioqub SDK for Android [0.7.0](https://yandex.ru/support2/varioqub-app/en/sdk/changelog).
- Varioqub SDK for iOS [0.7.1](https://yandex.ru/support2/varioqub-app/en/sdk/changelog).

You can found documentation [here](https://yandex.ru/support/varioqub-app/en/).

## Getting Started
In your flutter project add the following dependency:
```
dependencies:
  varioqub_plugin: ^0.1.0
```
```
// init varioqub
Varioqub.initVarioqubWithAppMetricaAdapter(VarioqubSettings("<YOUR_CLIENT_ID>"));

// fetch config with flags
Varioqub.fetchConfig().then((value) {
    if (value.status == 0) {
        // activate config
        Varioqub.activateConfig();
    } else {
        log("Error while fetching config - ${value.error}");
    }
});

// use flag from config
Varioqub.getString("key", "defaultValue");
```

## Notes
This plugin uses [Pigeon](https://pub.dev/packages/pigeon) to generate interfaces for communication between Flutter and host platform.
