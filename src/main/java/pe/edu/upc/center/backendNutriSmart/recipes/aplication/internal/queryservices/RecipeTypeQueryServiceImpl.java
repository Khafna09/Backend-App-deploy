package pe.edu.upc.center.backendNutriSmart.recipes.aplication.internal.queryservices;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities.RecipeType;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllRecipesTypesQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetRecipeTypeByIdQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.RecipeTypeQueryService;
import pe.edu.upc.center.backendNutriSmart.recipes.infrastructure.persistence.jpa.repositories.RecipeTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeTypeQueryServiceImpl implements RecipeTypeQueryService {

    private final RecipeTypeRepository recipeTypeRepository;

    public RecipeTypeQueryServiceImpl(RecipeTypeRepository recipeTypeRepository) {
        this.recipeTypeRepository = recipeTypeRepository;
    }

    @Override
    public List<RecipeType> handle(GetAllRecipesTypesQuery query) {
        return recipeTypeRepository.findAll();
    }

    @Override
    public Optional<RecipeType> handle(GetRecipeTypeByIdQuery query) {
        return recipeTypeRepository.findById(query.recipeTypeId());
    }
}
