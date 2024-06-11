package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.patseev.securityauthserver.dto.Jaw;
import ru.patseev.securityauthserver.dto.JawResponse;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RestTemplateJawClient {
    private final RestTemplate restTemplate;

    //get all jaws
    public List<JawResponse> getJaws() {
        ResponseEntity<List<JawResponse>> restExchange =
                restTemplate.exchange(
                        "http://jaws-server/jaws",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference<>() {
                        });
        return restExchange.getBody();
    }

    //add jaw
    public Jaw addJaw(Jaw jaw) {
        return restTemplate.postForObject(
                "http://my-gateway-server/jaws/add",
                jaw, Jaw.class);
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

        Resource file = multipartFile.getResource();
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        map.add("file", file);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new HttpEntity<>(
                map, headers);
        restTemplate.postForEntity(
                "http://my-gateway-server/jaws/photo/upload/{jawId}",
                requestEntity,
                Void.class,
                jawId);

    }

    //delete photo by file name
    public void deletePhoto(String fileName) {
                restTemplate.exchange(
                        "http://my-gateway-server/jaws/photo/delete?fileName={fileName}",
                        HttpMethod.DELETE,
                        null, Void.class, fileName);
    }
}
