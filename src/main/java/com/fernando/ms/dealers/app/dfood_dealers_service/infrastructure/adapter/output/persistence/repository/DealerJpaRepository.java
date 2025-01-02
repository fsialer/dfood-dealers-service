package com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.repository;

import com.fernando.ms.dealers.app.dfood_dealers_service.infrastructure.adapter.output.persistence.models.DealerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DealerJpaRepository extends JpaRepository<DealerEntity, Long> {
}
