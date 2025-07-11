package pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.aggregates.MealPlan;
import pe.edu.upc.center.backendNutriSmart.mealplan.domain.model.entities.MealPlanTag;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanEntryResource;
import pe.edu.upc.center.backendNutriSmart.mealplan.interfaces.rest.resources.MealPlanResource;

import java.util.List;

public class MealPlanResourceFromEntityAssembler {
    public static MealPlanResource toResourceFromEntity(MealPlan mealPlan) {
        return new MealPlanResource(
                mealPlan.getId(),
                mealPlan.getName(),
                mealPlan.getDescription(),
                mealPlan.getMacros().getCalories(),
                mealPlan.getMacros().getCarbs(),
                mealPlan.getMacros().getProteins(),
                mealPlan.getMacros().getFats(),
                mealPlan.getProfileId().userProfileId(),
                mealPlan.getCategory(),
                mealPlan.getIsCurrent(),
                MealPlanEntryResourceFromEntityAssembler.toResourceFromEntities(mealPlan.getEntries().getMealPlanEntries()),
                mealPlan.getTags().getMealPlanTags()
                        .stream()
                        .map(MealPlanTag::getTag)
                        .toList()
        );
    }
}