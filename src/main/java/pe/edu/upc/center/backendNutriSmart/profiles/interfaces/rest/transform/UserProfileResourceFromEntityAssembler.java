// src/main/java/pe/edu/upc/center/platform/profiles/interfaces/rest/transform/UserProfileResourceFromEntityAssembler.java
package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.UserProfile;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.UserProfileResource;

import java.util.List;
import java.util.stream.Collectors;

public class UserProfileResourceFromEntityAssembler {

    public static UserProfileResource toResourceFromEntity(UserProfile entity) {
        return new UserProfileResource(
                entity.getId(),
                entity.getGender(),
                entity.getHeight(),
                entity.getWeight(),
                entity.getUserScore(),
                entity.getActivityLevel().getId(),
                entity.getActivityLevel().getName(),
                (int) entity.getObjective().getId(),
                entity.getObjective().getObjectiveName(),
                entity.getAllergies().stream()
                        .map(a -> a.getName())
                        .toList()
        );
    }

    // Nuevo método para convertir una lista de entidades
    public static List<UserProfileResource> toResources(List<UserProfile> entities) {
        return entities.stream()
                .map(UserProfileResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}