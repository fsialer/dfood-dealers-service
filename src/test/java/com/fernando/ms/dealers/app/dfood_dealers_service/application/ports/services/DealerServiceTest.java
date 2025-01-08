package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.services;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output.DealerPersistencePort;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.services.DealerService;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.exceptions.DealerNotFoundException;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DealerServiceTest {
    @Mock
    private DealerPersistencePort dealerPersistencePort;

    @InjectMocks
    private DealerService dealerService;

    @Test
    @DisplayName("When Dealer Information Exists Expect A List Information Dealers")
    void When_DealerInformationExists_Expect_AListInformationDealers(){
        Dealer dealer= TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findAll()).thenReturn(Collections.singletonList(dealer));
        List<Dealer> dealers=dealerService.findAll();
        assertEquals(1,dealers.size());
        Mockito.verify(dealerPersistencePort,times(1)).findAll();
    }

    @Test
    @DisplayName("When Dealer Information Not Exists Expect A List Void")
    void When_DealerInformationNotExists_Expect_AListVoid(){
        Dealer dealer= TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findAll()).thenReturn(Collections.emptyList());
        List<Dealer> dealers=dealerService.findAll();
        assertEquals(0,dealers.size());
        Mockito.verify(dealerPersistencePort,times(1)).findAll();
    }

    @Test
    @DisplayName("When Dealer Information By Identifier Is Correct Expect Dealer Information Correct")
    void When_DealerInformationByIdentifierIsCorrect_Expect_DealerInformationCorrect(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.of(dealer));
        Dealer dealerResponse=dealerService.findById(1L);
        assertNotNull(dealerResponse);
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("Expect DealerNotFoundException When Dealer Information By Identifier Is Incorrect")
    void Expect_OrderNotFoundException_When_DealerInformationByIdentifierIsIncorrect(){
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DealerNotFoundException.class,()->dealerService.findById(1L));
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Correct")
    void When_DealerInformationIsCorrect_Expect_DealerInformationCorrect(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.save(dealer)).thenReturn(dealer);
        Dealer dealerResponse=dealerService.save(dealer);
        assertNotNull(dealerResponse);
        Mockito.verify(dealerPersistencePort,times(1)).save(dealer);
    }

    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Update Correct")
    void When_DealerInformationIsCorrect_Expect_DealerInformationUpdateCorrect(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.of(dealer));
        when(dealerPersistencePort.save(dealer)).thenReturn(dealer);
        Dealer dealerResponse=dealerService.update(1L,dealer);
        assertNotNull(dealerResponse);
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(1)).save(dealer);
    }

    @Test
    @DisplayName("Expect DealerNotFoundException When Dealer Identifier Is Incorrect")
    void Expect_DealerNotFoundException_When_DealerIdentifierIsIncorrect(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DealerNotFoundException.class,()->dealerService.update(1L,dealer));
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(0)).save(dealer);
    }

    @Test
    @DisplayName("When DealerIdentifier Is Correct Expect Dealer Information Delete Correct")
    void When_DealerIdentifierIsCorrect_Expect_DealerInformationDeleteCorrect(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.of(dealer));
        doNothing().when(dealerPersistencePort).delete(anyLong());
        dealerService.delete(1L);
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(1)).delete(anyLong());
    }

    @Test
    @DisplayName("Expect DealerNotFoundException When Dealer Identifier For Delete Is Incorrect")
    void Expect_DealerNotFoundException_When_DealerIdentifierForDeleteIsIncorrect(){
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DealerNotFoundException.class,()->dealerService.delete(1L));
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(0)).delete(anyLong());
    }

    @Test
    @DisplayName("When DealerIdentifier Is Correct Expect Return true")
    void When_DealerIdentifierIsCorrect_Expect_ReturnTrue(){
        Dealer dealer=TestUtilDealer.buildDealerMock();
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.of(dealer));
        doNothing().when(dealerPersistencePort).verifyExistsById(anyLong());
        dealerService.verifyExistsById(1L);
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(1)).verifyExistsById(anyLong());
    }

    @Test
    @DisplayName("Expect DealerNotFoundException When Dealer Identifier For Delete Is Incorrect")
    void Expect_DealerNotFoundException_When_DealerIdentifierForVerifyExistsByIdIsIncorrect(){
        when(dealerPersistencePort.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(DealerNotFoundException.class,()->dealerService.verifyExistsById(1L));
        Mockito.verify(dealerPersistencePort,times(1)).findById(anyLong());
        Mockito.verify(dealerPersistencePort,times(0)).verifyExistsById(anyLong());
    }



}
