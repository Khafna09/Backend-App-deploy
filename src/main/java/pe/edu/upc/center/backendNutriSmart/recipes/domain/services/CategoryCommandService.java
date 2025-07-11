package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.CreateCategoryCommand;

public interface CategoryCommandService {
    Long handle(CreateCategoryCommand command);
}
