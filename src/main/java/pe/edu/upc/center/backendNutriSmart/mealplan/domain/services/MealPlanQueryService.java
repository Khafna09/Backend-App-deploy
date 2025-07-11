package pe.edu.upc.center.backendNutriSmart.mealplan.domain.services;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.aggregates.MealPlan;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.queries.GetAllMealPlanByProfileIdQuery;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.queries.GetAllMealPlanQuery;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.queries.GetEntriesWithRecipeInfo;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.queries.GetMealPlanByIdQuery;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanEntryDetailedResource;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;

import java.util.List;
import java.util.Optional;

public interface MealPlanQueryService {
    Optional<MealPlan> handle(GetMealPlanByIdQuery query);
    List<MealPlan> handle(GetAllMealPlanQuery query);
    List<MealPlan> handle(GetAllMealPlanByProfileIdQuery query);
    List<MealPlanEntryDetailedResource> handle(GetEntriesWithRecipeInfo query);
    List<RecipeResource> handle(GetAllRecipesQuery query);
}
