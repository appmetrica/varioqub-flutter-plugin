import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:varioqub_plugin/varioqub_plugin.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _varioqubId = 'Unknown';

  var _varioqubKeys = <String?>[];

  @override
  void initState() {
    super.initState();
    _initVarioqub();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String varioqubId;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      varioqubId = await Varioqub.getId();
    } on PlatformException {
      varioqubId = 'Failed to get varioqub id.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _varioqubId = varioqubId;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Varioqub Plugin'),
        ),
        body: Column(
          children: [
            Text('Running on: $_varioqubId\n'),
            Text('$_varioqubKeys'),
          ],
        ),
      ),
    );
  }

  Future<void> _initVarioqub() async {
    // Project ID specified as appmetrica.XXXXXX, where XXXXXX is the app ID
    // from the AppMetrica interface. For example, VarioqubSettings("appmetrica.1234567")
    const settings = VarioqubSettings('appmetrica.1234567');

    // Initialize the Varioqub library
    await Varioqub.initVarioqubWithAppMetricaAdapter(settings);

    // Run fetchConfig() in the background to export the new flag values
    // from the server and activate them when the next session starts.
    final fetchStatus = await Varioqub.fetchConfig();

    if (fetchStatus.status == 0) {
      await Varioqub.activateConfig();
      final keys = await Varioqub.getAllKeys();

      setState(() {
        _varioqubKeys = keys;
      });

      // Examples how to get flag values
      await Varioqub.getBoolean('FEATURE_FLAG_KEY_0', false);
      await Varioqub.getDouble('FEATURE_FLAG_KEY_1', 1.0);
      await Varioqub.getInt('FEATURE_FLAG_KEY_2', 1);
      await Varioqub.getString('FEATURE_FLAG_KEY_3', 'test');
    } else {
      debugPrint(fetchStatus.error ?? '');
    }
  }
}
