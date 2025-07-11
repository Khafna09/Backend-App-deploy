package pe.edu.upc.center.backendNutriSmart.tracking.domain.services;

import pe.edu.upc.center.backendNutriSmart.tracking.domain.model.commands.SeedTrackingMasterDataCommand;

public interface TrackingMasterDataCommandService {
    void handle(SeedTrackingMasterDataCommand command);
}
