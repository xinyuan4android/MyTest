package com.example.iningke.myapplication.okhttptest;

import java.io.Serializable;

/**
 * Created by hxy on  2017/9/26.
 */

public class UserInfoModel implements Serializable {

    /**
     * result : {"comment":0,"confirm":0,"createDate":1501827832000,"goodsNum":0,"headImg":"/static/upload/shop/authentication/20170804/53e8d39a50c54c1395728dc77451d148.jpg","id":"53e8d39a50c54c1395728dc77451d148","loginType":"WeiBo","magezineNum":0,"newsNum":0,"nickName":"ComeOn新元","pay":0,"phone":"18396728449","refund":0,"role":1,"send":0,"sex":0,"shopMoney":0,"shopNum":0,"state":-1,"threeState":0}
     * success : true
     */

    private ResultBean result;
    private boolean success;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class ResultBean {
        /**
         * comment : 0
         * confirm : 0
         * createDate : 1501827832000
         * goodsNum : 0
         * headImg : /static/upload/shop/authentication/20170804/53e8d39a50c54c1395728dc77451d148.jpg
         * id : 53e8d39a50c54c1395728dc77451d148
         * loginType : WeiBo
         * magezineNum : 0
         * newsNum : 0
         * nickName : ComeOn新元
         * pay : 0
         * phone : 18396728449
         * refund : 0
         * role : 1
         * send : 0
         * sex : 0
         * shopMoney : 0
         * shopNum : 0
         * state : -1
         * threeState : 0
         */

        private int comment;
        private int confirm;
        private long createDate;
        private int goodsNum;
        private String headImg;
        private String id;
        private String loginType;
        private int magezineNum;
        private int newsNum;
        private String nickName;
        private int pay;
        private String phone;
        private int refund;
        private int role;
        private int send;
        private int sex;
        private int shopMoney;
        private int shopNum;
        private int state;
        private int threeState;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public int getConfirm() {
            return confirm;
        }

        public void setConfirm(int confirm) {
            this.confirm = confirm;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getGoodsNum() {
            return goodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            this.goodsNum = goodsNum;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public int getMagezineNum() {
            return magezineNum;
        }

        public void setMagezineNum(int magezineNum) {
            this.magezineNum = magezineNum;
        }

        public int getNewsNum() {
            return newsNum;
        }

        public void setNewsNum(int newsNum) {
            this.newsNum = newsNum;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPay() {
            return pay;
        }

        public void setPay(int pay) {
            this.pay = pay;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getRefund() {
            return refund;
        }

        public void setRefund(int refund) {
            this.refund = refund;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public int getSend() {
            return send;
        }

        public void setSend(int send) {
            this.send = send;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getShopMoney() {
            return shopMoney;
        }

        public void setShopMoney(int shopMoney) {
            this.shopMoney = shopMoney;
        }

        public int getShopNum() {
            return shopNum;
        }

        public void setShopNum(int shopNum) {
            this.shopNum = shopNum;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getThreeState() {
            return threeState;
        }

        public void setThreeState(int threeState) {
            this.threeState = threeState;
        }

    }

}
