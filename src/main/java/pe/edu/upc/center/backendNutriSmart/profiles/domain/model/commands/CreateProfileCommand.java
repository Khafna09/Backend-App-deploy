package pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands;

public record CreateProfileCommand(
        String name,
        String email,

        String password,
        Boolean isActive,
        String birthDate,
        Long userProfileId
) {
}
