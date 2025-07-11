package pe.edu.upc.center.backendNutriSmart.recipes.domain.services;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Ingredient;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllIngredientsQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetIngredientsByIdQuery;

import java.util.List;
import java.util.Optional;

public interface IngredientQueryService {
    List<Ingredient> handle(GetAllIngredientsQuery query);
    Optional<Ingredient> handle(GetIngredientsByIdQuery query);
}
