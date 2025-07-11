package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.UpdateUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.UserProfileResource;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.CreateUserProfileResource;
public class UpdateUserProfileCommandFromResourceAssembler {

    /**
     * Convierte un recurso REST y un ID a UpdateUserProfileCommand.
     */
    // En UpdateUserProfileCommandFromResourceAssembler.java
    public static UpdateUserProfileCommand toCommandFromResource(Long profileId, CreateUserProfileResource resource) {
        return new UpdateUserProfileCommand(
                profileId,
                resource.gender(),
                resource.height(),
                resource.weight(),
                resource.userScore(),
                resource.activityLevelId(),
                Long.valueOf(resource.objectiveId())
        );
    }
}
