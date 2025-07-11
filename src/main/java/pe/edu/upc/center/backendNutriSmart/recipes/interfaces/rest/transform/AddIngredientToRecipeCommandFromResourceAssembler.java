package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;


import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.AddIngredientToRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.AddIngredientToRecipeResource;

public class AddIngredientToRecipeCommandFromResourceAssembler {

    public static AddIngredientToRecipeCommand toCommand(int recipeId, AddIngredientToRecipeResource resource) {
        return new AddIngredientToRecipeCommand(recipeId, resource.ingredientId());
    }
}
