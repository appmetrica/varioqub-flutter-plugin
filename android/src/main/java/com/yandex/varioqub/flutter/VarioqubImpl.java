package com.yandex.varioqub.flutter;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.yandex.varioqub.appmetricaadapter.AppMetricaAdapter;
import com.yandex.varioqub.config.FetchError;
import com.yandex.varioqub.config.OnFetchCompleteListener;
import com.yandex.varioqub.config.Varioqub;
import com.yandex.varioqub.flutter.pigeon.Pigeon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VarioqubImpl implements Pigeon.VarioqubPigeon {

    public static final String TAG = "VarioqubFlutter";

    private static final long FETCH_REQUEST_SUCCESS = 0L;
    private static final long FETCH_REQUEST_THROTTLED = 1L;
    private static final long FETCH_REQUEST_ERROR = 2L;

    @NonNull
    private final Context context;

    public VarioqubImpl(@NonNull Context context) {
        this.context = context;
    }

    @Override
    public void initVarioqubWithAppMetricaAdapter(@NonNull Pigeon.VarioqubSettingsPigeon settingsPigeon) {
        try {
            Varioqub.init(
                VarioqubSettingsConverter.convertSettingsFromPigeon(settingsPigeon),
                new AppMetricaAdapter(context),
                context
            );
        } catch (Throwable e) {
            Log.e(TAG, "Something went wrong while parsing Varioqub Settings", e);
        }
    }

    @Override
    public void fetchConfig(@NonNull Pigeon.Result<Pigeon.FetchStatus> result) {
        Varioqub.fetchConfig(new OnFetchCompleteListener() {
            @Override
            public void onSuccess() {
                result.success(
                    new Pigeon.FetchStatus.Builder()
                        .setStatus(FETCH_REQUEST_SUCCESS)
                        .build()
                );
            }

            @Override
            public void onError(@NonNull String s, @NonNull FetchError fetchError) {
                long fetchStatusCode = -1L;
                switch (fetchError) {
                    case REQUEST_THROTTLED:
                        fetchStatusCode = FETCH_REQUEST_THROTTLED;
                        break;
                    case EMPTY_RESULT:
                    case IDENTIFIERS_NULL:
                    case RESPONSE_PARSE_ERROR:
                    case NETWORK_ERROR:
                    case INTERNAL_ERROR:
                        fetchStatusCode = FETCH_REQUEST_ERROR;
                        break;
                    default:
                        Log.e(TAG, "Unknown fetch error: " + fetchError);
                }

                result.success(
                    new Pigeon.FetchStatus.Builder()
                        .setStatus(fetchStatusCode)
                        .setError(fetchError.name())
                        .build()
                );
            }
        });
    }

    @Override
    public void activateConfig() {
        Varioqub.activateConfig(null);
    }

    @NonNull
    @Override
    public String getString(@NonNull String key, @NonNull String defaultValue) {
        return Varioqub.getString(key, defaultValue);
    }

    @NonNull
    @Override
    public Boolean getBoolean(@NonNull String key, @NonNull Boolean defaultValue) {
        return Varioqub.getBoolean(key, defaultValue);
    }

    @NonNull
    @Override
    public Long getInt(@NonNull String key, @NonNull Long defaultValue) {
        return Varioqub.getLong(key, defaultValue);
    }

    @NonNull
    @Override
    public Double getDouble(@NonNull String key, @NonNull Double defaultValue) {
        return Varioqub.getDouble(key, defaultValue);
    }

    @NonNull
    @Override
    public String getId() {
        return Varioqub.getId();
    }

    @Override
    public void putClientFeature(@NonNull String key, @NonNull String value) {
        Varioqub.putClientFeature(key, value);
    }

    @Override
    public void clearClientFeatures() {
        Varioqub.clearClientFeatures();
    }

    @NonNull
    @Override
    public List<String> getAllKeys() {
        return new ArrayList<>(Varioqub.getAllKeys());
    }

    @Override
    public void setDefaults(@NonNull Map<String, String> defaults) {
        Varioqub.setDefaults(defaults);
    }
}
