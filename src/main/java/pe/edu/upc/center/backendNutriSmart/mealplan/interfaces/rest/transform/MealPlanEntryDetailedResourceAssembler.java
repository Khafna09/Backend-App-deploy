package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.entities.MealPlanEntry;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanEntryDetailedResource;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;

import java.util.List;

public class MealPlanEntryDetailedResourceAssembler {

    public static List<MealPlanEntryDetailedResource> toDetailedResourcesFromEntities(
            List<MealPlanEntry> entries,
            java.util.function.Function<Integer, RecipeResource> fetchRecipe
    ) {
        return entries.stream().map(entry -> {
            var recipe = fetchRecipe.apply(entry.getRecipeId().recipeId());
            return new MealPlanEntryDetailedResource(
                    entry.getId(),
                    entry.getRecipeId().recipeId(),
                    recipe.name(),
                    recipe.description(),
                    entry.getDay(),
                    entry.getMealPlanType().getId(),
                    entry.getMealPlan().getId()
            );
        }).toList();
    }
}
