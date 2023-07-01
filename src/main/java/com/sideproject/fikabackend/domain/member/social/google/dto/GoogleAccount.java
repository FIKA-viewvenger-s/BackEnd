package com.sideproject.fikabackend.domain.member.social.google.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class GoogleAccount {
    private String name;
    private String email;
    private String picture;
}
