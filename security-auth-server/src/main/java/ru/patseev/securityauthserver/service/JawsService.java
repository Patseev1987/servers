package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.securityauthserver.dto.Jaw;
import ru.patseev.securityauthserver.dto.JawDTO;
import ru.patseev.securityauthserver.service.clients.RestTemplateJawClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JawsService {
    private final RestTemplateJawClient rest;

    //get all jaws
    public List<JawDTO> getStorageRecords() {
        return rest.getStorageRecords();
    }

    //add jaw
    public Jaw addJaw(Jaw jaw) {
        return rest.addJaw(jaw);
    }

    //update jaw
    public Jaw updateJaw(Jaw jaw) {
        return rest.updateJaw(jaw);
    }

    //delete jaw by id
    public void deleteJaw(Long jawId) {
        rest.deleteJaw(jawId);
    }

    //upload photo to server
    public void addPhoto(MultipartFile multipartFile, Long jawId) {
        rest.addPhoto(multipartFile, jawId);
    }

    //delete photo by file name
    public void deleteJaw(String fileName) {
        rest.deleteJaw(fileName);
    }
}