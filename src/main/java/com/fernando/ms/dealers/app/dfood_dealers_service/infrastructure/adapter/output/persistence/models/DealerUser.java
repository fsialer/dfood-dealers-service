package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "dealer_user")
public class DealerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long userId;
    @OneToOne
    @JoinColumn(name = "dealer_id", nullable = false)
    private DealerEntity dealer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DealerUser that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
