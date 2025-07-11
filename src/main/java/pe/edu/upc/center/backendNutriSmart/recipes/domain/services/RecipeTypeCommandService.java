package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateRecipeTypeCommand;

public interface RecipeTypeCommandService {
    Long handle(CreateRecipeTypeCommand command);
}
