package pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.TrackingGoal;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.aggregates.Tracking;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingGoalRepository extends JpaRepository<TrackingGoal, Long> {
    Optional<TrackingGoal> findByUserId(UserId userId);
    boolean existsByUserId(UserId userId);
    //Optional<MacronutrientValues> findTargetMacrosByTrackingGoalId(Long trackingGoalId);
}
