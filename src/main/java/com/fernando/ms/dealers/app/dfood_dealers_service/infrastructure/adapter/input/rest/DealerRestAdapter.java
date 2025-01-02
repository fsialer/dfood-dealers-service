package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper.DealerRestMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dealers")
public class DealerRestAdapter {

    private final DealerInputPort dealerInputPort;
    private final DealerRestMapper dealerRestMapper;

    @GetMapping
    public ResponseEntity<List<DealerResponse>> findAll(){
        return ResponseEntity.ok().body(dealerRestMapper.toDealersResponse(dealerInputPort.findAll()));
    }
}
