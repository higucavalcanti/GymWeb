package com.project.gymweb.controllers;

import com.project.gymweb.entities.Supplement;
import com.project.gymweb.services.SupplementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplements")
public class SupplementController {
    private final SupplementService supplementService;

    @Autowired
    public SupplementController(SupplementService supplementService) {
        this.supplementService = supplementService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Supplement>> getAllSupplements() {
        List<Supplement> supplements = supplementService.findAllSupplements();
        return ResponseEntity.status(HttpStatus.OK).body(supplements);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplement> getSupplementById(@PathVariable UUID id) {
        Supplement supplement = supplementService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(supplement);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Supplement>> searchSupplements(@RequestParam("name") String name) {
        List<Supplement> supplements = supplementService.searchSupplementsByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(supplements);
    }
}
