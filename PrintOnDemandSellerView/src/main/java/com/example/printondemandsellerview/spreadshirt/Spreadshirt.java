package com.example.printondemandsellerview.spreadshirt;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Spreadshirt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String email;
    private String password;

    public Spreadshirt(String emailSpreadshirt, String passwordSpreadshirt) {
        this.email = emailSpreadshirt;
        this.password = passwordSpreadshirt;
    }

}
