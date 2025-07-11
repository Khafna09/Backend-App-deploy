package pe.edu.upc.center.backendNutriSmart.recommendations.domain.services;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.entities.RecommendationTemplate;

import java.util.List;

public interface RecommendationTemplateQueryService {
    List<RecommendationTemplate> getAllTemplates();
}