package pe.edu.upc.center.backendNutriSmart.iam.domain.services;

import pe.edu.upc.center.backendNutriSmart.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
  void handle(SeedRolesCommand command);
}
