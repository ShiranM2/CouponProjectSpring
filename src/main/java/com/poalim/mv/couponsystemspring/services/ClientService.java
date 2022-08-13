package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.repos.CompanyRepository;
import com.poalim.mv.couponsystemspring.repos.CouponRepository;
import com.poalim.mv.couponsystemspring.repos.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    @Autowired
    protected CompanyRepository companyRepository;
    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected CouponRepository couponRepository;

    public abstract Boolean login(String email, String password);
}