package com.poalim.mv.couponsystemspring.clr;

import com.poalim.mv.couponsystemspring.beans.*;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import com.poalim.mv.couponsystemspring.jobs.CouponExpirationDailyJob;
import com.poalim.mv.couponsystemspring.repos.CouponRepository;
import com.poalim.mv.couponsystemspring.services.AdminService;
import com.poalim.mv.couponsystemspring.services.CompanyService;
import com.poalim.mv.couponsystemspring.services.CustomerService;
import com.poalim.mv.couponsystemspring.services.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;

@Component
@Order(1)
public class ProjectClr implements CommandLineRunner {
    @Autowired
    private LoginManager loginManager;
    @Autowired
    private CouponRepository couponRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------**********------------------**********--------- START ProjectClr ---------**********------------------**********---------");

        System.out.println("---------**********------------------ ClientService Testing ------------------**********---------");


        System.out.println("---------------------------------AdminService Testing---------------------------------");
        System.out.println("-------1. login into system as admin-------");
        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Administrator);

        System.out.println("-------2. add new companies-------");
        Company company1 = Company.builder()
                .name("castro")
                .email("castro@gmail.com")
                .password("castro1")
                .build();

        Company company2 = Company.builder()
                .name("fox")
                .email("fox@gmail.com")
                .password("fox1")
                .build();
        Company company3 = Company.builder()
                .name("golf")
                .email("golf@gmail.com")
                .password("golf")
                .build();
        Company company4 = Company.builder()
                .name("supersal")
                .email("supersal@gmail.com")
                .password("supersal1")
                .build();
        Company company5 = Company.builder()
                .name("victori")
                .email("victori@gmail.com")
                .password("victori1")
                .build();

        adminService.addCompany(company1);
        adminService.addCompany(company2);
        adminService.addCompany(company3);
        adminService.addCompany(company4);
        adminService.addCompany(company5);
        System.out.println("get All companies : ");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("-------3. update exist company->company with companyId=1-------");
        company1.setEmail("castroAndCo@gmail.com");
        adminService.updateCompany(company1.getId(), company1);
        System.out.println("-------company1 after updated =  ");
        System.out.println(adminService.getOneCompany(company1.getId()));
        System.out.println("-------4. delete exist company->company with companyId=1-------");
        adminService.deleteCompany(1);
        System.out.println("get all company after delete company1:");
        System.out.println(adminService.getAllCompanies());

        System.out.println("-------5. get all companies-------");
        System.out.println(adminService.getAllCompanies());

        System.out.println("-------6. get specific company acoording to companyId->company with companyId=3-------");
        System.out.println(adminService.getOneCompany(company3.getId()));


        System.out.println("-------7. add new customers-------");

        Customer customer1 = Customer.builder()
                .firstName("shiran")
                .lastName("maimon")
                .email("shiran@gmail.com")
                .password("123456")
                .build();
        Customer customer2 = Customer.builder()
                .firstName("nir")
                .lastName("naaman")
                .email("nir@gmail.com")
                .password("nir@gmail.com")
                .build();
        Customer customer3 = Customer.builder()
                .firstName("tamar")
                .lastName("maimon")
                .email("tamar@gmail.com")
                .password("123")
                .build();
        adminService.addCustomer(customer1);
        adminService.addCustomer(customer2);
        adminService.addCustomer(customer3);
        System.out.println("get all customers :");
        System.out.println(adminService.getAllCustomers());

        System.out.println("-------8. update exist customer->customer with customerId=1-------");
        customer1.setLastName("naaman");
        customer1.setEmail("shiranN@gmail.com");
        customer1.setPassword("1234");
        adminService.updateCustomer(customer1.getId(), customer1);
        System.out.println("get customer1 after updated=");
        System.out.println(adminService.getOneCustomer(customer1.getId()));

        System.out.println("-------9. delete exist customer->customer with customerId=3-------");
        adminService.deleteCustomer(customer3.getId());
        System.out.println("get all customers after deleted customer3");
        System.out.println(adminService.getAllCustomers());

        System.out.println("-------10. get all customers-------");
        System.out.println(adminService.getAllCustomers());

        System.out.println("-------11. get specific customer acoording to customerId->customer with customerId=1-------");
        System.out.println(adminService.getOneCustomer(customer1.getId()));


        System.out.println("---------------------------------2. CompanyService Testing---------------------------------");

        System.out.println("-------1. login into system-------");

        CompanyService companyService = (CompanyService) loginManager.login("fox@gmail.com", "fox1", ClientType.Company);
        companyService.setCompanyId(2);
        System.out.println("User from CompanyFacade connect succefully to system! name of company: " + companyService.getCompanyDetails().getName());

        System.out.println("-------2. add new coupons-------");
        System.out.println("-------add new coupons to company2-------");
        Coupon coupon1 = Coupon.builder()
                .id(1)
                .category(Category.VACATION)
                .company(company2)
                .title("title1")
                .description("description1")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 8, 30)))
                .amount(10)
                .price(100)
                .image("image1")
                .build();
        Coupon coupon2 = Coupon.builder()
                .id(2)
                .category(Category.FOOD)
                .company(company2)
                .title("title2")
                .description("description2")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 9, 30)))
                .amount(100)
                .price(100)
                .image("image2")
                .build();
        Coupon coupon3 = Coupon.builder()
                .id(3)
                .category(Category.PC)
                .company(company2)
                .title("title3")
                .description("description3")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 10, 20)))
                .amount(70)
                .price(500)
                .image("image3")
                .build();
        Coupon coupon4 = Coupon.builder()
                .id(4)
                .category(Category.ELECTRICITY)
                .company(company2)
                .title("title4")
                .description("description4")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 6, 2)))
                .amount(10)
                .price(60)
                .image("image4")
                .build();
        Coupon coupon5 = Coupon.builder()
                .id(5)
                .category(Category.ELECTRICITY)
                .company(company2)
                .title("title5")
                .description("description5")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 11, 2)))
                .amount(0)
                .price(60)
                .image("image5")
                .build();
        Coupon coupon6 = Coupon.builder()
                .id(6)
                .category(Category.ELECTRICITY)
                .company(company2)
                .title("title6")
                .description("description6")
                .startDate(Date.valueOf(LocalDate.now()))
                .endDate(Date.valueOf(LocalDate.of(2022, 1, 2)))
                .amount(0)
                .price(60)
                .image("image6")
                .build();
        companyService.addCoupon(coupon1);
        companyService.addCoupon(coupon2);
        companyService.addCoupon(coupon3);
        companyService.addCoupon(coupon4);
        companyService.addCoupon(coupon5);
        companyService.addCoupon(coupon6);
        System.out.println("get all company2's coupons:");
        System.out.println(companyService.getAllCompanyCoupons());

        System.out.println("-------3. update exist coupon->coupon with couponId=1-------");

        coupon1.setAmount(200);
        coupon1.setTitle("new title for coupon1");
        companyService.updateCoupon(1, coupon1);
        System.out.println("get all company2's coupons:(coupon1 after updated)");
        System.out.println(companyService.getAllCompanyCoupons());

        System.out.println("-------4. delete exist coupon->coupon with couponId=1-------");
        companyService.deleteCoupon(coupon1.getId());
        System.out.println("get all company2's coupons:( after deleted coupon1)");
        System.out.println(companyService.getAllCompanyCoupons());

        System.out.println("-------5. get all coupons of this company-------");
        System.out.println(companyService.getAllCompanyCoupons());

        System.out.println("-------6. get all coupons acoording to categoryId->coupons with categoryId=FOOD=1 -------");
        System.out.println(companyService.getAllCompanyCouponsBySpecificCategory(Category.FOOD));

        System.out.println("-------7. get all coupons acoording to max price->coupons with maxPrice=100 -------");

        System.out.println(companyService.getCompanyCouponsByMaxPrice(100.0));

        System.out.println("-------8. get company's details-------");
        System.out.println(companyService.getCompanyDetails());

        System.out.println("---------------------------------3. CustomerService Testing---------------------------------");


        System.out.println("-------1. login into system-------");
        CustomerService customerService = (CustomerService) loginManager.login("shiranN@gmail.com", "1234", ClientType.Customer);
        customerService.setCustomerId(1);
        System.out.println("User from CustomerService connect succefully to system! Details about customer: " + customerService.getCustomerDetails());


        System.out.println("-------2. Purchase new coupons-------");

        customerService.purchaseCoupon(coupon2);
        customerService.purchaseCoupon(coupon3);


        System.out.println("-------3. get all the coupons that customer Purchased -------");
        System.out.println(customerService.getAllCustomerCoupons());
        System.out.println("-------4. get all the coupons from specific category that customer Purchased -------");

        System.out.println(customerService.getCustomerCouponsBySpecificCategory(Category.FOOD));

        System.out.println("-------5. get all the coupons below to max price that customer Purchased -------");

        System.out.println(customerService.getCustomerCouponsByMaxPrice(100.0));


        System.out.println("-------6. get customer details -------");
        System.out.println(customerService.getCustomerDetails());

        System.out.println("---------------------------------------------4. Testing CouponSystemException---------------------------------------------");
        System.out.println("---------------------------------AdminService Testing---------------------------------");

        System.out.println("Trying to add Company with existing name or email(company=fox)");
        try {
            Company company2Duplicated = Company.builder()
                    .name("fox")
                    .email("fox@gmail.com")
                    .password("fox1")
                    .build();
            adminService.addCompany(company2Duplicated);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }



        System.out.println( "Trying to update name or id of exist Company (company=fox)");
        try {
            company2.setName("fox2");
            adminService.updateCompany(company2.getId(), company2);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }


