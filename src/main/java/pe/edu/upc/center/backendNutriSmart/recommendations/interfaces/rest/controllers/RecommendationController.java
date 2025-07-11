package pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.aggregates.Recommendation;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.AssignRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.AutoAssignRecommendationsCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.commands.DeleteRecommendationCommand;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.model.queries.GetRecommendationsByUserQuery;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.services.RecommendationCommandService;
import pe.edu.upc.center.backendNutriSmart.recommendations.domain.services.RecommendationQueryService;
import pe.edu.upc.center.backendNutriSmart.recommendations.infrastructure.persistence.jpa.repositories.RecommendationRepository;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.AssignRecommendationResource;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.CreateRecommendationResource;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.RecommendationResource;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.resources.UpdateRecommendationResource;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.transform.AssignRecommendationCommandFromResourceAssembler;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.transform.CreateRecommendationCommandFromResourceAssembler;
import pe.edu.upc.center.backendNutriSmart.recommendations.interfaces.rest.transform.RecommendationResourceFromEntityAssembler;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/recommendations", produces = "application/json")
@Tag(name = "Recommendations", description = "Recommendation Management Endpoints")
public class RecommendationController {

    private final RecommendationCommandService commandService;
    private final RecommendationQueryService queryService;
    private final RecommendationRepository recommendationRepository;

    public RecommendationController(
            RecommendationCommandService commandService,
            RecommendationQueryService queryService,
            RecommendationRepository recommendationRepository) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.recommendationRepository = recommendationRepository;
    }

    // ✅ 1. AUTO-ASIGNAR RECOMMENDATIONS (endpoint principal)
    @PostMapping("/auto-assign/{userId}")
    public ResponseEntity<List<RecommendationResource>> autoAssignRecommendations(@PathVariable Long userId) {
        try {
            AutoAssignRecommendationsCommand command = new AutoAssignRecommendationsCommand(userId);
            List<Recommendation> assignedRecommendations = commandService.handleAutoAssign(command);

            List<RecommendationResource> resources = assignedRecommendations.stream()
                    .map(RecommendationResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());

            return ResponseEntity.status(HttpStatus.CREATED).body(resources);

        } catch (RuntimeException e) {
            if (e.getMessage().contains("User profile not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 2. ASIGNAR RECOMMENDATION ESPECÍFICA
    @PostMapping("/assign")
    public ResponseEntity<RecommendationResource> assignSpecificRecommendation(@RequestBody AssignRecommendationResource resource) {
        try {
            AssignRecommendationCommand command = AssignRecommendationCommandFromResourceAssembler.toCommandFromResource(resource);
            int recommendationId = commandService.handle(command);

            return queryService.handle(new GetRecommendationsByUserQuery(command.userId()))
                    .stream()
                    .filter(rec -> rec.getId() == recommendationId)
                    .findFirst()
                    .map(RecommendationResourceFromEntityAssembler::toResourceFromEntity)
                    .map(responseResource -> ResponseEntity.status(HttpStatus.CREATED).body(responseResource))
                    .orElse(ResponseEntity.notFound().build());

        } catch (RuntimeException e) {
            if (e.getMessage().contains("User profile not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 3. CREAR RECOMMENDATION BASE (sin asignar)
    @PostMapping("/base")
    public ResponseEntity<RecommendationResource> createBaseRecommendation(@RequestBody CreateRecommendationResource resource) {
        try {
            // Validación: resource válido y templateId presente
            if (resource == null || resource.templateId() == null) {
                return ResponseEntity.badRequest().build();
            }
            // Llama al servicio para crear recommendation base
            int recommendationId = commandService.handle(
                    CreateRecommendationCommandFromResourceAssembler.toCommandFromResource(resource)
            );
            // Busca la recommendation recién creada
            return recommendationRepository.findById((long) recommendationId)
                    .map(RecommendationResourceFromEntityAssembler::toResourceFromEntity)
                    .map(r -> ResponseEntity.status(HttpStatus.CREATED).body(r))
                    .orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 4. OBTENER RECOMMENDATIONS POR USUARIO
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RecommendationResource>> getUserRecommendations(@PathVariable Long userId) {
        try {
            List<Recommendation> recommendations = queryService.handle(new GetRecommendationsByUserQuery(userId));
            List<RecommendationResource> resources = recommendations.stream()
                    .map(RecommendationResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(resources);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 5. ELIMINAR RECOMMENDATION
    @DeleteMapping("/{recommendationId}")
    public ResponseEntity<Void> deleteRecommendation(@PathVariable Long recommendationId) {
        try {
            commandService.handle(new DeleteRecommendationCommand(recommendationId));
            return ResponseEntity.noContent().build();

        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 6. OBTENER TODAS LAS RECOMMENDATIONS (admin)
    @GetMapping
    public ResponseEntity<List<RecommendationResource>> getAllRecommendations() {
        try {
            List<Recommendation> recommendations = recommendationRepository.findAll();
            List<RecommendationResource> resources = recommendations.stream()
                    .map(RecommendationResourceFromEntityAssembler::toResourceFromEntity)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(resources);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // ✅ 7. ACTUALIZAR RECOMMENDATION
    @PutMapping("/{recommendationId}")
    public ResponseEntity<RecommendationResource> updateRecommendation(
            @PathVariable Long recommendationId,
            @RequestBody UpdateRecommendationResource resource) {
        try {
            Recommendation updated = commandService.handleUpdate(recommendationId, resource);
            RecommendationResource response = RecommendationResourceFromEntityAssembler.toResourceFromEntity(updated);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            if (e.getMessage().contains("not found")) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}