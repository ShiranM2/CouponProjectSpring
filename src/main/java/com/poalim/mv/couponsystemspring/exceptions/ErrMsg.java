package com.poalim.mv.couponsystemspring.exceptions;

public enum ErrMsg {

    //---------********** Company **********---------");
    COMPANY_NAME_ALREADY_EXIST("Company name already exist..."),
    COMPANY_EMAIL_ALREADY_EXIST("Company email already exist..."),
    COMPANY_NAME_NOT_UPDATABLE("company name not updatable..."),
    COMPANY_ID_NOT_UPDATABLE("company id not updatable..."),
    NO_COMPANY_EXIST("there is no companies exist with this id..."),

    //---------********** Customer **********---------");
    CUSTOMER_EMAIL_ALREADY_EXIST("customer email already exist..."),
    CUSTOMER_ID_NOT_UPDATABLE("customer id not updatable..."),
    CUSTOMER_ID_NOT_EXIST("customer id not exist..."),

    //---------********** Coupon **********---------");
    COUPON_ID_NOT_EXIST("coupon id not exist..."),
    COUPON_ID_NOT_UPDATABLE("coupon id not updatable..."),
    COUPON_WITH_THIS_TITLE_ALREADY_EXIST("coupon with this title already exist"),
    THIS_COUPON_ALREADY_BEEN_PURCHASED_BY_CUSTOMER("this coupon already been purchased by this customer...Can't purchase coupon more than one time!"),
    THIS_COUPON_IS_OUT_OF_STOCK_RIGHT_NOW("this coupon is out of stock right now..."),
    THIS_COUPON_EXPIRED("this coupon expired");

    private String msg;

    ErrMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}