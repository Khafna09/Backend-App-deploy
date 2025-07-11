package pe.edu.upc.center.backendNutriSmart.recipes.aplication.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetRecipesByIdQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.RecipeQueryService;
import pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeQueryServiceImpl implements RecipeQueryService {

    private final RecipeRepository recipeRepository;

    public RecipeQueryServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public List<Recipe> handle(GetAllRecipesQuery query) {
        return recipeRepository.findAll();
    }

    @Override
    public Optional<Recipe> handle(GetRecipesByIdQuery query) {
        return recipeRepository.findById(query.recipeId());
    }
}
