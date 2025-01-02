package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dealers")
public class DealerEntity {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String numberLicense;
    private String numberDocument;
    private LocalDate expirationDateLicense;
    private LocalDate createdAt;
}
