package pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record UserId(Long userId) {
  public UserId {
    if(userId <= 0) {
      throw new IllegalArgumentException("UserId must be greater than 0");
    }
  }
  public UserId() {
    this(0L);
  }
}
