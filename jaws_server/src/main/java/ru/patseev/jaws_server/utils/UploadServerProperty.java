package ru.patseev.jaws_server.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "jaws-server")
@Data
public class UploadServerProperty {
    private String serverUrl;
}
