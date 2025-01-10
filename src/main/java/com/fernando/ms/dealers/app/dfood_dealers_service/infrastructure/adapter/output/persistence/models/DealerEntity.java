package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dealers")
public class DealerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String phone;
    private String numberLicense;
    private String numberDocument;
    private LocalDate expirationDateLicense;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne(mappedBy = "dealer", cascade = CascadeType.ALL, orphanRemoval = true)
    private DealerUser dealerUser;

    public void setDealerUserId(Long userId){
        this.dealerUser = DealerUser
                .builder()
                .userId(userId)
                .dealer(this)
                .build();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}
