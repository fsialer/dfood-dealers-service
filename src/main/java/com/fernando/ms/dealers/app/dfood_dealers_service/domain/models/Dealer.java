package com.fernando.ms.dealers.app.dfood_dealers_service.domain.models;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dealer {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String numberLicense;
    private String numberDocument;
    private LocalDate expirationDateLicense;
}
