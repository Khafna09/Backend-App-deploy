package pe.edu.upc.center.backendNutriSmart.recommendations.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.entities.RecommendationTemplate;

import java.util.List;

public interface RecommendationTemplateRepository extends JpaRepository<RecommendationTemplate, Long> {
    List<RecommendationTemplate> findByCategory(String category);
}