//        System.out.println("Deleting company and checking the coupons and purchase history ");
//        System.out.println("Before company deleted :");
//        System.out.println(customerService.getCustomerDetails());
//        adminService.deleteCompany(company2.getId());
//        System.out.println("After company deleted :");
//        System.out.println(customerService.getCustomerDetails());

        System.out.println("Adding new customer with same email to another customer: ");
        Customer  customer1Duplicated= Customer.builder()
                .firstName("shiran")
                .lastName("naaman")
                .email("shiranN@gmail.com")
                .password("1234")
                .build();
        try{
            adminService.addCustomer(customer1Duplicated);
        }catch (CouponSystemException e){
            System.out.println(e);
        }

        System.out.println("---------------------------------CompanyService Testing---------------------------------");
        System.out.println("Trying to add new coupon with existing title ");

        try {
            Coupon coupon2Duplicated = Coupon.builder()
                    .title("title2")
                    .build();
            companyService.addCoupon(coupon2Duplicated);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }
        System.out.println("Trying to update companyId in exist coupon   ");
        try {
            coupon2.setCompany(company2 );
            companyService.updateCoupon(coupon2.getId(),coupon2);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }

        System.out.println("---------------------------------CustomerService Testing---------------------------------");
        System.out.println("Trying to purchase Coupon that expired ");
        try {
            customerService.purchaseCoupon(coupon4);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }
        System.out.println("Trying to purchase Coupon that his amount is 0");
        try {
            customerService.purchaseCoupon(coupon5);
        } catch (CouponSystemException e) {
            System.out.println(e);
        }

        System.out.println("---------------------------------Testing Daily Job to delete Expired coupons Testing---------------------------------");

        CouponExpirationDailyJob thread = new CouponExpirationDailyJob(couponRepository);
        System.out.println("Get all the Coupons");
        System.out.println(companyService.getAllCompanyCoupons());
        thread.removeExpiredCoupons();
        System.out.println("After removal of expired coupons");
        System.out.println(companyService.getAllCompanyCoupons());
        System.out.println("---------**********------------------**********--------- END testAll ---------**********------------------**********---------");
    }
}