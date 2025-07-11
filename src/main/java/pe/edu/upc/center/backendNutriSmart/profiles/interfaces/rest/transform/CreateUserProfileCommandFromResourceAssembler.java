package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.CreateUserProfileResource;

public class CreateUserProfileCommandFromResourceAssembler {

    public static CreateUserProfileCommand toCommandFromResource(CreateUserProfileResource resource) {
        return new CreateUserProfileCommand(
                resource.gender(),
                resource.height(),
                resource.weight(),
                resource.userScore(),
                resource.activityLevelId(),
                resource.objectiveId(),
                resource.allergyIds()
        );
    }
}
