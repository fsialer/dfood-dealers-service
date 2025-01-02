package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.mapper;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.CreateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Mapper(componentModel = "spring")
public interface DealerPersistenceMapper {
    List<Dealer> toDealers(List<DealerEntity> dealers);
    Dealer toDealer(DealerEntity dealer);

    @Mapping(target = "expirationDateLicense", expression = "java(mapExpirationDateLicense(dealer))")
    @Mapping(target = "createdAt", expression = "java(mapCreatedAt())")
    DealerEntity toDealerEntity(Dealer dealer);

    default LocalDate mapExpirationDateLicense(Dealer dealer){
        return LocalDate.parse(dealer.getExpirationDateLicense());
    }

    default LocalDateTime mapCreatedAt(){
        return LocalDateTime.now();
    }
}
