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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealerPersistenceAdapterTest {
    @Mock
    private DealerJpaRepository dealerJpaRepository;

    @Mock
    private DealerPersistenceMapper dealerPersistenceMapper;

    @InjectMocks
    private DealerPersistenceAdapter dealerPersistenceAdapter;

    @Test
    @DisplayName("When Dealer Information Exists Expect A List Dealers Availability")
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

    @Test
    @DisplayName("When Dealer Information No Exists Expect A List Void")
    void When_DealerInformationNotExists_Expect_AListVoid(){
        Dealer dealer= TestUtilDealer.buildDealerMock();


        when(dealerJpaRepository.findAll()).thenReturn(Collections.emptyList());
        when(dealerPersistenceMapper.toDealers(anyList())).thenReturn(Collections.emptyList());

        List<Dealer> dealers=dealerPersistenceAdapter.findAll();
        assertEquals(0,dealers.size());
        Mockito.verify(dealerJpaRepository,times(1)).findAll();
        Mockito.verify(dealerPersistenceMapper,times(1)).toDealers(anyList());
    }


    @Test
    @DisplayName("When Dealer Identifier Is Correct Expect Dealer Information Successfully")
    void When_DealerIdentifierIsCorrectExpectDealerInformationSuccessfully(){
        DealerEntity dealerEntity = TestUtilDealer.buildDealerEntityMock();
        Dealer dealer = TestUtilDealer.buildDealerMock();
        when(dealerJpaRepository.findById(anyLong())).thenReturn(Optional.of(dealerEntity));
        when(dealerPersistenceMapper.toDealer(any(DealerEntity.class))).thenReturn(dealer);
        Optional<Dealer> dealerResponse=dealerPersistenceAdapter.findById(1L);
        assertNotNull(dealerResponse);
        Mockito.verify(dealerJpaRepository,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistenceMapper,times(1)).toDealer(any(DealerEntity.class));
    }

    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Save Successfully")
    void When_DealerInformationIsCorrect_Expect_DealerInformationSaveSuccessfully(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        DealerEntity dealerEntity=TestUtilDealer.buildDealerEntityMock();
        when(dealerJpaRepository.save(any(DealerEntity.class))).thenReturn(dealerEntity);
        when(dealerPersistenceMapper.toDealerEntity(any(Dealer.class))).thenReturn(dealerEntity);
        when(dealerPersistenceMapper.toDealer(any(DealerEntity.class))).thenReturn(dealer);
        Dealer dealerResponse=dealerPersistenceAdapter.save(dealer);
        assertNotNull(dealerResponse);
        Mockito.verify(dealerJpaRepository,times(1)).save(any(DealerEntity.class));
        Mockito.verify(dealerPersistenceMapper,times(1)).toDealerEntity(any(Dealer.class));
        Mockito.verify(dealerPersistenceMapper,times(1)).toDealer(any(DealerEntity.class));
    }

    @Test
    @DisplayName("When DealerIdentifier Is Correct Expect Dealer Information Delete Successfully")
    void When_DealerIdentifierIsCorrect_Expect_DealerInformationDeleteSuccessfully(){
        doNothing().when(dealerJpaRepository).deleteById(anyLong());
        dealerPersistenceAdapter.delete(1L);
        Mockito.verify(dealerJpaRepository,times(1)).deleteById(anyLong());
    }

    @Test
    @DisplayName("When DealerIdentifier Is Correct Expect Dealer Verify Successfully")
    void When_DealerIdentifierIsCorrect_Expect_DealerVerifySuccessfully(){
        when(dealerJpaRepository.existsById(anyLong())).thenReturn(true);
        dealerPersistenceAdapter.verifyExistsById(1L);
        Mockito.verify(dealerJpaRepository,times(1)).existsById(anyLong());
    }
}
