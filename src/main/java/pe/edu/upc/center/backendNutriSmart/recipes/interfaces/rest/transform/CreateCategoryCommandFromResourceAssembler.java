package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateCategoryCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateCategoryResource;

public class CreateCategoryCommandFromResourceAssembler {

    public static CreateCategoryCommand toCommandFromResource(CreateCategoryResource resource) {
        return new CreateCategoryCommand(resource.name());
    }
}
