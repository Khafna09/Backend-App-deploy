package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.CreateMealPlanCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.commands.CreateMealPlanEntryCommand;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.MealPlanMacros;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.MealPlanTags;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.valueobjects.UserProfileId;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.CreateMealPlanEntryResource;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.CreateMealPlanResource;

import java.util.List;

public class CreateMealPlanCommandFromResourceAssembler {
    public static CreateMealPlanCommand toCommandFromResource(CreateMealPlanResource resource) {
        return new CreateMealPlanCommand(
                resource.name(),
                resource.description(),
                new MealPlanMacros(
                        resource.calories(),
                        resource.carbs(),
                        resource.proteins(),
                        resource.fats()
                ),
                new UserProfileId(resource.profileId()),
                resource.category(),
                resource.isCurrent(),
                resource.entries().stream()
                        .map(CreateMealPlanEntryCommandFromResourceAssembler::toCommandFromResource)
                        .toList(),
                resource.tags()
        );
    }
}