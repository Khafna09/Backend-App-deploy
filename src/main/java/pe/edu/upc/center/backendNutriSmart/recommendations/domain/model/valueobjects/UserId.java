package pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long value) {
    public UserId {
        // Permitir null para recomendaciones sin usuario asignado
        if (value != null && value <= 0) {
            throw new IllegalArgumentException("User ID must be positive when provided.");
        }
    }

    // Constructor sin argumentos requerido por JPA
    public UserId() {
        this(null);
    }

    // Método getter explícito (opcional, pero usado en el proyecto)
    public Long getValue() {
        return value;
    }
}