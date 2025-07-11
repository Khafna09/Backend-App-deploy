package pe.edu.upc.center.backendNutriSmart.recipes.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {
    public UserId {
        if (userId < 0) {
        throw new IllegalArgumentException("Profile userId cannot be negative");
        }
    }
    public UserId(){
        this(0L);
    }
}
