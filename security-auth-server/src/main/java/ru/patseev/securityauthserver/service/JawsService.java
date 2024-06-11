package ru.patseev.securityauthserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.securityauthserver.dto.Jaw;
import ru.patseev.securityauthserver.dto.JawResponse;
import ru.patseev.securityauthserver.service.clients.RestTemplateJawClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JawsService {
    private final RestTemplateJawClient rest;

    //get all jaws
    public List<JawResponse> getJaws() {
        return rest.getJaws();
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
    public void deletePhoto(String fileName) {
        rest.deletePhoto(fileName);
    }
}