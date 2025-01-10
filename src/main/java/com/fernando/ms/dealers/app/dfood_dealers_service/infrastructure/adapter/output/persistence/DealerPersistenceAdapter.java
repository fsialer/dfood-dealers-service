package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output.DealerPersistencePort;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.mapper.DealerPersistenceMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.repository.DealerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DealerPersistenceAdapter implements DealerPersistencePort {

    private final DealerJpaRepository dealerJpaRepository;
    private final DealerPersistenceMapper dealerPersistenceMapper;

    @Override
    public List<Dealer> findAll() {
        return dealerPersistenceMapper.toDealers(dealerJpaRepository.findAll());
    }

    @Override
    public Optional<Dealer> findById(Long id) {
        return dealerJpaRepository.findById(id).map(dealerPersistenceMapper::toDealer);
    }

    @Override
    public Dealer save(Dealer dealer) {
        DealerEntity dealerEntity=dealerPersistenceMapper.toDealerEntity(dealer);

        if(dealer.getId()!=null){
            DealerEntity dealerEntity2= dealerJpaRepository.findById(dealer.getId()).get();
            dealerEntity.setDealerUser(dealerEntity2.getDealerUser());
        }else{
            dealerEntity.setDealerUserId(dealer.getUser().getId());
        }

        return dealerPersistenceMapper.toDealer(dealerJpaRepository.save(dealerEntity));
    }

    @Override
    public void delete(Long id) {
        dealerJpaRepository.deleteById(id);
    }

    @Override
    public void verifyExistsById(Long id) {
        dealerJpaRepository.existsById(id);
    }
}
