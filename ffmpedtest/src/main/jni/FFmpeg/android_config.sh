#!/bin/bash
PREBUILT=D:/android-ndk-r8e/toolchains/arm-linux-androideabi-4.4.3/prebuilt
PLATFORM=D:/android-ndk-r8e/platforms/android-9/arch-arm

d:/code/git/AAATest/ffmpedtest/jni/ffmpeg/configure
--disable-static
--enable-shared
--enable-gpl
--enable-version3
--enable-nonfree
--enable-doc
--enable-ffmpeg
--enable-ffplay
--enable-ffprobe
--enable-ffserver
--enable-avdevice
--enable-avfilter
--enable-postproc
--enable-small
--cross-prefix=$PREBUILT/windows/bin/arm-linux-androideabi-
--enable-cross-compile
--target-os=linux
--extra-cflags='-I$PLATFORM/usr/include'
--extra-ldflags='-L$PLATFORM/usr/lib -nostdlib'
--arch=arm
--disable-symver
--disable-debug
--disable-stripping