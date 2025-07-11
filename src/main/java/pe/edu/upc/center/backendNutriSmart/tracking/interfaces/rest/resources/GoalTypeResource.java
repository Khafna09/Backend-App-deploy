package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.GoalTypes;

public record GoalTypeResource(
        GoalTypes goalType,
        String displayName,
        double calories,
        double carbs,
        double proteins,
        double fats
) {
    public static GoalTypeResource from(GoalTypes goalType) {
        return new GoalTypeResource(
                goalType,
                goalType.getDisplayName(),
                goalType.getCalories(),
                goalType.getCarbs(),
                goalType.getProteins(),
                goalType.getFats()
        );
    }
}