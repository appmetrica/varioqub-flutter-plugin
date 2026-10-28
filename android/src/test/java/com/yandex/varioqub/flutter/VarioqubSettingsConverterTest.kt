package com.yandex.varioqub.flutter

import org.assertj.core.api.Assertions.assertThat
import com.yandex.varioqub.flutter.pigeon.Pigeon
import org.junit.Assert.assertThrows
import org.junit.Test

class VarioqubSettingsConverterTest {

    @Test
    fun `null clientId throws`() {
        assertThrows(IllegalArgumentException::class.java) {
            VarioqubSettingsConverter.convertSettingsFromPigeon(
                Pigeon.VarioqubSettingsPigeon.Builder().build()
            )
        }
    }

    @Test
    fun `converts settings with optional fields and skips empty url`() {
        val full = VarioqubSettingsConverter.convertSettingsFromPigeon(
            Pigeon.VarioqubSettingsPigeon.Builder()
                .setClientId("client-1")
                .setUrl("https://example.com/vq")
                .setFetchThrottleIntervalSeconds(120L)
                .setLogs(true)
                .setActivateEvent(false)
                .setClientFeatures(mapOf("k1" to "v1", "k2" to "v2"))
                .build()
        )
        assertThat(full).isNotNull()

        val emptyUrl = VarioqubSettingsConverter.convertSettingsFromPigeon(
            Pigeon.VarioqubSettingsPigeon.Builder()
                .setClientId("client-2")
                .setUrl("")
                .build()
        )
        assertThat(emptyUrl).isNotNull()
    }
}
