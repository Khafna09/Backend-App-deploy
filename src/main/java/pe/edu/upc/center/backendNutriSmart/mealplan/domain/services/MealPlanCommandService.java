package pe.edu.upc.center.backendNutriSmart.mealplan.domain.services;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.aggregates.MealPlan;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.DeleteMealPlanCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.UpdateMealPlanCommand;

import java.util.Optional;

public interface MealPlanCommandService {
    Optional<MealPlan> handle(CreateMealPlanCommand command);
    Optional<MealPlan> handle(UpdateMealPlanCommand command);
    void handle(DeleteMealPlanCommand command);
}
