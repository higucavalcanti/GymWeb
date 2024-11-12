package com.project.gymweb.services;

import com.project.gymweb.dto.create.ImcDTO;
import com.project.gymweb.dto.view.ImcRO;
import com.project.gymweb.entities.Imc;
import com.project.gymweb.repositories.ImcRepository;
import com.project.gymweb.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ImcService {
    private final ImcRepository imcRepository;
    private final UserRepository userRepository;

    @Autowired
    public ImcService(ImcRepository imcRepository, UserRepository userRepository) {
        this.imcRepository = imcRepository;
        this.userRepository = userRepository;
    }

    public List<ImcRO> findAll() {
        return imcRepository.findAll().stream().map(this::entityToRO).toList();
    }

    public List<ImcRO> findAllByUser(UUID userId) {
        return imcRepository.findAllByUserId(userId).stream().map(this::entityToRO).toList();
    }

    public ImcRO findById(UUID id) {
        var imc = imcRepository.findById(id).orElseThrow();
        return entityToRO(imc);
    }

    public List<ImcRO> findAllByUserUsername(String username) {
        var user = userRepository.findByUsername(username).orElseThrow();
        return imcRepository.findAllByUserId(user.getId()).stream().map(this::entityToRO).toList();
    }

    public List<ImcRO> findAllByUserEmail(String email) {
        var user = userRepository.findByEmail(email).orElseThrow();
        return imcRepository.findAllByUserId(user.getId()).stream().map(this::entityToRO).toList();
    }

    public ImcRO createImc(ImcDTO imcDTO) {
        Imc imc = dtoToEntity(imcDTO);
        var savedImc = imcRepository.save(imc);
        return entityToRO(savedImc);
    }

    public ImcRO updateImc(UUID id, ImcDTO imcDTO) {
        var imc = imcRepository.findById(id).orElseThrow();
        var user = userRepository.findById(imcDTO.userId()).orElseThrow();

        imc.setGender(imcDTO.gender());
        imc.setWeight(imcDTO.weight());
        imc.setHeight(imcDTO.height());

        imc.setValue(calculateImc(imc.getWeight(), imc.getHeight()));
        imc.setDate(new Date());
        imc.setUser(user);

        var savedImc = imcRepository.save(imc);

        return entityToRO(savedImc);
    }

    public void deleteImc(UUID id) {
        imcRepository.deleteById(id);
    }

    private Imc dtoToEntity(ImcDTO imcDTO) {
        Imc imc = new Imc();
        imc.setId(UUID.randomUUID());
        imc.setGender(imcDTO.gender());
        imc.setWeight(imcDTO.weight());
        imc.setHeight(imcDTO.height());

        imc.setValue(calculateImc(imc.getWeight(), imc.getHeight()));
        imc.setDate(new Date());

        var user = userRepository.findById(imcDTO.userId()).orElseThrow();

        imc.setUser(user);

        return imc;
    }

    private ImcRO entityToRO(Imc imc) {
        return new ImcRO(imc.getId(), imc.getGender(), imc.getWeight(), imc.getHeight(), imc.getUser().getId());
    }

    private Double calculateImc(Double weight, Double height) {
        return weight / (height * height);
    }
}
