package com.project.gymweb.repositories;

import com.project.gymweb.entities.Imc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImcRepository extends JpaRepository<Imc, UUID> {
    List<Imc> findAllByUserId(UUID userId);
}
