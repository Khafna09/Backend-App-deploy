// UpdateMealPlanEntryCommandFromResourceAssembler.java
package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.Entities.MealPlanType;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.UpdateMealPlanEntryInTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.MealPlanTypes;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.RecipeId;
import pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources.UpdateMealPlanEntryResource;

public class UpdateMealPlanEntryCommandFromResourceAssembler {
    public static UpdateMealPlanEntryInTrackingCommand toCommand(UpdateMealPlanEntryResource resource, Long mealPlanEntryId) {
        return new UpdateMealPlanEntryInTrackingCommand(
                resource.trackingId() != null ? resource.trackingId() : 0L, // Si es null, pasar 0L para que el service lo resuelva
                mealPlanEntryId,
                new RecipeId(resource.recipeId()),
                MealPlanTypes.valueOf(resource.mealPlanType()),
                resource.dayNumber()
        );
    }
}