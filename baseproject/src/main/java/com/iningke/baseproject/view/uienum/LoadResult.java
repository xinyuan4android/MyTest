package com.iningke.baseproject.view.uienum;

/**
 * 创建者：Mcablylx
 * 时间：2015/11/13 17:22
 * 类描述：服务器返回的结果类型. 暂时分为3种. 空(empty), 错误(error) ,成功(success),其他（other）
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public enum LoadResult {

    RESULT_ERROR(2), RESULT_EMPTY(3), RESULT_SUCCESS(4),RESULT_OTHER(5);
    private int value;
    LoadResult(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
