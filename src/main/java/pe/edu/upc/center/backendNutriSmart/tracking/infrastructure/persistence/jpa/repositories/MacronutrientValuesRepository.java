package pe.edu.upc.center.backendNutriSmart.tracking.infrastructure.persistence.jpa.repositories;


import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MacronutrientValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MacronutrientValuesRepository extends JpaRepository<MacronutrientValues, Long> {
    //Consumed Macros
    //Target Macros
    //Optional<MacronutrientValues> findTargetMacrosByTrackingGoalId(@Param("trackingGoalId") Long trackingGoalId);

    boolean existsByCaloriesAndCarbsAndProteinsAndFats(double calories, double carbs, double proteins, double fats);
}
