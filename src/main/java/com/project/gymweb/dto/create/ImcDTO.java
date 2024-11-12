package com.project.gymweb.dto.create;

import java.util.UUID;

public record ImcDTO(String gender, Double weight, Double height, UUID userId) {
}
