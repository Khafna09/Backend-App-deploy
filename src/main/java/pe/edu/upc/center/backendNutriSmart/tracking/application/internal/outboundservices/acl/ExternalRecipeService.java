package pe.edu.upc.center.backendNutriSmart.tracking.application.internal.outboundservices.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.acl.RecipeContextFacade;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.RecipeId;

import java.util.List;
import java.util.Optional;

@Service
public class ExternalRecipeService {

    private final RecipeContextFacade recipeContextFacade;

    public ExternalRecipeService(RecipeContextFacade recipeContextFacade) {
        this.recipeContextFacade = recipeContextFacade;
    }

    /**
     * Verifica si existe una receta con el ID especificado
     * @param recipeId ID de la receta a verificar (value object)
     * @return true si la receta existe, false en caso contrario
     */
    public boolean existsByRecipeId(RecipeId recipeId) {
        Optional<RecipeResource> recipe = recipeContextFacade.fetchById(Math.toIntExact(recipeId.recipeId()));
        return recipe.isPresent();
    }

    /**
     * Obtiene una receta por su ID
     * @param recipeId ID de la receta (value object)
     * @return Optional con la receta si existe
     */
    public Optional<RecipeResource> getRecipeById(RecipeId recipeId) {
        return recipeContextFacade.fetchById(Math.toIntExact(recipeId.recipeId()));
    }

    /**
     * Verifica si existe una receta con el nombre especificado
     * @param name Nombre de la receta
     * @return true si la receta existe, false en caso contrario
     */
    public boolean existsByName(String name) {
        return recipeContextFacade.existsByName(name);
    }

    /**
     * Obtiene todas las recetas disponibles
     * @return Lista de todas las recetas
     */
    public List<RecipeResource> getAllRecipes() {
        return recipeContextFacade.fetchAll();
    }
}