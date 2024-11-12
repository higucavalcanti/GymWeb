package com.project.gymweb.controllers;

import com.project.gymweb.dto.create.ImcDTO;
import com.project.gymweb.dto.view.ImcRO;
import com.project.gymweb.services.ImcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("IMC")
@RequestMapping("/api/imcs")
public class ImcController {
    private final ImcService imcService;

    @Autowired
    public ImcController(ImcService imcService) {
        this.imcService = imcService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ImcRO>> imcs() {
        var imcs = imcService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(imcs);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ImcRO>> imcsByUserId(@PathVariable("userId") UUID userId) {
        var imcs = imcService.findAllByUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(imcs);
    }

    @GetMapping("/user/username/{username}")
    public ResponseEntity<List<ImcRO>> imcsByUserUsername(@PathVariable String username) {
        var imcs = imcService.findAllByUserUsername(username);
        return ResponseEntity.status(HttpStatus.OK).body(imcs);
    }

    @GetMapping("/user/email/{email}")
    public ResponseEntity<List<ImcRO>> imcsByUserEmail(@PathVariable String email) {
        var imcs = imcService.findAllByUserEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(imcs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImcRO> imc(@PathVariable UUID id) {
        var imc = imcService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(imc);
    }

    @PostMapping("/")
    public ResponseEntity<ImcRO> create(@RequestBody ImcDTO imcDTO) {
        var imc = imcService.createImc(imcDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(imc);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImcRO> update(@PathVariable UUID id, @RequestBody ImcDTO imcDTO) {
        var imc = imcService.updateImc(id, imcDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(imc);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        imcService.deleteImc(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
