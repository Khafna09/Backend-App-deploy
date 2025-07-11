package pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects.RecommendationStatus;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects.TimeOfDay;

public record AssignRecommendationCommand(
        Long userId,
        Long templateId,
        String reason,
        String notes,
        TimeOfDay timeOfDay,
        Double score,
        RecommendationStatus status
) {}
