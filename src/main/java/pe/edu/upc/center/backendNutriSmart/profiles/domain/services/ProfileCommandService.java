package pe.edu.upc.center.backendNutriSmart.profiles.domain.services;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.Profile;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.commands.CreateProfileCommand;

public interface ProfileCommandService {
    Profile handle(CreateProfileCommand command);
}
