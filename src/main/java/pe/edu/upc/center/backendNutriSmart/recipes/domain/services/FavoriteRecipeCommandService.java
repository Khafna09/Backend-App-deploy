package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateFavoriteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.DeleteFavoriteRecipeCommand;

public interface FavoriteRecipeCommandService {
    Long handle(CreateFavoriteRecipeCommand command);
    void handle(DeleteFavoriteRecipeCommand command);
}
