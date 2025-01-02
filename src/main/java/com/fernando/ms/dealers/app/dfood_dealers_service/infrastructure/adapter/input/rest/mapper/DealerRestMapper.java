package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealerRestMapper {
    List<DealerResponse> toDealersResponse(List<Dealer> dealers);
    DealerResponse toDealerResponse(Dealer dealer);
}
