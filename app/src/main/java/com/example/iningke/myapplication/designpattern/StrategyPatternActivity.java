package com.example.iningke.myapplication.designpattern;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.designpattern.strategy.AxeBehavior;
import com.example.iningke.myapplication.designpattern.strategy.BowAndArrowBehavior;
import com.example.iningke.myapplication.designpattern.strategy.Character;
import com.example.iningke.myapplication.designpattern.strategy.King;
import com.example.iningke.myapplication.designpattern.strategy.KnifeBehavior;
import com.example.iningke.myapplication.designpattern.strategy.Knight;
import com.example.iningke.myapplication.designpattern.strategy.Queen;
import com.example.iningke.myapplication.designpattern.strategy.SwordBehavior;
import com.example.iningke.myapplication.designpattern.strategy.Troll;

/**
 * 策略模式
 *
 * @author hxy
 * @since 2017/12/21 18:48
 */

public class StrategyPatternActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_strategy_pattern);
        init();
    }

    private void init() {
        king = new King();
        king.doFight();
        queen = new Queen();
        queen.doFight();
        knight = new Knight();
        knight.doFight();
        troll = new Troll();
        troll.doFight();
    }

    private Character king;
    private Character queen;
    private Character knight;
    private Character troll;

    public void king(View view) {
        king.setWeaponBehavior(new BowAndArrowBehavior());
        king.doFight();
    }

    public void queen(View view) {
        queen.setWeaponBehavior(new SwordBehavior());
        queen.doFight();
    }

    public void knight(View view) {
        knight.setWeaponBehavior(new AxeBehavior());
        knight.doFight();
    }

    public void troll(View view) {
        troll.setWeaponBehavior(new KnifeBehavior());
        troll.doFight();
    }
}
