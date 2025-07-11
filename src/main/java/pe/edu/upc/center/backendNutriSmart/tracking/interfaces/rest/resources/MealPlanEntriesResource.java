package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;

public record MealPlanEntriesResource(Long id, Long recipeId, String mealPlanType, int dayNumber) {
}
