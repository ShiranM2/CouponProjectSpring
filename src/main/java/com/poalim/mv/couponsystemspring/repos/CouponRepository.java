package com.poalim.mv.couponsystemspring.repos;

import com.poalim.mv.couponsystemspring.beans.Category;
import com.poalim.mv.couponsystemspring.beans.Company;
import com.poalim.mv.couponsystemspring.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

        boolean existsByIdAndTitle(int companyId, String title);


        @Query(value = "DELETE FROM `coupon-project`.customer_coupons  where coupons_id = :couponId", nativeQuery = true)
        @Transactional
        @Modifying
        void deletePurchaseByCouponId(@Param("couponId") int couponId);


        @Query(value = "SELECT * FROM `coupon-project`.coupon where company_id = :companyId and price <= :maxPrice ; ", nativeQuery = true)
        List<Coupon> findCompanyCouponsByMaxPrice(@Param("companyId")int companyId, @Param("maxPrice")double maxPrice);


        List<Coupon> findByCompanyAndCategory(Company company, Category category);

        @Query(value = "SELECT * FROM `coupon-project`.coupon where category = :category and id in (SELECT coupons_id from `coupon-project`.customer_coupons where customer_id = :customerId )", nativeQuery = true)
        List<Coupon> findCustomerCouponsByCategory(@Param("customerId")int customerId, @Param("category")String category);

        @Query(value = "SELECT * FROM `coupon-project`.coupon where price <= :maxPrice and id in (SELECT  coupons_id from `coupon-project`.customer_coupons where customer_id = :customerId)  ", nativeQuery = true)
        List<Coupon> findCustomerCouponsByMaxPrice(@Param("customerId")int customerId,@Param("maxPrice") double maxPrice);

        List<Coupon> findByEndDateBefore(Date currDate );

        @Query(value = "DELETE FROM `coupon-project`.customer_coupons where coupons_id  =:id ", nativeQuery = true)
        @Transactional
        @Modifying
        void deleteCustomerVsCouponByCouponId(@Param("id") int couponId);

        void deleteCouponById(@Param("couponId") int couponId);
}