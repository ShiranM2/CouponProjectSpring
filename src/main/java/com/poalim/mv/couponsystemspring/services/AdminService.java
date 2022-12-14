package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.Company;
import com.poalim.mv.couponsystemspring.beans.Customer;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import java.util.List;

public interface AdminService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(int companyId, Company company) throws CouponSystemException;
    void deleteCompany(int companyId) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getOneCompany(int companyId) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(int customerId, Customer customer) throws CouponSystemException;
    void deleteCustomer(int customerId) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getOneCustomer(int customerId) throws CouponSystemException;
}
