package pe.edu.upc.center.backendNutriSmart.recommendations.domain.services;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.aggregates.Recommendation;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.AssignRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.AutoAssignRecommendationsCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.CreateRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.DeleteRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.UpdateRecommendationResource;

import java.util.List;

public interface RecommendationCommandService {
    int handle(AssignRecommendationCommand command);
    int handle(CreateRecommendationCommand command);
    void handle(DeleteRecommendationCommand command);
    List<Recommendation> handleAutoAssign(AutoAssignRecommendationsCommand command);
    Recommendation handleUpdate(Long recommendationId, UpdateRecommendationResource resource);
}
