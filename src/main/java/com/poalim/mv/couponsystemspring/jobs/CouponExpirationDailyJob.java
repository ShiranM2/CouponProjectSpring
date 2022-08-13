package com.poalim.mv.couponsystemspring.jobs;

import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.repos.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CouponExpirationDailyJob {

    @Autowired
    private final CouponRepository couponRepository;

    @Scheduled(fixedRate = 1000 * 60 * 60 * 24)
    public void removeExpiredCoupons() {
        List<Coupon> expCoupons = couponRepository.findByEndDateBefore(Date.valueOf(LocalDate.now()));
        expCoupons.forEach(coupon -> couponRepository.deleteCustomerVsCouponByCouponId(coupon.getId()));
        expCoupons.forEach((coupon -> couponRepository.deleteById(coupon.getId())));
    }
}