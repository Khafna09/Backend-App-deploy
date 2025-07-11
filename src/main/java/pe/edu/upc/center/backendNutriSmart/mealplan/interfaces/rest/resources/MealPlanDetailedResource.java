package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources;

import java.util.List;

public record MealPlanDetailedResource(
        int id,
        String name,
        String description,
        float calories,
        float carbs,
        float proteins,
        float fats,
        int profileId,
        String category,
        boolean isCurrent,
        List<MealPlanEntryDetailedResource> entries,
        List<String> tags
) {
}
