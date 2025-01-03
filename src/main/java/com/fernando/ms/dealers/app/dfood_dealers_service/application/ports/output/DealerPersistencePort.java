package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;

import java.util.List;
import java.util.Optional;

public interface DealerPersistencePort {
    List<Dealer> findAll();
    Optional<Dealer> findById(Long id);
    Dealer save(Dealer dealer);
    void delete(Long id);
}
