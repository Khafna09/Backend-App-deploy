package pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    boolean existsByName(String name);
}
