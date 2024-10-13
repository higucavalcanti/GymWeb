package com.project.gymweb.services;

import com.project.gymweb.dto.create.ExerciseDTO;
import com.project.gymweb.dto.view.ExerciseRO;
import com.project.gymweb.entities.Exercise;
import com.project.gymweb.repositories.ExerciseRepository;
import com.project.gymweb.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final RoutineRepository routineRepository;

    @Autowired
    public ExerciseService(ExerciseRepository exerciseRepository, RoutineRepository routineRepository) {
        this.exerciseRepository = exerciseRepository;
        this.routineRepository = routineRepository;
    }

    public List<ExerciseRO> findAll() {
        return exerciseRepository.findAll().stream().map(this::entityToRO).toList();
    }

    public List<ExerciseRO> findByRoutineId(UUID routineId) {
        return exerciseRepository.findAllByRoutineId(routineId).stream().map(this::entityToRO).toList();
    }

    public ExerciseRO getExercise(UUID id) {
        var exercise = exerciseRepository.findById(id).orElseThrow();
        return entityToRO(exercise);
    }

    public ExerciseRO createExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = dtoToEntity(exerciseDTO);
        var savedExercise = exerciseRepository.save(exercise);
        return entityToRO(savedExercise);
    }

    public ExerciseRO updateExercise(UUID id, ExerciseDTO exerciseDTO) {
        var exercise = exerciseRepository.findById(id).orElseThrow();
        var routine = routineRepository.findById(exerciseDTO.routineId()).orElseThrow();

        exercise.setName(exerciseDTO.name());
        exercise.setReps(exerciseDTO.reps());
        exercise.setSets(exerciseDTO.reps());
        exercise.setRoutine(routine);

        var savedExercise = exerciseRepository.save(exercise);

        return entityToRO(savedExercise);
    }

    public void deleteExercise(UUID id) {
        exerciseRepository.deleteById(id);
    }

    private Exercise dtoToEntity(ExerciseDTO exerciseDTO) {
        Exercise exercise = new Exercise();
        exercise.setId(UUID.randomUUID());
        exercise.setName(exerciseDTO.name());
        exercise.setSets(exerciseDTO.sets());
        exercise.setReps(exerciseDTO.reps());

        var routine = routineRepository.findById(exerciseDTO.routineId()).orElseThrow();

        exercise.setRoutine(routine);

        return exercise;
    }

    private ExerciseRO entityToRO(Exercise exercise) {
        return new ExerciseRO(exercise.getId(), exercise.getName(), exercise.getSets(), exercise.getReps(), exercise.getRoutine().getId());
    }
}
