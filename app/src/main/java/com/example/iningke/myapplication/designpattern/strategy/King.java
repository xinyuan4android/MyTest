package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:10.
 */

public class King extends Character {
    public King() {
        weaponBehavior = new SwordBehavior();
        display();
    }

    @Override
    protected void display() {
        LogUtils.e("am a king");
    }
}
