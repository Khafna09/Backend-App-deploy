package pe.edu.upc.center.backendNutriSmart.recommendations.domain.services;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.aggregates.Recommendation;

import java.util.List;

public interface RecommendationAssignmentService {
    List<Recommendation> autoAssignRecommendations(Long userId);
    Recommendation assignSingleRecommendation(Long userId, Long templateId);
}