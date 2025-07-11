package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.AddIngredientToRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.UpdateRecipeCommand;

import java.util.Optional;

public interface RecipeCommandService {
    int handle(CreateRecipeCommand command);
    Optional<Recipe> handle(UpdateRecipeCommand command);
    void handle(DeleteRecipeCommand command);

    void handle(AddIngredientToRecipeCommand command);
}
