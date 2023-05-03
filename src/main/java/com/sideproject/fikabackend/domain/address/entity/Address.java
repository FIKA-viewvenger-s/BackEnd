package com.sideproject.fikabackend.domain.address.entity;

import com.sideproject.fikabackend.global.util.Timestamped;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrId;

    @NotNull(message = "address must not be null")
    private String address;

    @NotNull(message = "Latitude must not be null")
    private String Latitude;

    @NotNull(message = "LongiTude must not be null")
    private String LongiTude;


}
