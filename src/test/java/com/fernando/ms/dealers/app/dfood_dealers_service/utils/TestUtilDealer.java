package com.fernando.ms.dealers.app.dfood_dealers_service.utils;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;

import java.time.LocalDate;

public class TestUtilDealer {

    public static Dealer buildDealerMock(){
        return Dealer.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense(LocalDate.now())
                .build();
    }

    public static DealerEntity buildDealerEntityMock(){
        return DealerEntity.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense(LocalDate.now())
                .createdAt(LocalDate.now())
                .build();
    }

    public static DealerResponse buildDealerResponseMock(){
        return DealerResponse.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense(LocalDate.now())
                .build();
    }

}
