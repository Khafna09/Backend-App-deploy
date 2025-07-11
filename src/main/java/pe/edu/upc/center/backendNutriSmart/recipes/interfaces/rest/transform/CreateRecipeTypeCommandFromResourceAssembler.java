package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateRecipeTypeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateRecipeTypeResource;

public class CreateRecipeTypeCommandFromResourceAssembler {

    public static CreateRecipeTypeCommand toCommandFromResource(CreateRecipeTypeResource resource) {
        return new CreateRecipeTypeCommand(resource.name());
    }
}
