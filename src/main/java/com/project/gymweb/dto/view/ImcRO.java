package com.project.gymweb.dto.view;

import java.util.UUID;

public record ImcRO(UUID id, String gender, Double weight, Double height, UUID userId) {
}
