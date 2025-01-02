package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.mapper.DealerPersistenceMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.repository.DealerJpaRepository;
import com.fernando.ms.dealers.app.dfood_dealers_service.utils.TestUtilDealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealerPersistenceAdapterTest {
    @Mock
    private DealerJpaRepository dealerJpaRepository;

    @Mock
    private DealerPersistenceMapper dealerPersistenceMapper;

    @InjectMocks
    private DealerPersistenceAdapter dealerPersistenceAdapter;

    @Test
    @DisplayName("When Dealer Information Exists Expect_AListDealersAvailability")
    void When_DealerInformationExists_Expect_AListDealersAvailability(){
        Dealer dealer= TestUtilDealer.buildDealerMock();
        DealerEntity dealerEntity=TestUtilDealer.buildDealerEntityMock();

        when(dealerJpaRepository.findAll()).thenReturn(Collections.singletonList(dealerEntity));
        when(dealerPersistenceMapper.toDealers(anyList())).thenReturn(Collections.singletonList(dealer));

        List<Dealer> dealers=dealerPersistenceAdapter.findAll();
        assertEquals(1,dealers.size());
        Mockito.verify(dealerJpaRepository,times(1)).findAll();
        Mockito.verify(dealerPersistenceMapper,times(1)).toDealers(anyList());
    }
}
