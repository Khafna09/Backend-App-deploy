package pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.AssignRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.AssignRecommendationResource;

public class AssignRecommendationCommandFromResourceAssembler {

    public static AssignRecommendationCommand toCommandFromResource(AssignRecommendationResource resource)
    {
        return new AssignRecommendationCommand(
                resource.userId(),
                resource.templateId(),
                resource.reason(),
                resource.notes(),
                resource.timeOfDay(),
                resource.score(),
                resource.status()
        );
    }
}
