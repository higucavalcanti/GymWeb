package com.project.gymweb.services;

import com.project.gymweb.dto.create.RoutineDTO;
import com.project.gymweb.dto.view.RoutineRO;
import com.project.gymweb.entities.Routine;
import com.project.gymweb.repositories.RoutineRepository;
import com.project.gymweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RoutineService {
    private final RoutineRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public RoutineService(RoutineRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    public List<RoutineRO> findAll() {
        return repository.findAll().stream().map(this::entityToRO).toList();
    }

    public List<RoutineRO> findAllByUserId(UUID userId) {
        return repository.findAllByUserId(userId).stream().map(this::entityToRO).toList();
    }

    public List<RoutineRO> findAllByUserUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow();
        return repository.findAllByUserId(user.getId()).stream().map(this::entityToRO).toList();
    }

    public List<RoutineRO> findAllByUserEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        return repository.findAllByUserId(user.getId()).stream().map(this::entityToRO).toList();
    }


    public RoutineRO getRoutine(UUID routineId) {
        var routine = repository.findById(routineId).orElseThrow();
        return entityToRO(routine);
    }

    public RoutineRO createRoutine(RoutineDTO routineDTO) {
        Routine routine = dtoToEntity(routineDTO);
        var savedRoutine = repository.save(routine);
        return entityToRO(savedRoutine);
    }

    public RoutineRO updateRoutine(UUID id, RoutineDTO routineDTO) {
        var routine = repository.findById(id).orElseThrow();
        var user = userRepository.findById(routineDTO.userId()).orElseThrow();

        routine.setName(routineDTO.name());
        routine.setUser(user);

        var savedRoutine = repository.save(routine);

        return entityToRO(savedRoutine);
    }

    public void deleteRoutine(UUID id) {
        repository.deleteById(id);
    }

    private Routine dtoToEntity(RoutineDTO routineDTO) {
        Routine routine = new Routine();
        routine.setId(UUID.randomUUID());
        routine.setName(routineDTO.name());

        var user = userRepository.findById(routineDTO.userId()).orElseThrow();

        routine.setUser(user);

        return routine;
    }

    private RoutineRO entityToRO(Routine routine) {
        return new RoutineRO(routine.getId(), routine.getName(), routine.getUser().getId());
    }
}
