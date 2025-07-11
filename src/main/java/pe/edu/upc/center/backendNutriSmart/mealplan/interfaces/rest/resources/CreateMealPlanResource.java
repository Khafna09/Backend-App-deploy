package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources;

import java.util.List;

public record CreateMealPlanResource(
        String name,
        String description,
        Float calories,
        Float carbs,
        Float proteins,
        Float fats,
        Integer profileId,
        String category,
        Boolean isCurrent,
        List<CreateMealPlanEntryResource> entries,
        List<String> tags
) {
}
