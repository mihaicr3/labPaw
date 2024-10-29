package com.example.laburipaw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/produs")
public class ProdusController {

    @Autowired
    private ProdusRepository produsRepository;

    // Create a new Produs
    @PostMapping
    public ResponseEntity<Produs> createProdus(@RequestBody Produs produs) {
        Produs savedProdus = produsRepository.save(produs);
        return new ResponseEntity<>(savedProdus, HttpStatus.CREATED);
    }

    // Get all Produs
    @GetMapping
    public List<Produs> getAllProdus() {
        return StreamSupport.stream(produsRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }
    // Get a single Produs by ID
    @GetMapping("/{id}")
    public ResponseEntity<Produs> getProdusById(@PathVariable Long id) {
        Optional<Produs> produs = produsRepository.findById(id);
        return produs.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update an existing Produs
    @PutMapping("/{id}")
    public ResponseEntity<Produs> updateProdus(@PathVariable Long id, @RequestBody Produs produsDetails) {
        Optional<Produs> optionalProdus = produsRepository.findById(id);
        if (!optionalProdus.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Produs produsToUpdate = optionalProdus.get();
        produsToUpdate.setNume(produsDetails.getNume());
        produsToUpdate.setCost(produsDetails.getCost());
        Produs updatedProdus = produsRepository.save(produsToUpdate);
        return ResponseEntity.ok(updatedProdus);
    }

    // Delete a Produs by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdus(@PathVariable Long id) {
        if (!produsRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produsRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
