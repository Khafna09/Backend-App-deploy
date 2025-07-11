package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;

public record UpdateMealPlanEntryResource(
        Long trackingId,
        Long recipeId,
        String mealPlanType,
        int dayNumber
) {}