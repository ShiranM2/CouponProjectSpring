package com.poalim.mv.couponsystemspring;

import com.poalim.mv.couponsystemspring.exceptions.CouponSystemException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CouponSystemApplication {

		public static void main(String[] args) throws CouponSystemException {
		SpringApplication.run(CouponSystemApplication.class, args);
	}
}