package ru.patseev.securityauthserver.service.clients;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;
import ru.patseev.securityauthserver.dto.Tool;
import ru.patseev.securityauthserver.dto.Worker;

import java.io.IOException;
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
        return restTemplate.postForObject(
                "http://my-gateway-server/records/tools/add",
                tool,
                Tool.class
        );
    }

    public Tool updateTool(Tool tool){


        }




        HttpEntity<Tool> entity = new HttpEntity<>(tool);
        ResponseEntity<Void> restExchange = restTemplate.exchange(
                "http://my-gateway-server/records/tools/update",
                 HttpMethod.PUT,
                 entity,Void.class
        );
        return restExchange.getBody();
    }
}
