package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;

import java.util.List;

public interface DealerInputPort {
    List<Dealer> findAll();
    Dealer findById(Long id);
}
   