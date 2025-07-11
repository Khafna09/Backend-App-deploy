package pe.edu.upc.center.backendNutriSmart.recommendations.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.aggregates.Recommendation;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.entities.RecommendationTemplate;

import java.util.List;
import java.util.Optional;

public interface RecommendationRepository extends JpaRepository<Recommendation, Long> {
    List<Recommendation> findAllByUserId_Value(Long userId);

    // Métodos para recommendations sin asignar (BASE templates)
    List<Recommendation> findByTemplateAndUserIdIsNull(RecommendationTemplate template);
    Optional<Recommendation> findFirstByTemplateAndUserIdIsNull(RecommendationTemplate template);

    // Query para debug - ver todas las recommendations sin asignar
    @Query("SELECT r FROM Recommendation r WHERE r.userId IS NULL")
    List<Recommendation> findByUserIdIsNull();

    // Query para debug - contar recommendations base disponibles
    @Query("SELECT COUNT(r) FROM Recommendation r WHERE r.userId IS NULL")
    Long countAvailableBaseRecommendations();
}