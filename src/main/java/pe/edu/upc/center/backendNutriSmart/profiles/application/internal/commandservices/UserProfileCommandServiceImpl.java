package pe.edu.upc.center.backendNutriSmart.profiles.application.internal.commandservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.UserProfile;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.DeleteUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.UpdateUserProfileCommand;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.services.UserProfileCommandService;
import pe.edu.upc.center.backendNutriSmart.profiles.infrastructure.persistence.jpa.repositories.*;

import java.util.Optional;

@Service
public class UserProfileCommandServiceImpl implements UserProfileCommandService {

    private final UserProfileRepository userProfileRepository;
    private final ActivityLevelRepository activityLevelRepository;
    private final ObjectiveRepository objectiveRepository;
    private final AllergyRepository allergyRepository;

    public UserProfileCommandServiceImpl(UserProfileRepository userProfileRepository,
                                         ActivityLevelRepository activityLevelRepository,
                                         ObjectiveRepository objectiveRepository,
                                         AllergyRepository allergyRepository) {
        this.userProfileRepository = userProfileRepository;
        this.activityLevelRepository = activityLevelRepository;
        this.objectiveRepository = objectiveRepository;
        this.allergyRepository = allergyRepository;
    }

    @Override
    public int handle(CreateUserProfileCommand command) {
        var activityLevel = activityLevelRepository.findById(command.activityLevelId())
                .orElseThrow(() -> new IllegalArgumentException("Activity level not found"));

        var objective = objectiveRepository.findById(command.objectiveId())
                .orElseThrow(() -> new IllegalArgumentException("Objective not found"));

        var userProfile = new UserProfile(command, activityLevel, objective);

        if (command.allergyIds() != null) {
            var allergies = allergyRepository.findAllById(command.allergyIds());
            allergies.forEach(userProfile::addAllergy);
        }

        var savedProfile = userProfileRepository.save(userProfile);
        return savedProfile.getId();
    }

    @Override
    public Optional<UserProfile> handle(UpdateUserProfileCommand command) {
        var profile = userProfileRepository.findById(command.userProfileId())
                .orElseThrow(() -> new IllegalArgumentException("Profile profile not found"));

        var activityLevel = activityLevelRepository.findById(command.activityLevelId())
                .orElseThrow(() -> new IllegalArgumentException("Activity level not found"));

        var objective = objectiveRepository.findById(command.objectiveId())
                .orElseThrow(() -> new IllegalArgumentException("Objective not found"));

        profile.updateProfile(command.gender(), command.height(), command.weight(),
                activityLevel, objective, command.userScore());

        var updatedProfile = userProfileRepository.save(profile);
        return Optional.of(updatedProfile);
    }

    @Override
    public void handle(DeleteUserProfileCommand command) {
        if (!userProfileRepository.existsById(command.userProfileId())) {
            throw new IllegalArgumentException("Profile profile not found");
        }
        userProfileRepository.deleteById(command.userProfileId());
    }
}
