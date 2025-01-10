package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper;

import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.User;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.CreateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.UpdateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealerRestMapper {
    List<DealerResponse> toDealersResponse(List<Dealer> dealers);
    DealerResponse toDealerResponse(Dealer dealer);
    @Mapping(target = "user", expression = "java(mapDealer(rq))")
    Dealer toDealer(CreateDealerRequest rq);

    Dealer toDealer(UpdateDealerRequest rq);

    default User mapDealer(CreateDealerRequest rq){
        return User.builder().id(rq.getUserId()).build();
    }
}
