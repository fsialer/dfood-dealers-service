package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;

import java.util.List;

public interface DealerInputPort {
    List<Dealer> findAll();
    Dealer findById(Long id);
    Dealer save(Dealer dealer);
    Dealer update(Long id,Dealer dealer);
    void delete(Long id);
    void verifyExistsById(Long id);
}