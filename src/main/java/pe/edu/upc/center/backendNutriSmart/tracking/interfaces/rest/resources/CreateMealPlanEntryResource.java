package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;

public record CreateMealPlanEntryResource(
        Long userId,
        Long recipeId,
        String mealPlanType,
        int dayNumber
) {}