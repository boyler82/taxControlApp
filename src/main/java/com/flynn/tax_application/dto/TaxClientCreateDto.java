package com.flynn.tax_application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxClientCreateDto {
    private String name;
    private String nip;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phoneNumber;
    private String mail;
}