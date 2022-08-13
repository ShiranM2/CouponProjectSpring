package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Category;
import com.poalim.mv.couponsystemspring.beans.Company;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import java.util.List;

public interface CompanyService {

    void addCoupon(Coupon coupon) throws CouponSystemException;

    void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException;

    void deleteCoupon(int couponId) throws CouponSystemException;

    List<Coupon> getAllCompanyCoupons() throws CouponSystemException;

    List<Coupon> getAllCompanyCouponsBySpecificCategory(Category category) throws CouponSystemException;

    List<Coupon> getCompanyCouponsByMaxPrice(Double maxPrice) throws CouponSystemException;

    Company getCompanyDetails() throws CouponSystemException;

    void setCompanyId(int companyId);
}