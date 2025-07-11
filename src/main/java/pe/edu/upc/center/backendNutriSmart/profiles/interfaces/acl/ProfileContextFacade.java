package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.services.ProfileCommandService;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.services.ProfileQueryService;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.CreateProfileResource;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.ProfileResource;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileContextFacade {
    private final ProfileCommandService commandService;
    private final ProfileQueryService queryService;

    public ProfileContextFacade(ProfileCommandService commandService, ProfileQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    public List<ProfileResource> fetchAll() {
        return UserResourceFromEntityAssembler.toResources(queryService.getAllUsers());
    }

    public Optional<ProfileResource> fetchById(Long id) {
        return queryService.getUserById(id).map(UserResourceFromEntityAssembler::toResourceFromEntity);
    }

    public int create(CreateProfileResource resource) {
        CreateProfileCommand command = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        return commandService.handle(command).getId();
    }
}
