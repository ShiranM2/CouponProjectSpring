package com.poalim.mv.couponsystemspring.exceptions;

public class CouponSystemException  extends Exception{

    public CouponSystemException(ErrMsg errMsg)  {
        super(errMsg.getMsg());
    }
}