package com.sideproject.fikabackend.domain.address.entity;

import com.sideproject.fikabackend.global.util.Timestamped;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Address extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addrId;

    /* 주소 */
    @NotNull(message = "address must not be null")
    private String address;

    /* 위도(가로축) */
    @NotNull(message = "Latitude must not be null")
    private String Latitude;

    /* 경도(세로축) */
    @NotNull(message = "LongiTude must not be null")
    private String Longitude;


}
