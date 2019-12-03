#import "ShareImageSociallyPlugin.h"
#import <share_image_socially/share_image_socially-Swift.h>

@implementation ShareImageSociallyPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftShareImageSociallyPlugin registerWithRegistrar:registrar];
}
@end
