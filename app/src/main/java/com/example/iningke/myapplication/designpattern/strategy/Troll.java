package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:42.
 */

public class Troll extends Character {
    public Troll() {
        weaponBehavior = new AxeBehavior();
        display();
    }

    @Override
    protected void display() {
        LogUtils.e("am a troll");
    }
}
