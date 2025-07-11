package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.GoalTypes;

public record UpdateTrackingGoalResource(
        GoalTypes goalType
) {
}