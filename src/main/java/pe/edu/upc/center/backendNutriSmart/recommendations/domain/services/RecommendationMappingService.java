package pe.edu.upc.center.backendNutriSmart.recommendations.domain.services;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.entities.RecommendationTemplate;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects.TimeOfDay;

public interface RecommendationMappingService {
    TimeOfDay determineTimeOfDay(RecommendationTemplate template);
    Double calculateScore(RecommendationTemplate template);
    String generateReason(RecommendationTemplate template);
}