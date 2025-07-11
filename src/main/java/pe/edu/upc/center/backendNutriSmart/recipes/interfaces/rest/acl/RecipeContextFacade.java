package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.acl;

import org.springframework.stereotype.Service;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands.DeleteRecipeCommand;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetAllRecipesQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.queries.GetRecipesByIdQuery;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.RecipeCommandService;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.services.RecipeQueryService;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CreateRecipeResource;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.RecipeResource;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform.CreateRecipeCommandFromResourceAssembler;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform.RecipeResourceFromEntityAssembler;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeContextFacade {

    private final RecipeCommandService commandService;
    private final RecipeQueryService queryService;

    public RecipeContextFacade(RecipeCommandService commandService,
                               RecipeQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    public List<RecipeResource> fetchAll() {
        var entities = queryService.handle(new GetAllRecipesQuery());
        return RecipeResourceFromEntityAssembler.toResources(entities);
    }

    public Optional<RecipeResource> fetchById(int id) {
        return queryService.handle(new GetRecipesByIdQuery(id))
                .map(RecipeResourceFromEntityAssembler::toResourceFromEntity);
    }

    public int create(CreateRecipeResource resource) {
        var cmd = CreateRecipeCommandFromResourceAssembler.toCommandFromResource(resource);
        return commandService.handle(cmd);
    }

    public void delete(int id) {
        commandService.handle(new DeleteRecipeCommand(id));
    }

    public boolean existsByName(String name) {
        return queryService.handle(new GetAllRecipesQuery())
                .stream()
                .anyMatch(recipe -> recipe.getName().equalsIgnoreCase(name));
    }
}