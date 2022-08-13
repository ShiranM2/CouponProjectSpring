package com.poalim.mv.couponsystemspring.services;

import com.poalim.mv.couponsystemspring.beans.ClientType;
import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import com.poalim.mv.couponsystemspring.exceptions.ErrMsg;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;

    public ClientService login(String email, String password, ClientType clientType)   {

        switch (clientType) {
            case Administrator: {
                AdminService administrator = ctx.getBean(AdminService.class);
                if (((AdminServiceImpl) administrator).login(email, password)) {
                    return (ClientService) administrator;
                }
                break;


            }
            case Company: {
                CompanyService company =  ctx.getBean(CompanyService.class);
                if (((CompanyServiceImpl) company).login(email, password)) {
                    return (ClientService) company;
                }
                break;
            }
            case Customer: {
                CustomerService customer = ctx.getBean(CustomerService.class);
                if (((CustomerServiceImpl) customer).login(email, password)) {
                    return (ClientService) customer;
                }
                break;
            }
        }
        return null;
    }
}