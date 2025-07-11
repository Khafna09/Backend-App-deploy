package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource r) {
        return new CreateProfileCommand(
                r.name(),
                r.email(),
                r.password(),
                r.isActive(),
                r.birthDate(),
                r.userProfileId()
        );
    }
}
