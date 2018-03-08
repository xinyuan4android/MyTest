package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:03.
 */

public class AxeBehavior implements WeaponBehavior {
    @Override
    public void userWeapon() {
        LogUtils.e("使用斧头劈砍");
    }

}