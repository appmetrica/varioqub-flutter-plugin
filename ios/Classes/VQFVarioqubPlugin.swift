import Flutter
import UIKit

public class VQFVarioqubPlugin: NSObject, FlutterPlugin{

    public static func register(with registrar: FlutterPluginRegistrar) {
        let instance = VQFVarioqubImplementation.init();
        VarioqubPigeonSetup.setUp(binaryMessenger: registrar.messenger(), api: instance);
    }
}
