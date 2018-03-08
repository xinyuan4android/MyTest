package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:11.
 */

public class Knight extends Character {
    public Knight() {
        weaponBehavior = new KnifeBehavior();
        display();
    }

    @Override
    protected void display() {
        LogUtils.e(" am a  knight");
    }
}
