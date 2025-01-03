package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.models.Dealer;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper.DealerRestMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.requests.CreateDealerRequest;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.DealerResponse;
import com.fernando.ms.dealers.app.dfood_dealers_service.utils.TestUtilDealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        objectMapper=new ObjectMapper();
    }

    @Test
    @DisplayName("When Dealers Are Availability Expect Dealers Information Successfully")
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

    @Test
    @DisplayName("When Dealer Are Availability Expect Dealers Information Successfully")
    void When_DealerAreAvailability_Expect_DealerInformationSuccessfully() throws Exception {

        Dealer dealer = TestUtilDealer.buildDealerMock();
        DealerResponse dealerResponse= TestUtilDealer.buildDealerResponseMock();

        when(dealerInputPort.findById(anyLong()))
                .thenReturn(dealer);

        when(dealerRestMapper.toDealerResponse(any(Dealer.class)))
                .thenReturn(dealerResponse);

        mockMvc.perform(get("/dealers/{id}",1L).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andDo(print());

        Mockito.verify(dealerInputPort,times(1)).findById(anyLong());
        Mockito.verify(dealerRestMapper,times(1)).toDealerResponse(any(Dealer.class));
    }


    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Save Successfully")
    void When_DealerInformationIsCorrect_Expect_DealerInformationSaveSuccessfully() throws Exception {
        DealerResponse productResponse=TestUtilDealer.buildDealerResponseMock();
        Dealer dealer=TestUtilDealer.buildDealerMock();
        CreateDealerRequest rq=TestUtilDealer.buildCreateDealerRequestMock();
        when(dealerInputPort.save(any(Dealer.class)))
                .thenReturn(dealer);
        when(dealerRestMapper.toDealerResponse(any(Dealer.class)))
                .thenReturn(productResponse);
        when(dealerRestMapper.toDealer(any(CreateDealerRequest.class)))
                .thenReturn(dealer);


        mockMvc.perform(post("/dealers")
                        .content( objectMapper.writeValueAsString(rq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
        Mockito.verify(dealerInputPort,times(1)).save((any(Dealer.class)));
        Mockito.verify(dealerRestMapper,times(1)).toDealerResponse(any(Dealer.class));
        Mockito.verify(dealerRestMapper,times(1)).toDealer(any(CreateDealerRequest.class));
    }

    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Updated Successfully")
    void When_DealerInformationIsCorrect_Expect_DealerInformationUpdatedSuccessfully() throws Exception {
        DealerResponse productResponse=TestUtilDealer.buildDealerResponseMock();
        Dealer dealer=TestUtilDealer.buildDealerMock();
        CreateDealerRequest rq=TestUtilDealer.buildCreateDealerRequestMock();
        when(dealerInputPort.update(anyLong(),any(Dealer.class)))
                .thenReturn(dealer);
        when(dealerRestMapper.toDealerResponse(any(Dealer.class)))
                .thenReturn(productResponse);
        when(dealerRestMapper.toDealer(any(CreateDealerRequest.class)))
                .thenReturn(dealer);


        mockMvc.perform(put("/dealers/{id}",1L)
                        .content( objectMapper.writeValueAsString(rq))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
        Mockito.verify(dealerInputPort,times(1)).update(anyLong(),(any(Dealer.class)));
        Mockito.verify(dealerRestMapper,times(1)).toDealerResponse(any(Dealer.class));
        Mockito.verify(dealerRestMapper,times(1)).toDealer(any(CreateDealerRequest.class));
    }

    @Test
    @DisplayName("When Dealer Information Is Correct Expect Dealer Information Deleted Successfully")
    void When_DealerInformationIsCorrect_Expect_DealerInformationDeletedSuccessfully() throws Exception {

        doNothing().when(dealerInputPort).delete(anyLong());
        mockMvc.perform(delete("/dealers/{id}",1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        Mockito.verify(dealerInputPort,times(1)).delete(anyLong());
    }



}
