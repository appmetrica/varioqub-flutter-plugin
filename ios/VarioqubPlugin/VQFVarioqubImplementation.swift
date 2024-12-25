import Foundation
import Varioqub
import MetricaAdapterReflection

class VQFVarioqubImplementation: VarioqubPigeon {
    func initVarioqubWithAppMetricaAdapter(settings: VarioqubSettingsPigeon) throws {
        
        let clientId = settings.clientId!
        let vqConfig = convertPigeonSettingsToVarioqubConfig(settings: settings)

        let adapter = AppmetricaAdapter()

        VarioqubFacade.shared.initialize(clientId: clientId, config: vqConfig, idProvider: adapter, reporter: adapter)
    }

    func fetchConfig(completion: @escaping (Result<FetchStatus, any Error>) -> Void) {
        let varioqub = VarioqubFacade.shared

        varioqub.fetchConfig({ status in
            switch status {
            case .success, .cached: completion(.success(FetchStatus(error: "", status: 0)))
            case .throttled: completion(.success(FetchStatus(error: "", status: 1)))
            case .error(let e):
                switch e {
                case .emptyResult: completion(.success(FetchStatus(error: "EMPTY_RESULT", status: 2)))
                case .nullIdentifiers: completion(.success(FetchStatus(error: "IDENTIFIERS_NULL", status: 2)))
                case .request: completion(.success(FetchStatus(error: "INTERNAL_ERROR", status: 2)))
                case .response(_): completion(.success(FetchStatus(error: "NETWORK_ERROR", status: 2)))
                case .parse(_): completion(.success(FetchStatus(error: "RESPONSE_PARSE_ERROR", status: 2)))
                case .network(_): completion(.success(FetchStatus(error: "NETWORK_ERROR", status: 2)))
                case .underlying(_): completion(.success(FetchStatus(error: "INTERNAL_ERROR", status: 2)))
                @unknown default:
                    completion(.success(FetchStatus(error: "UNKNOWN", status: 2)))
                }
            @unknown default:
                completion(.success(FetchStatus(error: "UNKNOWN", status: 2)))
            }
        })
    }

    func activateConfig() {
        VarioqubFacade.shared.activateConfigAndWait()
    }

    func getString(key: String, defaultValue: String) throws -> String {
        return VarioqubFacade.shared.getString(for: VarioqubFlag(rawValue: key), defaultValue: defaultValue)
    }

    func getBoolean(key: String, defaultValue: Bool) throws -> Bool {
        return VarioqubFacade.shared.getBool(for: VarioqubFlag(rawValue: key), defaultValue: defaultValue)
    }

    func getInt(key: String, defaultValue: Int64) throws -> Int64 {
        return VarioqubFacade.shared.getInt64(for: VarioqubFlag(rawValue: key), defaultValue: defaultValue)
    }

    func getDouble(key: String, defaultValue: Double) throws -> Double {
        return VarioqubFacade.shared.getDouble(for: VarioqubFlag(rawValue: key), defaultValue: defaultValue)
    }

    func getId() throws -> String {
        return VarioqubFacade.shared.varioqubId ?? ""
    }

    func putClientFeature(key: String, value: String) throws {
        VarioqubFacade.shared.clientFeatures.setFeature(value, forKey: key)
    }

    func clearClientFeatures() throws {
        VarioqubFacade.shared.clientFeatures.clearFeatures()
    }

    func getAllKeys() throws -> [String] {
        return Array(VarioqubFacade.shared.allKeys.map({"\($0.rawValue)"}))
    }

    func setDefaults(defaults: [String : String]) throws {
        var defaultFlagsMap = [Varioqub.VarioqubFlag : String]()

        for (key, value) in defaults {
            defaultFlagsMap[VarioqubFlag(rawValue: key )] = String(describing: value)
        }

        VarioqubFacade.shared.setDefaultsAndWait(defaultFlagsMap)
    }
    
    func convertPigeonSettingsToVarioqubConfig(settings: VarioqubSettingsPigeon) -> VarioqubConfig {
        var vqConfig = VarioqubConfig.default
        
        if let url = settings.url {
            vqConfig.baseURL = URL(string: url)
        }
        vqConfig.fetchThrottle = TimeInterval(truncating: settings.fetchThrottleIntervalSeconds! as NSNumber)
        vqConfig.initialClientFeatures = ClientFeatures(dictionary: settings.clientFeatures as? [String: String] ?? [:])
        
        return vqConfig
    }
}
