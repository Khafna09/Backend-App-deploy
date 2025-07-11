package pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.MealPlanMacros;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.UserProfileId;

import java.util.List;

public record UpdateMealPlanCommand(
        int id,
        String name,
        String description,
        MealPlanMacros macros,
        UserProfileId profileId,
        String category,
        boolean isCurrent,
        List<UpdateMealPlanEntryCommand> entries,
        List<String> tags
) {
}
