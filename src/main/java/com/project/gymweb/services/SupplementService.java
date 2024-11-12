package com.project.gymweb.services;

import com.project.gymweb.entities.Supplement;
import com.project.gymweb.repositories.SupplementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SupplementService {
    private final SupplementRepository supplementRepository;

    @Autowired
    public SupplementService(SupplementRepository supplementRepository) {
        this.supplementRepository = supplementRepository;
    }

    public List<Supplement> findAllSupplements() {
        return supplementRepository.findAll();
    }

    public List<Supplement> searchSupplementsByName(String name) {
        return supplementRepository.findByNameContainingIgnoreCase(name);
    }

    public Supplement findById(UUID id) {
        return supplementRepository.findById(id).orElseThrow();
    }
}
