package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.securityauthserver.dto.Jaw;
import ru.patseev.securityauthserver.dto.JawDTO;
import ru.patseev.securityauthserver.dto.StorageRecord;
import ru.patseev.securityauthserver.dto.enums.Department;
import ru.patseev.securityauthserver.dto.enums.ToolType;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateJawClient {
    private final RestTemplate restTemplate;

    //get all jaws
    public List<JawDTO> getStorageRecords() {
        ResponseEntity<List<JawDTO>> restExchange =
                restTemplate.exchange(
                        "http://jaws-server/jaws",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });
        return restExchange.getBody();
    }

    //add jaw
    public Jaw addJaw(Jaw jaw) {
        ResponseEntity<Jaw> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/add",
                        HttpMethod.POST,
                        null, Jaw.class, jaw);
        return restExchange.getBody();
    }

    //update jaw
    public Jaw updateJaw(Jaw jaw) {
        ResponseEntity<Jaw> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/update",
                        HttpMethod.PUT,
                        null, Jaw.class, jaw);
        return restExchange.getBody();
    }

    //delete jaw by id
    public void deleteJaw(Long jawId) {
        ResponseEntity<Void> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/delete/{jawId}",
                        HttpMethod.DELETE,
                        null, Void.class, jawId);
    }

    //upload photo to server
    public void addPhoto(MultipartFile multipartFile, Long jawId) {
        ResponseEntity<Void> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/photo/upload/{jawId}",
                        HttpMethod.POST,
                        null, Void.class,multipartFile, jawId);
    }

    //delete photo by file name
    public void deleteJaw(String fileName) {
        ResponseEntity<Void> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/delete/photo?filename={fileName}",
                        HttpMethod.DELETE,
                        null, Void.class, fileName);
    }
}
