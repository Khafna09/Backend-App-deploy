package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.UpdateMealPlanEntryCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.RecipeId;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanEntryResource;

public class UpdateMealPlanEntryCommandFromResourceAssembler {
    public static UpdateMealPlanEntryCommand toCommandFromResource(MealPlanEntryResource resource) {
        return new UpdateMealPlanEntryCommand(
            resource.id(),
                new RecipeId(resource.recipeId()),
                resource.mealPlanType(),
                resource.day(),
                resource.mealPlanId()
        );
    }
}
