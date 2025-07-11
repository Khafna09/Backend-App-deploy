package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Ingredient;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;

import java.util.List;
import java.util.stream.Collectors;

public class RecipeResourceFromEntityAssembler {

    public static RecipeResource toResourceFromEntity(Recipe recipe) {
        List<String> ingredientNames = recipe.getIngredients()
                .stream()
                .map(Ingredient::getName)
                .toList();

        return new RecipeResource(
                recipe.getId(),
                recipe.getUserId(),
                recipe.getName(),
                recipe.getDescription(),
                recipe.getPreparationTime(),
                recipe.getDifficulty(),
                recipe.getCategory().getName(),
                recipe.getRecipeType().getName(),
                ingredientNames
        );
    }


    public static List<RecipeResource> toResources(List<Recipe> recipes) {
        return recipes.stream()
                .map(RecipeResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}
