package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.UpdateRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateRecipeResource;

public class UpdateRecipeCommandFromResourceAssembler {

    public static UpdateRecipeCommand toCommandFromResource(int recipeId, CreateRecipeResource resource) {
        return new UpdateRecipeCommand(
                recipeId,
                resource.name(),
                resource.description(),
                resource.preparationTime(),
                resource.difficulty(),
                resource.category(),
                resource.recipeType()
        );
    }
}
