package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;

import java.util.List;

public interface DealerPersistencePort {
    List<Dealer> findAll();
}
