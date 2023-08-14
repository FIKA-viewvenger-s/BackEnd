package com.sideproject.fikabackend.domain.assembly.controller;

import com.sideproject.fikabackend.domain.assembly.service.AssemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Assembly", description = "모임 관련 API")
@RequiredArgsConstructor
@RestController
public class AssemController {

    private final AssemService assemService;

    @PostMapping("/assemblies")
    public ResponseEntity<?>assembly(){
        return assemService.assembly();
    }
}
