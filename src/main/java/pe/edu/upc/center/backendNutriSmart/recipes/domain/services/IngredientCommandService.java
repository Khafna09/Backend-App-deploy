package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateIngredientCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.DeleteIngredientCommand;

public interface IngredientCommandService {
    Integer handle(CreateIngredientCommand command);
    void handle(DeleteIngredientCommand command);
}
