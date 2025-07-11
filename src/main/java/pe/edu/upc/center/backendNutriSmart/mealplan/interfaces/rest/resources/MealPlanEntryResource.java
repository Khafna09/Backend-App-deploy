package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources;

public record MealPlanEntryResource(
        int id,
        int recipeId,
        int day,
        int mealPlanType,
        int mealPlanId
) {
}
