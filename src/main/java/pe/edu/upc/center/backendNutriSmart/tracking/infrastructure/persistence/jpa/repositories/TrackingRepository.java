package pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories;



import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.aggregates.Tracking;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
    Optional<Tracking> findByUserId(UserId userId);
    boolean existsByUserId(UserId userId);
}
