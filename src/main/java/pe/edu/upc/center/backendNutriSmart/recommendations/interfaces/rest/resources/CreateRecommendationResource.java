package pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects.RecommendationStatus;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects.TimeOfDay;

public record CreateRecommendationResource(
        // NO tiene id (se genera automáticamente)
        // NO tiene userId (recommendations BASE no tienen usuario)
        Long templateId,
        String reason,
        String notes,
        TimeOfDay timeOfDay,     // ← Enum directo (más estricto)
        Double score,
        RecommendationStatus status // ← Enum directo (más estricto)
        // NO tiene assignedAt (se genera al asignar)
) {}