package pe.edu.upc.center.backendNutriSmart.tracking.domain.services;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.CreateMacronutrientValuesCommand;


public interface MacronutrientValuesCommandService {
    Long handle(CreateMacronutrientValuesCommand command);
}
