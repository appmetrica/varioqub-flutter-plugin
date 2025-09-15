// swift-tools-version: 5.9
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "varioqub_plugin",
    platforms: [
        .iOS("13.0")
    ],
    products: [
        .library(name: "varioqub-plugin", targets: ["varioqub_plugin"]),
    ],
    dependencies: [
        .package(
            url: "https://github.com/appmetrica/varioqub-sdk-ios",
            from: "1.0.1"
        ),
        .package(
            url: "https://github.com/appmetrica/varioqub-sdk-appmetrica-adapter-ios",
            from: "1.0.1"
        ),
    ],
    targets: [
        .target(
            name: "varioqub_plugin",
            dependencies: [
                .product(name: "Varioqub", package: "varioqub-sdk-ios"),
                .product(name: "VarioqubAppMetricaAdapter", package: "varioqub-sdk-appmetrica-adapter-ios"),
            ],
            resources: [],
            cSettings: [
                .headerSearchPath("include/varioqub_plugin"),
            ]
        )
    ]
)
