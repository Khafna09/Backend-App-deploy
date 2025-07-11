package pe.edu.upc.center.backendNutriSmart.recommendations.application.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.aggregates.Recommendation;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.queries.GetRecommendationsByUserQuery;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.services.RecommendationQueryService;
import pe.edu.upc.center.backendNutriSmart.recommendations.infrastructure.persistence.jpa.repositories.RecommendationRepository;

import java.util.List;

@Service
public class RecommendationQueryServiceImpl implements RecommendationQueryService {

    private final RecommendationRepository recommendationRepository;

    public RecommendationQueryServiceImpl(RecommendationRepository recommendationRepository) {
        this.recommendationRepository = recommendationRepository;
    }

    @Override
    public List<Recommendation> handle(GetRecommendationsByUserQuery query) {
        return this.recommendationRepository.findAllByUserId_Value(query.userId());
    }
}
