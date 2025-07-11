package pe.edu.upc.center.backendNutriSmart.mealplan.domain.services;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.SeedMealPlanTypesCommand;

public interface MealPlanTypeCommandService {
    void handle(SeedMealPlanTypesCommand command);
}
