import XCTest
@testable import varioqub_plugin

final class RunnerTests: XCTestCase {

    func testConvertPigeonSettingsToVarioqubConfig() {
        let impl = VQFVarioqubImplementation()
        var settings = VarioqubSettingsPigeon()
        settings.clientId = "app.client.id"
        settings.url = "https://example.com/vq"
        settings.fetchThrottleIntervalSeconds = 90
        settings.clientFeatures = ["tier": "gold"]
        settings.logs = true
        settings.activateEvent = false

        let config = impl.convertPigeonSettingsToVarioqubConfig(settings: settings)
        XCTAssertEqual(config.baseURL?.absoluteString, "https://example.com/vq")
        XCTAssertEqual(config.fetchThrottle, 90)
    }

    func testConvertPigeonSettingsKeepsDefaultsWithoutOptionalFields() {
        let impl = VQFVarioqubImplementation()
        var settings = VarioqubSettingsPigeon()
        settings.clientId = "app.client.id"
        let config = impl.convertPigeonSettingsToVarioqubConfig(settings: settings)
        XCTAssertNil(config.baseURL)
    }
}
