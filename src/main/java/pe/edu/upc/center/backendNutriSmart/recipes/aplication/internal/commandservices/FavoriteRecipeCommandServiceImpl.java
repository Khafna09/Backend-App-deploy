package pe.edu.upc.center.backendNutriSmart.recipes.aplication.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateFavoriteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.DeleteFavoriteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities.FavoriteRecipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.valueobjects.UserId;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.FavoriteRecipeCommandService;
import pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories.FavoriteRecipeRepository;
import pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories.RecipeRepository;

@Service
public class FavoriteRecipeCommandServiceImpl implements FavoriteRecipeCommandService {

    private final FavoriteRecipeRepository favoriteRecipeRepository;
    private final RecipeRepository recipeRepository;

    public FavoriteRecipeCommandServiceImpl(FavoriteRecipeRepository favoriteRecipeRepository, RecipeRepository recipeRepository) {
        this.favoriteRecipeRepository = favoriteRecipeRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Long handle(CreateFavoriteRecipeCommand command) {
        if (favoriteRecipeRepository.existsByUserId_UserIdAndRecipe_Id(command.userId(), command.recipeId())) {
            throw new IllegalArgumentException("Recipe already marked as favorite.");
        }

        var optionalRecipe = recipeRepository.findById(command.recipeId());
        if (optionalRecipe.isEmpty()) {
            throw new IllegalArgumentException("Recipe not found.");
        }

        var favoriteRecipe = new FavoriteRecipe(new UserId(command.userId()), optionalRecipe.get());
        var savedFavorite = favoriteRecipeRepository.save(favoriteRecipe);

        return savedFavorite.getId(); // 🔥 Retorna el ID generado
    }

    @Override
    public void handle(DeleteFavoriteRecipeCommand command) {
        if (!favoriteRecipeRepository.existsByUserId_UserIdAndRecipe_Id(command.userId(), command.recipeId())) {
            throw new IllegalArgumentException("Favorite recipe not found.");
        }
        favoriteRecipeRepository.deleteByUserId_UserIdAndRecipe_Id(command.userId(), command.recipeId());
    }
}