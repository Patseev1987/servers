package ru.patseev.securityauthserver.service.utils;

import lombok.Getter;
import lombok.Setter;
import ru.patseev.securityauthserver.domain.Worker;

import java.util.List;
@Getter
@Setter
public class WorkerList {
    private List<Worker> workers;
}
