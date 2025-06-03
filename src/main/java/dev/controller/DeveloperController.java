package dev.controller;


import dev.model.Developer;
import dev.service.DeveloperService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/developers")
@RequiredArgsConstructor
public class DeveloperController {

    private final DeveloperService developerService;

    @PostMapping
    public ResponseEntity<Developer> createDeveloper(@RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.createDeveloper(developer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable Long id, @RequestBody Developer developer) {
        return ResponseEntity.ok(developerService.updateDeveloper(id, developer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeveloper(@PathVariable Long id) {
        developerService.deleteDeveloper(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Developer> getDeveloper(@PathVariable Long id) {
        return ResponseEntity.ok(developerService.getDeveloper(id));
    }

    @GetMapping
    public ResponseEntity<List<Developer>> getAllDevelopers() {
        return ResponseEntity.ok(developerService.getAllDevelopers());
    }
}
