package com.fernando.ms.dealers.app.dfood_dealers_service.utils;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.CreateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TestUtilDealer {

    public static Dealer buildDealerMock(){
        return Dealer.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .phone("123456789")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense("2025-06-01")
                .build();
    }

    public static DealerEntity buildDealerEntityMock(){
        return DealerEntity.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .phone("123456789")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense(LocalDate.of(2025,6,1))
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static DealerResponse buildDealerResponseMock(){
        return DealerResponse.builder()
                .id(1L)
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .phone("123456789")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense(LocalDate.of(2025,6,1))
                .build();
    }

    public static CreateDealerRequest buildCreateDealerRequestMock(){
        return CreateDealerRequest.builder()
                .name("Dealer 1")
                .lastName("Dealer 2")
                .email("dealer@gmail.com")
                .phone("123456789")
                .numberLicense("452156485456")
                .numberDocument("46821744")
                .expirationDateLicense("2025-06-01")
                .build();
    }




}
