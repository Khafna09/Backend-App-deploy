package pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingMealPlanEntry;

import java.util.List;

public interface TrackingMealPlanEntryRepository extends JpaRepository<TrackingMealPlanEntry, Long> {

    List<TrackingMealPlanEntry> findAllByTrackingId(Long trackingId);
}