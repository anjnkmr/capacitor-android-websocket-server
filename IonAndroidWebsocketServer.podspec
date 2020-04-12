
  Pod::Spec.new do |s|
    s.name = 'IonAndroidWebsocketServer'
    s.version = '0.0.1'
    s.summary = 'It is a wrapper for github.com/koush/AndroidAsync android library. Asynchronous socket, http(s) (client+server) and websocket library for android. Based on nio.'
    s.license = 'MIT'
    s.homepage = 'ion-android-websocket-server'
    s.author = 'Anjan Kumar <kumar.rakuditi@gmail.com> (https://github.com/anjnkmr)'
    s.source = { :git => 'ion-android-websocket-server', :tag => s.version.to_s }
    s.source_files = 'ios/Plugin/**/*.{swift,h,m,c,cc,mm,cpp}'
    s.ios.deployment_target  = '11.0'
    s.dependency 'Capacitor'
  end