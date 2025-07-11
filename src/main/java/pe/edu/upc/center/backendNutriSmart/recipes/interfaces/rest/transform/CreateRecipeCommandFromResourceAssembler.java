package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateRecipeResource;

public class CreateRecipeCommandFromResourceAssembler {

    public static CreateRecipeCommand toCommandFromResource(CreateRecipeResource resource) {
        return new CreateRecipeCommand(
                resource.userId(),
                resource.name(),
                resource.description(),
                resource.preparationTime(),
                resource.difficulty(),
                resource.category(),
                resource.recipeType()
        );
    }
}
