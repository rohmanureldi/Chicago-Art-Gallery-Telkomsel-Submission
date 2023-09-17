#include <jni.h>

// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("chicagoartgallery");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("chicagoartgallery")
//      }
//    }
extern "C"
JNIEXPORT jstring JNICALL
Java_com_eldirohmanur_chicagoartgallery_core_secured_Secured_getBaseUrl(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("https://api.artic.edu/api/v1/");
}