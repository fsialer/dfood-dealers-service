package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.application.ports.input.DealerInputPort;
import com.fernando.ms.dealers.app.dfood_dealers_service.domain.exceptions.DealerNotFoundException;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.mapper.DealerRestMapper;
import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.response.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.enums.ErrorType.FUNCTIONAL;
import static com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.input.rest.models.enums.ErrorType.SYSTEM;
import static com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.utils.ErrorCatalog.DEALER_NOT_FOUND;
import static com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.utils.ErrorCatalog.INTERNAL_SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealerRestAdapter.class)
public class GlobalControllerAdviceTest {

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
    @DisplayName("Expect DealerNotFoundException When Dealer Identifier Is Unknown")
    void Expect_DealerNotFoundException_When_DealerIdentifierIsUnknown() throws Exception {
        when(dealerInputPort.findById(anyLong()))
                .thenThrow(new DealerNotFoundException());
        mockMvc.perform(get("/dealers/{id}",2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class);
                    assertAll(
                            ()->assertEquals(DEALER_NOT_FOUND.getCode(),errorResponse.getCode()),
                            ()->assertEquals(FUNCTIONAL,errorResponse.getType()),
                            ()->assertEquals(DEALER_NOT_FOUND.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });
    }

    @Test
    void whenThrowsGenericExceptionThenReturnInternalServerErrorResponse() throws Exception {
        when(dealerInputPort.findAll())
                .thenThrow(new RuntimeException("Generic error"));
        mockMvc.perform(post("/dealers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(result->{
                    ErrorResponse errorResponse=objectMapper.readValue(
                            result.getResponse().getContentAsString(), ErrorResponse.class
                    );

                    assertAll(
                            ()->assertEquals(INTERNAL_SERVER_ERROR.getCode(),errorResponse.getCode()),
                            ()->assertEquals(SYSTEM,errorResponse.getType()),
                            ()->assertEquals(INTERNAL_SERVER_ERROR.getMessage(),errorResponse.getMessage()),
                            ()->assertNotNull(errorResponse.getDetails()),
                            ()->assertNotNull(errorResponse.getTimestamp())
                    );
                });
    }

}
