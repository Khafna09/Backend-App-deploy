package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.commands;

public record UpdateRecipeCommand(
        int recipeId,
        String name,
        String description,
        int preparationTime,
        String difficulty,
        String categoryName,
        String recipeTypeName
) { }
