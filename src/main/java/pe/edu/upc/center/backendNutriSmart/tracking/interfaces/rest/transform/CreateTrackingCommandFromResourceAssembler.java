// CreateTrackingCommandFromResourceAssembler.java
package pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.transform;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.CreateTrackingCommand;
import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.valueobjects.UserId;
import pe.edu.upc.center.backendNutriSmart.tracking.interfaces.rest.resources.CreateTrackingResource;

public class CreateTrackingCommandFromResourceAssembler {
    public static CreateTrackingCommand toCommand(CreateTrackingResource resource) {
        return new CreateTrackingCommand(new UserId(resource.userId()));
    }
}