package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Category;
import com.poalim.mv.couponsystemspring.beans.Company;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import com.poalim.mv.couponsystemspring.exceptions.ErrMsg;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@NoArgsConstructor
public class CompanyServiceImpl extends ClientService implements CompanyService {

    private int companyId;

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public Boolean login(String email, String password) {
        return (this.companyRepository.existsByEmailAndPassword(email, password));
    }

    @Override
    public void addCoupon(Coupon coupon) throws CouponSystemException {
        if (this.couponRepository.existsByIdAndTitle(companyId, coupon.getTitle()))
            throw new CouponSystemException(ErrMsg.COUPON_WITH_THIS_TITLE_ALREADY_EXIST);
        this.couponRepository.save(coupon);

    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemException {
        Coupon needToBeUpdatedCoupon = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST));

        if (couponId != coupon.getId())
            throw new CouponSystemException(ErrMsg.COUPON_ID_NOT_UPDATABLE);

        if (needToBeUpdatedCoupon.getCompany().getId() != coupon.getCompany().getId())
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_UPDATABLE);

        this.couponRepository.save(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CouponSystemException {
        Coupon needToBeDeletedCoupons = this.couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.COUPON_ID_NOT_EXIST));

        this.couponRepository.deletePurchaseByCouponId(couponId);
        this.couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons() throws CouponSystemException {
        Company currCompany= this.companyRepository.findById(this.companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));
        return currCompany.getCoupons();
    }

    @Override
    public List<Coupon> getAllCompanyCouponsBySpecificCategory(Category category) throws CouponSystemException {
        Company currCompany= this.companyRepository.findById(this.companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));

        return this.couponRepository.findByCompanyAndCategory(currCompany, category);
    }

    @Override
    public List<Coupon> getCompanyCouponsByMaxPrice(Double maxPrice) throws CouponSystemException {
        return this.couponRepository.findCompanyCouponsByMaxPrice(this.companyId, maxPrice);

    }

    @Override
    public Company getCompanyDetails() throws CouponSystemException {
        return this.companyRepository.findById(this.companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));
    }
}