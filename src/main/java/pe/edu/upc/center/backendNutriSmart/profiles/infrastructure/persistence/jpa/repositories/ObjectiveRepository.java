package pe.edu.upc.center.backendNutriSmart.profiles.infrastructure.persistence.jpa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.center.backendNutriSmart.profiles.domain.model.Entities.Objective;

@Repository
public interface ObjectiveRepository extends JpaRepository<Objective, Long> {}
