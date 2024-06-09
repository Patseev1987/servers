package ru.patseev.securityauthserver.service.clients;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RestTemplateToolClient {
    private final RestTemplate restTemplate;

    public List<Tool> getTools (){
        ResponseEntity<List<Tool>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/tools",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){});
        return Objects.requireNonNull(restExchange.getBody());
    }

    public List<Tool> getToolsWithCode(String code){
        ResponseEntity<List<Tool>> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/tools/code?code={code}",
                        HttpMethod.GET,
                        null, new ParameterizedTypeReference <>(){},code);
        return Objects.requireNonNull(restExchange.getBody());
    }

    public Tool getToolByCode(String code){
        ResponseEntity<Tool> restExchange =
                restTemplate.exchange(
                        "http://my-gateway-server/records/tools/{code}",
                        HttpMethod.GET,
                        null, Tool.class,code);
        return restExchange.getBody();
    }

    public Tool addTool(Tool tool){
        ResponseEntity<Tool> result = restTemplate.exchange(
                "http://my-gateway-server/records/tools/add",
                HttpMethod.POST,
                null, Tool.class,tool
        );
        return result.getBody();
    }

    public Tool updateTool(Tool tool){
        ResponseEntity<Tool> result = restTemplate.exchange(
                "http://my-gateway-server/records/tools/update",
                HttpMethod.PUT,
                null, Tool.class,tool
        );
        return result.getBody();
    }
}
