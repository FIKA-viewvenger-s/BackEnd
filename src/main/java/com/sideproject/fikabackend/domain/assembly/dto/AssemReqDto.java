package com.sideproject.fikabackend.domain.assembly.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AssemReqDto {

    private String assmTitle;
    private String assmDt;
    private String assmAddr;
    private String assmLat;

}
