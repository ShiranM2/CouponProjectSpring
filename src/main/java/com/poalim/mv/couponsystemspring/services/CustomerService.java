package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Category;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.beans.Customer;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {

    void purchaseCoupon(Coupon coupon) throws CouponSystemException;

    List<Coupon> getAllCustomerCoupons() throws CouponSystemException;

    List<Coupon> getCustomerCouponsBySpecificCategory(Category category);

    List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice);

    Customer getCustomerDetails() throws CouponSystemException;

    void setCustomerId(int customerId);

}