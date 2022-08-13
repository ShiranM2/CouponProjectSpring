package com.poalim.mv.couponsystemspring.beans;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany
    private List<Coupon> coupons = new ArrayList<>();
}