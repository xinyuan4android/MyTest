package com.example.iningke.myapplication.dragItem;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.Collections;
import java.util.List;

/**
 * 描述：
 * 作者：hxy on  2017/10/10 16:51.
 */

public class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {
    private RecyclerView.Adapter adapter;
    private List list;

    public ItemTouchHelperCallBack(RecyclerView.Adapter adapter, List list) {
        this.adapter = adapter;
        this.list = list;

    }

    /**
     * 官方文档的说明如下：
     * o control which actions user can take on each view, you should override getMovementFlags(RecyclerView, ViewHolder)
     * and return appropriate set of direction flags. (LEFT, RIGHT, START, END, UP, DOWN).
     * 返回我们要监控的方向，上下左右，我们做的是上下拖动，要返回都是UP和DOWN
     * 关键坑爹的是下面方法返回值只有1个，也就是说只能监控一个方向。
     * 不过点入到源码里面有惊喜。源码标记方向如下：
     * public static final int UP = 1     0001
     * public static final int DOWN = 1 << 1; （位运算：值其实就是2）0010
     * public static final int LEFT = 1 << 2   左 值是3
     * public static final int RIGHT = 1 << 3  右 值是8
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //也就是说返回值是组合式的
        //makeMovementFlags (int dragFlags, int swipeFlags)，看下面的解释说明
        int swipFlag = 0;
        //如果也监控左右方向的话，swipFlag=ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        int dragflag = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        //等价于：0001&0010;多点触控标记触屏手指的顺序和个数也是这样标记哦
        return makeMovementFlags(dragflag, swipFlag);

        /**
         * 备注：由getMovementFlags可以联想到setMovementFlags，不过文档么有这个方法，但是：
         * 有 makeMovementFlags (int dragFlags, int swipeFlags)
         * Convenience method to create movement flags.便捷方法创建moveMentFlag
         * For instance, if you want to let your items be drag & dropped vertically and swiped left to be dismissed,
         * you can call this method with: makeMovementFlags(UP | DOWN, LEFT);
         * 这个recyclerview的文档写的简直完美，示例代码都弄好了！！！
         * 如果你想让item上下拖动和左边滑动删除，应该这样用： makeMovementFlags(UP | DOWN, LEFT)
         */

        //拓展一下：如果只想上下的话：makeMovementFlags（UP | DOWN, 0）,标记方向的最小值1
    }


    /**
     * 官方文档的说明如下
     * If user drags an item, ItemTouchHelper will call onMove(recyclerView, dragged, target). Upon receiving this callback,
     * you should move the item from the old position (dragged.getAdapterPosition()) to new position (target.getAdapterPosition())
     * in your adapter and also call notifyItemMoved(int, int).
     * 拖动某个item的回调，在return前要更新item位置，调用notifyItemMoved（draggedPosition，targetPosition）
     * viewHolde:正在拖动item
     * target：要拖到的目标
     *
     * @return true 表示消费事件
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //直接按照文档来操作啊，这文档写得太给力了,简直完美！
        adapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        //注意这里有个坑的，itemView 都移动了，对应的数据也要移动
        Collections.swap(list, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }


    /**
     * 谷歌官方文档说明如下：
     * 这个看了一下主要是做左右拖动的回调
     * When a View is swiped, ItemTouchHelper animates it until it goes out of bounds, then calls onSwiped(ViewHolder, int).
     * At this point, you should update your adapter (e.g. remove the item) and call related Adapter#notify event.
     *
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //暂不处理
    }


    /**
     * 官方文档如下：返回true 当前tiem可以被拖动到目标位置后，直接”落“在target上，其他的上面的tiem跟着“落”，
     * 所以要重写这个方法，不然只是拖动的tiem在动，target tiem不动，静止的
     * Return true if the current ViewHolder can be dropped over the the target ViewHolder.
     *
     * @param recyclerView
     * @param current
     * @param target
     * @return
     */
    @Override
    public boolean canDropOver(RecyclerView recyclerView, RecyclerView.ViewHolder current, RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    /**
     * 官方文档说明如下：
     * Returns whether ItemTouchHelper should start a drag and drop operation if an item is long pressed.
     * 是否开启长按 拖动
     *
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        //return true后，可以实现长按拖动排序和拖动动画了
        return true;
    }
}


