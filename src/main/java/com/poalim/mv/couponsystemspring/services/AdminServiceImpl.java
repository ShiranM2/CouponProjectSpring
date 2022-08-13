package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Company;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import com.poalim.mv.couponsystemspring.beans.Customer;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import com.poalim.mv.couponsystemspring.exceptions.ErrMsg;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class AdminServiceImpl extends ClientService implements AdminService {

    @Override
    public Boolean login(String email, String password) {
        return ((email.equals("admin@admin.com")) && (password.equals("admin")));
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (this.companyRepository.existsByName(company.getName()))
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_ALREADY_EXIST);
        if (this.companyRepository.existsByEmail(company.getEmail()))
            throw new CouponSystemException(ErrMsg.COMPANY_EMAIL_ALREADY_EXIST);
        this.companyRepository.save(company);
    }

    @Override
    public void updateCompany(int companyId, Company company) throws CouponSystemException {
        Company currCompany = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));

        if (companyId != company.getId())
            throw new CouponSystemException(ErrMsg.COMPANY_ID_NOT_UPDATABLE);

        if (!company.getName().equals(currCompany.getName()))
            throw new CouponSystemException(ErrMsg.COMPANY_NAME_NOT_UPDATABLE);

        this.companyRepository.save(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CouponSystemException {
        Company needToBeDeletedCompany = this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));


        for (Coupon coupon : needToBeDeletedCompany.getCoupons()) {
            this.couponRepository.deletePurchaseByCouponId(coupon.getId());
        }
        this.companyRepository.delete(needToBeDeletedCompany);
    }

    @Override
    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    @Override
    public Company getOneCompany(int companyId) throws CouponSystemException {
        return this.companyRepository.findById(companyId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.NO_COMPANY_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (this.customerRepository.existsByEmail(customer.getEmail()))
            throw new CouponSystemException(ErrMsg.CUSTOMER_EMAIL_ALREADY_EXIST);
        this.customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(int customerId, Customer customer) throws CouponSystemException {
        if (customerId != customer.getId())
            throw new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_UPDATABLE);

        this.customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(int customerId) throws CouponSystemException {
        Customer needToBeDeletedCustomer = this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));

        for (Coupon coupon : needToBeDeletedCustomer.getCoupons()) {
            this.couponRepository.deletePurchaseByCouponId(coupon.getId());

        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CouponSystemException {
        return this.customerRepository.findById(customerId)
                .orElseThrow(() -> new CouponSystemException(ErrMsg.CUSTOMER_ID_NOT_EXIST));
    }

}