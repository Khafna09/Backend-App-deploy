package pe.edu.upc.center.backendNutriSmart.recommendations.application.internal.queryservices;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.entities.RecommendationTemplate;
import pe.edu.upc.center.backendNutriSmart.recommendations.infrastructure.persistence.jpa.repositories.RecommendationTemplateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendationTemplateQueryService implements pe.edu.upc.center.backendNutriSmart.recommendations.domain.services.RecommendationTemplateQueryService {

    private final RecommendationTemplateRepository repository;

    public RecommendationTemplateQueryService(RecommendationTemplateRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<RecommendationTemplate> getAllTemplates() {
        return repository.findAll();
    }
}
