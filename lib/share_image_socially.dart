import 'dart:async';
import 'dart:io';
import 'dart:typed_data';
import 'package:path_provider/path_provider.dart';

import 'package:flutter/services.dart';

class ShareImageSocially {
  static const MethodChannel _channel =
      const MethodChannel('channel:com.mobion.share_image_socially/share');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static void shareImage(String path, String fileName) async {
    final ByteData bytes = await rootBundle.load(path + '/' + fileName);
    final Uint8List list = bytes.buffer.asUint8List();

    final tempDir = await getTemporaryDirectory();
    final file = await new File('${tempDir.path}/' + fileName).create();
    file.writeAsBytesSync(list);
    _channel.invokeMethod('shareImage', fileName);
  }
}
