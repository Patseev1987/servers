package ru.patseev.transactionsserver.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "urls")
@Data
public class MicroservicesUrls {
    private String toolServerUrl;
    private String workerServerUrl;
}
