package pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources;

import java.time.LocalDateTime;

public record RecommendationResource(
        Long id,
        Long userId,
        Long templateId,
        String reason,
        String notes,
        String timeOfDay,        // String en lugar de TimeOfDay
        Double score,
        String status,           // String en lugar de RecommendationStatus
        LocalDateTime assignedAt
) {}