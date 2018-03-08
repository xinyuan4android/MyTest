package com.example.iningke.myapplication.designpattern.strategy;

import com.example.hxy_baseproject.utils.LogUtils;

/**
 * @author :  hxy
 * @since :  2017/12/21 18:00.
 */

public class KnifeBehavior implements WeaponBehavior {

    @Override
    public void userWeapon() {
        LogUtils.e("使用匕首刺杀");
    }
}
