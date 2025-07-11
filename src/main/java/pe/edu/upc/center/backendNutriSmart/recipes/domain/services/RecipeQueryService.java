package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetRecipesByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RecipeQueryService {
    List<Recipe> handle(GetAllRecipesQuery query);
    Optional<Recipe> handle(GetRecipesByIdQuery query);
}
