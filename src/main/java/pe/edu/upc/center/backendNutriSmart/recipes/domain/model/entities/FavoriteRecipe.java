package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.aggregates.Recipe;
import pe.edu.upc.center.backendNutriSmart.recipes.domain.model.valueobjects.UserId;
import pe.edu.upc.center.backendNutriSmart.shared.domain.model.entities.AuditableModel;

@Getter
@Entity
@Table(name = "favorite_recipes")
@NoArgsConstructor
@ToString
public class FavoriteRecipe extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 👉 Usuario que marcó la receta como favorita (value object)
    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "user_id", nullable = false))
    private UserId userId;

    // 👉 Receta marcada como favorita
    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    // ------------------------------------------
    public FavoriteRecipe(UserId userId, Recipe recipe) {
        this.userId = userId;
        this.recipe = recipe;
    }

    public Long getUserId() {
        return this.userId.userId();
    }
}