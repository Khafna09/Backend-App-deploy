package pe.edu.upc.center.backendNutriSmart.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.UserProfile;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.services.ProfileCommandService;
import pe.edu.upc.center.backendNutriSmart.profiles.infrastructure.persistence.jpa.repositories.UserProfileRepository;
import pe.edu.upc.center.backendNutriSmart.profiles.infrastructure.persistence.jpa.repositories.ProfileRepository;

@Service
public class ProfileCommandServiceImpl implements ProfileCommandService {

    private final ProfileRepository profileRepository;
    private final UserProfileRepository userProfileRepository;

    public ProfileCommandServiceImpl(ProfileRepository profileRepository,
                                     UserProfileRepository userProfileRepository) {
        this.profileRepository = profileRepository;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    @Transactional
    public Profile handle(CreateProfileCommand command) {
        UserProfile userProfile;

        if (command.userProfileId() != null) {
            // viene perfil explícito
            userProfile = userProfileRepository.findById(command.userProfileId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "UserProfile no encontrado con id: " + command.userProfileId()
                    ));
        } else {
            // NO vino id → tomamos el último perfil creado
            userProfile = userProfileRepository.findTopByOrderByIdDesc()
                    .orElseThrow(() -> new IllegalStateException(
                            "No hay perfiles en la base de datos para asociar"
                    ));
        }

        Profile profile = new Profile(
                command.name(),
                command.email(),
                command.password(),
                command.isActive(),
                command.birthDate(),
                userProfile
        );
        return profileRepository.save(profile);
    }
}
