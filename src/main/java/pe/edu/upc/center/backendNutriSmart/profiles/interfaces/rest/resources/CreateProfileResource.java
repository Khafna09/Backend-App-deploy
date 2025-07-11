package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources;

public record CreateProfileResource(
        String name,
        String email,
        String password,
        Boolean isActive,
        String birthDate,
        Long userProfileId
) {}
