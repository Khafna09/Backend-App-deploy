package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.acl.ProfileContextFacade;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;

import java.util.Optional;

/**
 * External Profile Service
 * This service provides an ACL (Anti-Corruption Layer) for the Tracking bounded context
 * to interact with the Profile bounded context without direct coupling.
 */
@Service
public class ExternalProfileService {

    private final UserProfilesContextFacade userProfilesFacade;

    private final ProfileContextFacade profileContextFacade;

    public ExternalProfileService(UserProfilesContextFacade userProfilesFacade, ProfileContextFacade profileContextFacade) {
        this.userProfilesFacade = userProfilesFacade;
        this.profileContextFacade = profileContextFacade;
    }

    public boolean existsByUserId(UserId userId) {
        return profileContextFacade.fetchAll().stream()
                .anyMatch(profile -> profile.userProfileId() == userId.userId());
    }

    /**
     * Obtiene el nombre del objetivo de un perfil de usuario
     * @param profileId ID del perfil (equivale al userId en el contexto de tracking)
     * @return Optional con el nombre del objetivo si existe
     */
    public Optional<String> getObjectiveNameByProfileId(Long profileId) {
        return userProfilesFacade.fetchObjectiveNameByProfileId(profileId);
    }

    /**
     * Verifica si un perfil existe
     * @param profileId ID del perfil
     * @return true si el perfil existe, false en caso contrario
     */
    public boolean existsProfile(Long profileId) {
        return userProfilesFacade.existsProfileById(profileId);
    }

    /**
     * Valida que un perfil exista antes de crear/actualizar un tracking goal
     * @param profileId ID del perfil
     * @throws IllegalArgumentException si el perfil no existe
     */
    public void validateProfileExists(Long profileId) {
        if (!existsProfile(profileId)) {
            throw new IllegalArgumentException("Profile not found with ID: " + profileId);
        }
    }

    /**
     * Obtiene el objetivo y valida que el perfil exista
     * @param profileId ID del perfil
     * @return Nombre del objetivo
     * @throws IllegalArgumentException si el perfil no existe o no tiene objetivo
     */
    public String getValidatedObjectiveName(Long profileId) {
        validateProfileExists(profileId);

        return getObjectiveNameByProfileId(profileId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Profile exists but has no objective defined for ID: " + profileId));
    }
}