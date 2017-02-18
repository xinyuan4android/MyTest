package com.example.iningke.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 圆饼图
 *
 * @author hxy
 * @date 2016/11/7
 */

public class MPAndroidChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    private PieChart pieChart;
    private List<ConsumeTypeMoneyPo> consumeTypeMoneyVoList = new ArrayList<>();
    private float totalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mpandroid_chart);
        initData();
        initView();
    }

    private void initData() {
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行1", 41.0f));
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行2", 256.02f));
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行3", 789.23f));
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行4", 256.1f));
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行5", 25.1f));
        consumeTypeMoneyVoList.add(new ConsumeTypeMoneyPo("出行6", 186.7f));
    }

    private void initView() {
        pieChart = (PieChart) findViewById(R.id.consume_pie_chart);
        pieChart.setUsePercentValues(true);//设置value是否用显示百分数,默认为false
        pieChart.setDescription("全年消费情况");//设置描述
        pieChart.setDescriptionTextSize(20);//设置描述字体大小
//pieChart.setDescriptionColor(); //设置描述颜色
//pieChart.setDescriptionTypeface();//设置描述字体

        pieChart.setExtraOffsets(5, 5, 5, 5);//设置饼状图距离上下左右的偏移量

        pieChart.setDragDecelerationFrictionCoef(0.95f);//设置阻尼系数,范围在[0,1]之间,越小饼状图转动越困难


        pieChart.setDrawCenterText(true);//是否绘制中间的文字
        pieChart.setCenterTextColor(Color.RED);//中间的文字颜色
        pieChart.setCenterTextSize(18);//中间的文字字体大小


        pieChart.setDrawHoleEnabled(true);//是否绘制饼状图中间的圆
        pieChart.setHoleColor(Color.WHITE);//饼状图中间的圆的绘制颜色
        pieChart.setHoleRadius(55f);//饼状图中间的圆的半径大小

        pieChart.setTransparentCircleColor(Color.WHITE);//设置圆环的颜色
        pieChart.setTransparentCircleAlpha(110);//设置圆环的透明度[0,255]
        pieChart.setTransparentCircleRadius(60f);//设置圆环的半径值


// enable rotation of the chart by touch
        pieChart.setRotationEnabled(false);//设置饼状图是否可以旋转(默认为true)
        pieChart.setRotationAngle(10);//设置饼状图旋转的角度

        pieChart.setHighlightPerTapEnabled(true);//设置旋转的时候点中的tab是否高亮(默认为true)

        Legend l = pieChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART_CENTER);//设置每个tab的显示位置
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);//设置tab之间Y轴方向上的空白间距值
        l.setYOffset(0f);


        pieChart.setOnChartValueSelectedListener(this);//设值点击时候的回调
        pieChart.animateY(3400, Easing.EasingOption.EaseInQuad);//设置Y轴上的绘制动画
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        for (ConsumeTypeMoneyPo typeMoneyVo : consumeTypeMoneyVoList) {
            PieEntry pieEntry = new PieEntry((float) typeMoneyVo.getTotalMoney(), typeMoneyVo.getConsumeTypeName());
            pieEntries.add(pieEntry);
            totalMoney += typeMoneyVo.getTotalMoney();
        }
        String centerText = 2016 + "年消费\n¥" + totalMoney;
//        String centerText = "年消费\n¥" + 5555;
        pieChart.setCenterText(centerText);//设置中间的文字

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "");
        pieDataSet.setColors(getPieChartColors());
        pieDataSet.setSliceSpace(3f);//设置选中的Tab离两边的距离
        pieDataSet.setSelectionShift(5f);//设置选中的tab的多出来的
        PieData pieData = new PieData();
        pieData.setDataSet(pieDataSet);

        //百分比 数字
        pieData.setValueFormatter(new PercentFormatter());
        pieData.setValueTextSize(12f);
        pieData.setValueTextColor(Color.BLUE);//百分比 数字的颜色

        // entry label styling
        pieChart.setDrawEntryLabels(true);//设置是否绘制Label
        pieChart.setEntryLabelColor(Color.BLACK);//设置绘制Label的颜色
        //pieChart.setEntryLabelTypeface(mTfRegular);
        pieChart.setEntryLabelTextSize(8f);//设置绘制Label的字体大小

        pieChart.setData(pieData);
// undo all highlights
        pieChart.highlightValues(null);
        pieChart.invalidate();
    }

    private int[] getPieChartColors() {
        int[] colors = {Color.CYAN, Color.GRAY, Color.RED, Color.GREEN, Color.MAGENTA, Color.YELLOW};
        return colors;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e != null) {
            PieEntry pieEntry = (PieEntry) e;
            Log.e("iningke", "getConsumeTypeName()" + pieEntry.getLabel());
            Log.e("iningke", "getTotalMoney()" + pieEntry.getValue());
        }
    }

    @Override
    public void onNothingSelected() {
        Log.e("iningke", "!!!!!!!!!!!!onNothingSelected()!!!!!!!!!!!");
    }

    private class ConsumeTypeMoneyPo {
        private float totalMoney;
        private String consumeTypeName;

        public ConsumeTypeMoneyPo() {
        }

        public ConsumeTypeMoneyPo(String consumeTypeName, float totalMoney) {
            this.consumeTypeName = consumeTypeName;
            this.totalMoney = totalMoney;
        }

        public float getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(float totalMoney) {
            this.totalMoney = totalMoney;
        }

        public String getConsumeTypeName() {
            return consumeTypeName;
        }

        public void setConsumeTypeName(String consumeTypeName) {
            this.consumeTypeName = consumeTypeName;
        }
    }
}
