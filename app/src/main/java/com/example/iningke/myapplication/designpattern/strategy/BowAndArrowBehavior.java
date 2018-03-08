package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:01.
 */

public class BowAndArrowBehavior implements WeaponBehavior {
    @Override
    public void userWeapon() {
        LogUtils.e("使用弓箭射杀");
    }
}
