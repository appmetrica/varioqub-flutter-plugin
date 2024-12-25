package com.yandex.varioqub.flutter;

import android.text.TextUtils;

import com.yandex.varioqub.config.Varioqub;
import com.yandex.varioqub.config.VarioqubSettings;
import com.yandex.varioqub.flutter.pigeon.Pigeon;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class VarioqubSettingsConverter {

    public static VarioqubSettings convertSettingsFromPigeon(Pigeon.VarioqubSettingsPigeon settingsPigeon) {

        String clientId = settingsPigeon.getClientId();

        if (clientId == null) {
            throw new IllegalArgumentException("Client id must not be null");
        }

        VarioqubSettings.Builder settingsBuilder = new VarioqubSettings.Builder(clientId);

        if (!TextUtils.isEmpty(settingsPigeon.getUrl())) {
            settingsBuilder.withUrl(settingsPigeon.getUrl());
        }

        if (settingsPigeon.getFetchThrottleIntervalSeconds() != null) {
            settingsBuilder.withThrottleInterval(settingsPigeon.getFetchThrottleIntervalSeconds());
        }

        if (settingsPigeon.getLogs() != null && settingsPigeon.getLogs()) {
            settingsBuilder.withLogs();
        }

        if  (settingsPigeon.getActivateEvent() != null) {
            settingsBuilder.withActivateEvent(settingsPigeon.getActivateEvent());
        }

        if (settingsPigeon.getClientFeatures() != null) {
            for (Map.Entry<String, String> entry : settingsPigeon.getClientFeatures().entrySet()) {
                settingsBuilder.withClientFeature(entry.getKey(), entry.getValue());
            }
        }

        return settingsBuilder.build();
    }
}
