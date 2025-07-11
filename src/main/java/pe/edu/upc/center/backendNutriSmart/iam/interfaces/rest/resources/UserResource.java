package pe.edu.upc.center.backendNutriSmart.iam.interfaces.rest.resources;

import java.util.List;

public record UserResource(int id, String username, List<String> roles) {
}
