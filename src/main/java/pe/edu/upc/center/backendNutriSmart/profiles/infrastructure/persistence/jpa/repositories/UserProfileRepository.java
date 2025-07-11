// src/main/java/pe/edu/upc/center/backendNutriSmart/profiles/infrastructure/persistence/jpa/repositories/UserProfileRepository.java
package pe.edu.upc.center.backendNutriSmart.profiles.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.aggregates.UserProfile;

import java.util.Optional;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    /**
     * Devuelve el UserProfile con el ID más alto (el último creado).
     */
    Optional<UserProfile> findTopByOrderByIdDesc();
}
