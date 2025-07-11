package pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities.Category;
import pe.edu.upc.center.backendNutriSmart.recipes.interfaces.rest.resources.CategoryResource;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryResourceFromEntityAssembler {

    public static CategoryResource toResourceFromEntity(Category category) {
        return new CategoryResource(category.getId(), category.getName());
    }

    public static List<CategoryResource> toResources(List<Category> categories) {
        return categories.stream()
                .map(CategoryResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
    }
}
