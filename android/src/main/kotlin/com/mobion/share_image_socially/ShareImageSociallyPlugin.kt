package com.mobion.share_image_socially

import android.content.Context
import android.content.Intent
import androidx.core.content.FileProvider
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar
import java.io.File

class ShareImageSociallyPlugin(private val registrar: PluginRegistry.Registrar): MethodCallHandler {
  private val androidContext: Context = registrar.activity() ?: registrar.activeContext()
  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "channel:com.mobion.share_image_socially/share")
      channel.setMethodCallHandler(ShareImageSociallyPlugin(registrar))
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if (call.method == "shareImage") {
      shareImage(call.arguments as String)
    } else {
      result.notImplemented()
    }
  }

  private fun shareImage(path:String) {
    val imageFile = File(androidContext.cacheDir,path)
    val contentUri = FileProvider.getUriForFile(androidContext,"com.mobion.share_image_socially",imageFile)

    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.type="image/jpg"
    shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri)
    androidContext.startActivity(Intent.createChooser(shareIntent,"Compartir usando"))
  }
}
