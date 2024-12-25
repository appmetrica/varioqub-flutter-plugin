package com.yandex.varioqub.flutter;

import androidx.annotation.NonNull;

import com.yandex.varioqub.flutter.pigeon.Pigeon;

import io.flutter.embedding.engine.plugins.FlutterPlugin;

/**
 * VarioqubPlugin
 */
public class VarioqubPlugin implements FlutterPlugin {

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        Pigeon.VarioqubPigeon.setup(
            flutterPluginBinding.getBinaryMessenger(),
            new VarioqubImpl(flutterPluginBinding.getApplicationContext())
        );
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

}
