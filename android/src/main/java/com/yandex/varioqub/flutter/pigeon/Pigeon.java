// Autogenerated from Pigeon (v10.1.6), do not edit directly.
// See also: https://pub.dev/packages/pigeon

package com.yandex.varioqub.flutter.pigeon;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.flutter.plugin.common.BasicMessageChannel;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MessageCodec;
import io.flutter.plugin.common.StandardMessageCodec;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** Generated class from Pigeon. */
@SuppressWarnings({"unused", "unchecked", "CodeBlock2Expr", "RedundantSuppression", "serial"})
public class Pigeon {

  /** Error class for passing custom error details to Flutter via a thrown PlatformException. */
  public static class FlutterError extends RuntimeException {

    /** The error code. */
    public final String code;

    /** The error details. Must be a datatype supported by the api codec. */
    public final Object details;

    public FlutterError(@NonNull String code, @Nullable String message, @Nullable Object details) 
    {
      super(message);
      this.code = code;
      this.details = details;
    }
  }

  @NonNull
  protected static ArrayList<Object> wrapError(@NonNull Throwable exception) {
    ArrayList<Object> errorList = new ArrayList<Object>(3);
    if (exception instanceof FlutterError) {
      FlutterError error = (FlutterError) exception;
      errorList.add(error.code);
      errorList.add(error.getMessage());
      errorList.add(error.details);
    } else {
      errorList.add(exception.toString());
      errorList.add(exception.getClass().getSimpleName());
      errorList.add(
        "Cause: " + exception.getCause() + ", Stacktrace: " + Log.getStackTraceString(exception));
    }
    return errorList;
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static final class FetchStatus {
    private @Nullable String error;

    public @Nullable String getError() {
      return error;
    }

    public void setError(@Nullable String setterArg) {
      this.error = setterArg;
    }

    private @Nullable Long status;

    public @Nullable Long getStatus() {
      return status;
    }

    public void setStatus(@Nullable Long setterArg) {
      this.status = setterArg;
    }

    public static final class Builder {

      private @Nullable String error;

      public @NonNull Builder setError(@Nullable String setterArg) {
        this.error = setterArg;
        return this;
      }

      private @Nullable Long status;

      public @NonNull Builder setStatus(@Nullable Long setterArg) {
        this.status = setterArg;
        return this;
      }

      public @NonNull FetchStatus build() {
        FetchStatus pigeonReturn = new FetchStatus();
        pigeonReturn.setError(error);
        pigeonReturn.setStatus(status);
        return pigeonReturn;
      }
    }

    @NonNull
    ArrayList<Object> toList() {
      ArrayList<Object> toListResult = new ArrayList<Object>(2);
      toListResult.add(error);
      toListResult.add(status);
      return toListResult;
    }

    static @NonNull FetchStatus fromList(@NonNull ArrayList<Object> list) {
      FetchStatus pigeonResult = new FetchStatus();
      Object error = list.get(0);
      pigeonResult.setError((String) error);
      Object status = list.get(1);
      pigeonResult.setStatus((status == null) ? null : ((status instanceof Integer) ? (Integer) status : (Long) status));
      return pigeonResult;
    }
  }

  /** Generated class from Pigeon that represents data sent in messages. */
  public static final class VarioqubSettingsPigeon {
    private @Nullable String clientId;

    public @Nullable String getClientId() {
      return clientId;
    }

    public void setClientId(@Nullable String setterArg) {
      this.clientId = setterArg;
    }

    private @Nullable String url;

    public @Nullable String getUrl() {
      return url;
    }

    public void setUrl(@Nullable String setterArg) {
      this.url = setterArg;
    }

    private @Nullable Long fetchThrottleIntervalSeconds;

    public @Nullable Long getFetchThrottleIntervalSeconds() {
      return fetchThrottleIntervalSeconds;
    }

    public void setFetchThrottleIntervalSeconds(@Nullable Long setterArg) {
      this.fetchThrottleIntervalSeconds = setterArg;
    }

    private @Nullable Map<String, String> clientFeatures;

    public @Nullable Map<String, String> getClientFeatures() {
      return clientFeatures;
    }

    public void setClientFeatures(@Nullable Map<String, String> setterArg) {
      this.clientFeatures = setterArg;
    }

    private @Nullable Boolean logs;

    public @Nullable Boolean getLogs() {
      return logs;
    }

    public void setLogs(@Nullable Boolean setterArg) {
      this.logs = setterArg;
    }

    private @Nullable Boolean activateEvent;

    public @Nullable Boolean getActivateEvent() {
      return activateEvent;
    }

    public void setActivateEvent(@Nullable Boolean setterArg) {
      this.activateEvent = setterArg;
    }

    public static final class Builder {

      private @Nullable String clientId;

      public @NonNull Builder setClientId(@Nullable String setterArg) {
        this.clientId = setterArg;
        return this;
      }

      private @Nullable String url;

      public @NonNull Builder setUrl(@Nullable String setterArg) {
        this.url = setterArg;
        return this;
      }

      private @Nullable Long fetchThrottleIntervalSeconds;

      public @NonNull Builder setFetchThrottleIntervalSeconds(@Nullable Long setterArg) {
        this.fetchThrottleIntervalSeconds = setterArg;
        return this;
      }

      private @Nullable Map<String, String> clientFeatures;

      public @NonNull Builder setClientFeatures(@Nullable Map<String, String> setterArg) {
        this.clientFeatures = setterArg;
        return this;
      }

      private @Nullable Boolean logs;

      public @NonNull Builder setLogs(@Nullable Boolean setterArg) {
        this.logs = setterArg;
        return this;
      }

      private @Nullable Boolean activateEvent;

      public @NonNull Builder setActivateEvent(@Nullable Boolean setterArg) {
        this.activateEvent = setterArg;
        return this;
      }

      public @NonNull VarioqubSettingsPigeon build() {
        VarioqubSettingsPigeon pigeonReturn = new VarioqubSettingsPigeon();
        pigeonReturn.setClientId(clientId);
        pigeonReturn.setUrl(url);
        pigeonReturn.setFetchThrottleIntervalSeconds(fetchThrottleIntervalSeconds);
        pigeonReturn.setClientFeatures(clientFeatures);
        pigeonReturn.setLogs(logs);
        pigeonReturn.setActivateEvent(activateEvent);
        return pigeonReturn;
      }
    }

    @NonNull
    ArrayList<Object> toList() {
      ArrayList<Object> toListResult = new ArrayList<Object>(6);
      toListResult.add(clientId);
      toListResult.add(url);
      toListResult.add(fetchThrottleIntervalSeconds);
      toListResult.add(clientFeatures);
      toListResult.add(logs);
      toListResult.add(activateEvent);
      return toListResult;
    }

    static @NonNull VarioqubSettingsPigeon fromList(@NonNull ArrayList<Object> list) {
      VarioqubSettingsPigeon pigeonResult = new VarioqubSettingsPigeon();
      Object clientId = list.get(0);
      pigeonResult.setClientId((String) clientId);
      Object url = list.get(1);
      pigeonResult.setUrl((String) url);
      Object fetchThrottleIntervalSeconds = list.get(2);
      pigeonResult.setFetchThrottleIntervalSeconds((fetchThrottleIntervalSeconds == null) ? null : ((fetchThrottleIntervalSeconds instanceof Integer) ? (Integer) fetchThrottleIntervalSeconds : (Long) fetchThrottleIntervalSeconds));
      Object clientFeatures = list.get(3);
      pigeonResult.setClientFeatures((Map<String, String>) clientFeatures);
      Object logs = list.get(4);
      pigeonResult.setLogs((Boolean) logs);
      Object activateEvent = list.get(5);
      pigeonResult.setActivateEvent((Boolean) activateEvent);
      return pigeonResult;
    }
  }

  public interface Result<T> {
    @SuppressWarnings("UnknownNullness")
    void success(T result);

    void error(@NonNull Throwable error);
  }

  private static class VarioqubPigeonCodec extends StandardMessageCodec {
    public static final VarioqubPigeonCodec INSTANCE = new VarioqubPigeonCodec();

    private VarioqubPigeonCodec() {}

    @Override
    protected Object readValueOfType(byte type, @NonNull ByteBuffer buffer) {
      switch (type) {
        case (byte) 128:
          return FetchStatus.fromList((ArrayList<Object>) readValue(buffer));
        case (byte) 129:
          return VarioqubSettingsPigeon.fromList((ArrayList<Object>) readValue(buffer));
        default:
          return super.readValueOfType(type, buffer);
      }
    }

    @Override
    protected void writeValue(@NonNull ByteArrayOutputStream stream, Object value) {
      if (value instanceof FetchStatus) {
        stream.write(128);
        writeValue(stream, ((FetchStatus) value).toList());
      } else if (value instanceof VarioqubSettingsPigeon) {
        stream.write(129);
        writeValue(stream, ((VarioqubSettingsPigeon) value).toList());
      } else {
        super.writeValue(stream, value);
      }
    }
  }

  /** Generated interface from Pigeon that represents a handler of messages from Flutter. */
  public interface VarioqubPigeon {

    void initVarioqubWithAppMetricaAdapter(@NonNull VarioqubSettingsPigeon settings);

    void fetchConfig(@NonNull Result<FetchStatus> result);

    void activateConfig();

    @NonNull 
    String getString(@NonNull String key, @NonNull String defaultValue);

    @NonNull 
    Boolean getBoolean(@NonNull String key, @NonNull Boolean defaultValue);

    @NonNull 
    Long getInt(@NonNull String key, @NonNull Long defaultValue);

    @NonNull 
    Double getDouble(@NonNull String key, @NonNull Double defaultValue);

    @NonNull 
    String getId();

    void putClientFeature(@NonNull String key, @NonNull String value);

    void clearClientFeatures();

    @NonNull 
    List<String> getAllKeys();

    void setDefaults(@NonNull Map<String, String> defaults);

    /** The codec used by VarioqubPigeon. */
    static @NonNull MessageCodec<Object> getCodec() {
      return VarioqubPigeonCodec.INSTANCE;
    }
    /**Sets up an instance of `VarioqubPigeon` to handle messages through the `binaryMessenger`. */
    static void setup(@NonNull BinaryMessenger binaryMessenger, @Nullable VarioqubPigeon api) {
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.initVarioqubWithAppMetricaAdapter", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                VarioqubSettingsPigeon settingsArg = (VarioqubSettingsPigeon) args.get(0);
                try {
                  api.initVarioqubWithAppMetricaAdapter(settingsArg);
                  wrapped.add(0, null);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.fetchConfig", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                Result<FetchStatus> resultCallback =
                    new Result<FetchStatus>() {
                      public void success(FetchStatus result) {
                        wrapped.add(0, result);
                        reply.reply(wrapped);
                      }

                      public void error(Throwable error) {
                        ArrayList<Object> wrappedError = wrapError(error);
                        reply.reply(wrappedError);
                      }
                    };

                api.fetchConfig(resultCallback);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.activateConfig", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  api.activateConfig();
                  wrapped.add(0, null);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getString", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                String keyArg = (String) args.get(0);
                String defaultValueArg = (String) args.get(1);
                try {
                  String output = api.getString(keyArg, defaultValueArg);
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getBoolean", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                String keyArg = (String) args.get(0);
                Boolean defaultValueArg = (Boolean) args.get(1);
                try {
                  Boolean output = api.getBoolean(keyArg, defaultValueArg);
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getInt", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                String keyArg = (String) args.get(0);
                Number defaultValueArg = (Number) args.get(1);
                try {
                  Long output = api.getInt(keyArg, (defaultValueArg == null) ? null : defaultValueArg.longValue());
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getDouble", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                String keyArg = (String) args.get(0);
                Double defaultValueArg = (Double) args.get(1);
                try {
                  Double output = api.getDouble(keyArg, defaultValueArg);
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getId", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  String output = api.getId();
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.putClientFeature", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                String keyArg = (String) args.get(0);
                String valueArg = (String) args.get(1);
                try {
                  api.putClientFeature(keyArg, valueArg);
                  wrapped.add(0, null);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.clearClientFeatures", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  api.clearClientFeatures();
                  wrapped.add(0, null);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.getAllKeys", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                try {
                  List<String> output = api.getAllKeys();
                  wrapped.add(0, output);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
      {
        BasicMessageChannel<Object> channel =
            new BasicMessageChannel<>(
                binaryMessenger, "dev.flutter.pigeon.varioqub_plugin.VarioqubPigeon.setDefaults", getCodec());
        if (api != null) {
          channel.setMessageHandler(
              (message, reply) -> {
                ArrayList<Object> wrapped = new ArrayList<Object>();
                ArrayList<Object> args = (ArrayList<Object>) message;
                Map<String, String> defaultsArg = (Map<String, String>) args.get(0);
                try {
                  api.setDefaults(defaultsArg);
                  wrapped.add(0, null);
                }
 catch (Throwable exception) {
                  ArrayList<Object> wrappedError = wrapError(exception);
                  wrapped = wrappedError;
                }
                reply.reply(wrapped);
              });
        } else {
          channel.setMessageHandler(null);
        }
      }
    }
  }
}
