package com.example.hxy_baseproject.utils;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 15:27.
 */

public class Exceptions {
    public static void illegalArgument(String msg, Object... params)
    {
        throw new IllegalArgumentException(String.format(msg, params));
    }
}
