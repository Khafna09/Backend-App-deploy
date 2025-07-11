package pe.edu.upc.center.backendNutriSmart.iam.domain.services;

import pe.edu.upc.center.backendNutriSmart.iam.domain.model.entities.Role;
import pe.edu.upc.center.backendNutriSmart.iam.domain.model.queries.GetAllRolesQuery;
import pe.edu.upc.center.backendNutriSmart.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
  List<Role> handle(GetAllRolesQuery query);
  Optional<Role> handle(GetRoleByNameQuery query);
}
