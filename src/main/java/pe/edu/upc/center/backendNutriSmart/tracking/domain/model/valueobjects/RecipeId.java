package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
public record RecipeId(Long recipeId) {
 public RecipeId {
   if (recipeId < 0) {
     throw new IllegalArgumentException("Recipe Id must exists");
   }
 }

 public RecipeId() {this(0L);}

}