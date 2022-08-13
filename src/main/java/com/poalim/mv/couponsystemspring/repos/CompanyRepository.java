package com.poalim.mv.couponsystemspring.repos;

import com.poalim.mv.couponsystemspring.beans.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {

    Boolean existsByEmailAndPassword(String email, String password);
    boolean existsByName(String name);
    boolean existsByEmail(String email);
}