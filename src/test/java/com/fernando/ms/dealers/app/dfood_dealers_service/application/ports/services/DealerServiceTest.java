package com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.services;

import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.output.DealerPersistencePort;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.services.DealerService;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DealerServiceTest {
    @Mock
    private DealerInputPort dealerInputPort;
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

}
