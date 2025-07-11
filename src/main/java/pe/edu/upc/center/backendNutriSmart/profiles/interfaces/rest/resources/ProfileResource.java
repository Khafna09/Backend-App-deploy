package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources;

public record ProfileResource(
        int id,
        String name,
        String email,
        String password,
        Boolean isActive,
        String birthDate,
        int userProfileId
) {}
