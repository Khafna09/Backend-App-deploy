package pe.edu.upc.center.backendNutriSmart.iam.domain.model.commands;

import pe.edu.upc.center.backendNutriSmart.iam.domain.model.entities.Role;

import java.util.List;

public record SignUpCommand(String username, String password, List<Role> roles) {
}
