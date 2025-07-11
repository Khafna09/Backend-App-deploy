package pe.edu.upc.center.backendNutriSmart.mealplan.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.entities.MealPlanTag;

@Repository
public interface MealPlanTagRepository extends JpaRepository<MealPlanTag, Integer> {
}
