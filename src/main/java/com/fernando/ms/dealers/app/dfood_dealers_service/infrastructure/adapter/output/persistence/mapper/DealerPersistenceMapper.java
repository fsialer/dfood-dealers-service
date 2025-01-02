package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.mapper;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealerPersistenceMapper {
    List<Dealer> toDealers(List<DealerEntity> dealers);
    Dealer toDealer(DealerEntity dealer);
}
