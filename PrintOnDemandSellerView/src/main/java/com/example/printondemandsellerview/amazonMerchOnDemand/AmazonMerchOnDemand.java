package com.example.printondemandsellerview.amazonMerchOnDemand;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class AmazonMerchOnDemand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String password;

    public AmazonMerchOnDemand(String emailAmazon, String passwordAmazon) {
        this.email = emailAmazon;
        this.password = passwordAmazon;
    }
}
