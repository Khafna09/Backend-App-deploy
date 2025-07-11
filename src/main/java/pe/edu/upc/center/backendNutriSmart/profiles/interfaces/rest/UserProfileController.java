package pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.acl.UserProfilesContextFacade;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.CreateUserProfileResource;
import pe.edu.upc.center.backendNutriSmart.profiles.interfaces.rest.resources.UserProfileResource;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user-profiles")
@Tag(name = "UserProfiles", description = "se registra el perfil del usuario")
public class UserProfileController {
    private final UserProfilesContextFacade facade;

    public UserProfileController(UserProfilesContextFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    public List<UserProfileResource> listAll() {
        return facade.fetchAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResource> getById(@PathVariable Long id) {
        return facade.fetchById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Integer> create(@RequestBody CreateUserProfileResource r) {
        var newId = facade.create(r);
        return ResponseEntity.ok(newId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> update(@PathVariable Long id,
                                       @RequestBody CreateUserProfileResource r) {
        if (facade.fetchById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        facade.update(id, r);
        return ResponseEntity.ok(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facade.delete(id);
        return ResponseEntity.noContent().build();
    }
}
