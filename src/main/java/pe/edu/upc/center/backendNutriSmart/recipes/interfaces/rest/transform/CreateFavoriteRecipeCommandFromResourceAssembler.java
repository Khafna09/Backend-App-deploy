package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateFavoriteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateFavoriteRecipeResource;

public class CreateFavoriteRecipeCommandFromResourceAssembler {
    public static CreateFavoriteRecipeCommand toCommandFromResource(CreateFavoriteRecipeResource resource) {
        return new CreateFavoriteRecipeCommand(resource.userId(), resource.recipeId());
    }
}
