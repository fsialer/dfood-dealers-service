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

    @Override
    public Dealer update(Long id, Dealer dealer) {
        return dealerPersistencePort.findById(id)
                .map(dealerUpdate->{
                    dealerUpdate.setName(dealer.getName());
                    dealerUpdate.setLastName(dealer.getLastName());
                    dealerUpdate.setEmail(dealer.getEmail());
                    dealerUpdate.setPhone(dealer.getPhone());
                    dealerUpdate.setNumberLicense(dealer.getNumberLicense());
                    dealerUpdate.setNumberDocument(dealer.getNumberDocument());
                    dealerUpdate.setExpirationDateLicense(dealer.getExpirationDateLicense());
                    return dealerPersistencePort.save(dealerUpdate);
                })
                .orElseThrow(DealerNotFoundException::new);
    }

    @Override
    public void delete(Long id) {
        if (!dealerPersistencePort.findById(id).isPresent()){
            throw new DealerNotFoundException();
        }

        dealerPersistencePort.delete(id);
    }


}
