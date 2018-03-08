package com.example.iningke.myapplication.designpattern.strategy;

/**
 * @author :  hxy
 * @since :  2017/12/21 17:53.
 */

public abstract class Character {
    protected WeaponBehavior weaponBehavior;

    protected abstract void display();

    public void doFight() {
        if (weaponBehavior != null) {
            weaponBehavior.userWeapon();
        }
    }

    public void setWeaponBehavior(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }
}
