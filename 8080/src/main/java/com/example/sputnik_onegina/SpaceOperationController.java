package com.example.sputnik_onegina;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SpaceOperationController {
    private final SpaceOperationCenterService spaceOperationCenterService;

    @PostMapping("/missions") // Уточнение, как к методу обращаться извне
    public ResponseEntity<Void> executeMission(@RequestBody MissionRequest missionRequest) {
        try {
            spaceOperationCenterService.executeMission(missionRequest);
            return ResponseEntity.ok().build(); //Возврат успешного ответа (можно туда кидать и обьекты результата)
        }
        catch (SpaceOperationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    @PostMapping("/add-satellites")
    public ResponseEntity<Void> addSatellite(@RequestBody AddSatelliteRequest addSatelliteRequest) {
        spaceOperationCenterService.addSatellite(addSatelliteRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/overview")
    public ResponseEntity<String> getSystemOverview() {
        return ResponseEntity.ok(spaceOperationCenterService.getSystemOverview());
    }
    @DeleteMapping("/constellations/{constellationName}/satellites/{satelliteName}")
    public ResponseEntity<Void> deleteSatellite(@PathVariable String constellationName, @PathVariable String satelliteName) {
        spaceOperationCenterService.deleteSatellite(constellationName, satelliteName);
        return ResponseEntity.noContent().build();
    }
}
