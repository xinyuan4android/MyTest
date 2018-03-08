package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:05.
 */

public class Queen extends Character {

    public Queen() {
        weaponBehavior = new BowAndArrowBehavior();
        display();
    }

    @Override
    protected void display() {
        LogUtils.e(" am a queen");
    }
}
