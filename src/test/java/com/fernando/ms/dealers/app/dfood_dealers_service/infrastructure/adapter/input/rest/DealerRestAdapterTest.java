package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper.DealerRestMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import com.fernando.ms.dealers.app.dfood_dealers_service.utils.TestUtilDealer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealerRestAdapter.class)
public class DealerRestAdapterTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    private DealerInputPort dealerInputPort;

    @MockBean
    private DealerRestMapper dealerRestMapper;

    private ObjectMapper objectMapper;

    @Test
    @DisplayName("When Orders Are Availability Expect Customers Information Successfully")
    void When_DealersAreAvailability_Expect_DealersInformationSuccessfully() throws Exception {

        Dealer dealer = TestUtilDealer.buildDealerMock();
        List<DealerResponse> dealerResponses= Collections.singletonList(TestUtilDealer.buildDealerResponseMock());

        when(dealerInputPort.findAll())
                .thenReturn(Collections.singletonList(dealer));

        when(dealerRestMapper.toDealersResponse(anyList()))
                .thenReturn(dealerResponses);

        mockMvc.perform(get("/dealers").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.length()").value(1))
                .andDo(print());

        Mockito.verify(dealerInputPort,times(1)).findAll();
        Mockito.verify(dealerRestMapper,times(1)).toDealersResponse(anyList());
    }


}
