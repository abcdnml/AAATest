LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#include$(all-subdir-makefiles)

include$(LOCAL_PATH)/ffmpeg/Android.mk

LOCAL_MODULE    := mycfile
LOCAL_SRC_FILES := mycfile.c
APP_PLATFORM    := android-19

LOCAL_SHORT_COMMANDS :=true