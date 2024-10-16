package com.project.gymweb.repositories;

import com.project.gymweb.entities.Supplement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SupplementRepository extends JpaRepository<Supplement, UUID> {
    List<Supplement> findByNameContainingIgnoreCase(String name);
}
