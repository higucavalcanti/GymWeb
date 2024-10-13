package com.project.gymweb.dto.create;

import java.util.UUID;

public record ExerciseDTO(String name, Long sets, Long reps, UUID routineId) {
}
