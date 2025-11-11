#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint varioqub_plugin.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'varioqub_plugin'
  s.version          = '0.2.0'
  s.summary          = 'Varioqub SDK for Flutter'
  s.description      = <<-DESC
A new Flutter project.
                       DESC
  s.homepage         = 'http://example.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'email@example.com' }
  s.source           = { :path => '.' }
  s.source_files = 'varioqub_plugin/Sources/varioqub_plugin/**/*.swift'
  s.dependency 'Flutter'
  s.dependency 'Varioqub', '~> 1.0'
  s.dependency 'VarioqubAppMetricaAdapter', '~> 1.0'
  s.platform = :ios, '13.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
