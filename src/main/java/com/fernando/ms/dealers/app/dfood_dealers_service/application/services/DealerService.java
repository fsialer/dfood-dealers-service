package com.fernando.ms.dealers.app.dfood_dealers_service.application.services;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output.DealerPersistencePort;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.exceptions.DealerNotFoundException;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DealerService implements DealerInputPort {

    private final DealerPersistencePort dealerPersistencePort;

    @Override
    public List<Dealer> findAll() {
        return dealerPersistencePort.findAll();
    }

    @Override
    public Dealer findById(Long id) {
        return dealerPersistencePort.findById(id).orElseThrow(DealerNotFoundException::new);
    }

    @Override
    public Dealer save(Dealer dealer) {
        return dealerPersistencePort.save(dealer);
    }
}
