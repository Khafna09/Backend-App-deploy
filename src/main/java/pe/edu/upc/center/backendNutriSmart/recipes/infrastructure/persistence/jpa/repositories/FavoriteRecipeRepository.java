package pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities.FavoriteRecipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, Long> {
    List<FavoriteRecipe> findByUserId_UserId(Long userId);
    Optional<FavoriteRecipe> findByUserId_UserIdAndRecipe_Id(Long userId, int recipeId);
    boolean existsByUserId_UserIdAndRecipe_Id(Long userId, int recipeId);
    void deleteByUserId_UserIdAndRecipe_Id(Long userId, int recipeId);

}