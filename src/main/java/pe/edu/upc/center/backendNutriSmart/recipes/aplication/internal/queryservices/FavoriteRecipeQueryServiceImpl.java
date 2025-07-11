package pe.edu.upc.center.backendNutriSmart.recipes.aplication.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities.FavoriteRecipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllFavoriteRecipesByUserIdQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetFavoriteRecipeByUserIdAndRecipeIdQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.FavoriteRecipeQueryService;
import pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories.FavoriteRecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class FavoriteRecipeQueryServiceImpl implements FavoriteRecipeQueryService {

    private final FavoriteRecipeRepository favoriteRecipeRepository;

    public FavoriteRecipeQueryServiceImpl(FavoriteRecipeRepository favoriteRecipeRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
    }

    @Override
    public Optional<FavoriteRecipe> handle(GetFavoriteRecipeByUserIdAndRecipeIdQuery query) {
        return favoriteRecipeRepository.findByUserId_UserIdAndRecipe_Id(query.userId(), query.recipeId());
    }

    @Override
    public List<FavoriteRecipe> handle(GetAllFavoriteRecipesByUserIdQuery query) {
        return favoriteRecipeRepository.findByUserId_UserId(query.userId());
    }
}
