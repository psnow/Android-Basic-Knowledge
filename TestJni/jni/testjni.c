#include <jni.h>

JNIEXPORT void JNICALL
Java_com_telenav_demo_testjni_Game_doNothing(JNIEnv* env, jobject obj) {
}

JNIEXPORT int JNICALL
Java_com_telenav_demo_testjni_Game_returnInt(JNIEnv* env, jobject obj) {
	return 3;
}

JNIEXPORT void JNICALL
Java_com_telenav_demo_testjni_Game_setInt(JNIEnv* env, jobject obj, jint x) {
	int xx = x;
	return;
}
