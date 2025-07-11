package pe.edu.upc.center.backendNutriSmart.mealplan.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.acl.RecipeContextFacade;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalMealPlanRecipeService {

    private final RecipeContextFacade recipeContextFacade;

    public ExternalMealPlanRecipeService(RecipeContextFacade recipeContextFacade) {
        this.recipeContextFacade = recipeContextFacade;
    }

    public Optional<RecipeResource> fetchRecipeById(int recipeId) {
        return recipeContextFacade.fetchById(recipeId);
    }
    public List<RecipeResource> fetchAllRecipes() {
        return recipeContextFacade.fetchAll();
    }
}
