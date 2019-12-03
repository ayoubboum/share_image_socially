import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:share_image_socially/share_image_socially.dart';

void main() {
  const MethodChannel channel = MethodChannel('share_image_socially');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await ShareImageSocially.platformVersion, '42');
  });
}
