package com.monkilatech.appmarket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    private String username;

    @JsonInclude(Include.NON_EMPTY)
    private String password;

    @JsonInclude(Include.NON_EMPTY)
    private String phoneNumber;

    @JsonInclude(Include.NON_EMPTY)
    private String email;

    @JsonInclude(Include.NON_EMPTY)
    private String photo;

    @JsonInclude(Include.NON_EMPTY)
    private String typeCompte;
}
