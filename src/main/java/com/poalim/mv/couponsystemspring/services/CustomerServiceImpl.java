package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Category;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.beans.Customer;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import com.poalim.mv.couponsystemspring.exceptions.ErrMsg;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
@NoArgsConstructor
public class CustomerServiceImpl extends ClientService implements CustomerService {
    private int customerId;
    private LoginManager loginManager = new LoginManager();

    @Override
    public Boolean login(String email, String password) {
        return (this.customerRepository.existsByEmailAndPassword(email, password));
    }

    @Override
    public void purchaseCoupon(Coupon coupon) throws CouponSystemException {
        Customer currCustomer = this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));

        Coupon needToBePurchasedCoupon = this.couponRepository.findById(coupon.getId())
                .orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST));

        if (currCustomer.getCoupons().contains(coupon))
            throw new CouponSystemException(ErrMsg.THIS_COUPON_ALREADY_BEEN_PURCHASED_BY_CUSTOMER);

        if (needToBePurchasedCoupon.getAmount() < 0)
            throw new CouponSystemException(ErrMsg.THIS_COUPON_IS_OUT_OF_STOCK_RIGHT_NOW);

        if (needToBePurchasedCoupon.getEndDate().before(Date.valueOf(LocalDate.now())))
            throw new CouponSystemException(ErrMsg.THIS_COUPON_EXPIRED);

        needToBePurchasedCoupon.setAmount(needToBePurchasedCoupon.getAmount() - 1);

        this.couponRepository.saveAndFlush(needToBePurchasedCoupon);

        currCustomer.getCoupons().add(needToBePurchasedCoupon);
        this.customerRepository.save(currCustomer);
    }

    @Override
    public List<Coupon> getAllCustomerCoupons() throws CouponSystemException {
        Customer currCustomer= this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));
        return currCustomer.getCoupons();
    }

    @Override
    public List<Coupon> getCustomerCouponsBySpecificCategory(Category category) {
       return this.couponRepository.findCustomerCouponsByCategory(this.customerId, category.name());

    }

    @Override
    public List<Coupon> getCustomerCouponsByMaxPrice(double maxPrice) {
        return this.couponRepository.findCustomerCouponsByMaxPrice(this.customerId,maxPrice);
    }

    @Override
    public Customer getCustomerDetails() throws CouponSystemException {
        return this.customerRepository.findById(this.customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));
    }

    @Override
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}