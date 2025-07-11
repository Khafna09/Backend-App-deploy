package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.MealPlanMacros;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.UserProfileId;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanEntryResource;

import java.util.List;

public record CreateMealPlanCommand(
        String name,
        String description,
        MealPlanMacros macros,
        UserProfileId profileId,
        String category,
        Boolean isCurrent,
        List<CreateMealPlanEntryCommand> entries,
        List<String> tags
) {
}
