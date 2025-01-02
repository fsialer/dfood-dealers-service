package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper.DealerRestMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.CreateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{id}")
    public ResponseEntity<DealerResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(dealerRestMapper.toDealerResponse(dealerInputPort.findById(id)));
    }

    @PostMapping
    public ResponseEntity<DealerResponse> save(@Valid @RequestBody CreateDealerRequest rq){
        DealerResponse response=dealerRestMapper.toDealerResponse(dealerInputPort.save(dealerRestMapper.toDealer(rq)));
        return ResponseEntity.created(URI.create("/dealers/".concat(response.getId().toString()))).body(response);
    }
}
