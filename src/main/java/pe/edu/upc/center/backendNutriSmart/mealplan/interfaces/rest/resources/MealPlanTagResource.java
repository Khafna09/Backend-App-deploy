package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources;

public record MealPlanTagResource(
        int id,
        String tag,
        int mealPlanId
) {
}
