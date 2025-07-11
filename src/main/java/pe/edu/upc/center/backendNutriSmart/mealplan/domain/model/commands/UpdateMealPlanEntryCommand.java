package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.RecipeId;

public record UpdateMealPlanEntryCommand(
        int id,
        RecipeId recipeId,
        int day,
        int mealPlanTypeId,
        int mealPlanId
) {
}
