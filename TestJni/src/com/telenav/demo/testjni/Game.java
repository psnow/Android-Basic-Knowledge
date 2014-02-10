/**
 *
 * Copyright 2013 TeleNav, Inc. All rights reserved.
 * Game.java
 *
 */
package com.telenav.demo.testjni;

/**
 * @author Shao Weichao (wchshao@telenav.cn)
 * @date 2013-12-23
 */
public class Game
{
    
    private static Game instance = new Game();
    
    public static Game getInstance()
    {
        return instance;
    }
    
    static
    {
        System.loadLibrary("testjni");
//        nativeInit();
    }
    /*
     * We use a class initializer to allow the native code to cache some
     * field offsets. This native function looks up and caches interesting
     * class/field/method IDs. Throws on failure.
     */
//    private static native void nativeInit();
    
    public native void doNothing();
    
    public native int returnInt();
    
    public native void setInt();
    
    public native void setParams(byte[] params);

}
