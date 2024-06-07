package ru.patseev.recordsserver.services;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patseev.recordsserver.domain.Place;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.enums.ToolType;
import ru.patseev.recordsserver.repositoryies.ToolRepository;
import ru.patseev.recordsserver.retrofit.ApiFactory;
import ru.patseev.recordsserver.utils.MyMapper;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToolService {
    private final ToolRepository toolRepository;
    private final ApiFactory api;
    private final MyMapper mapper;

    {
      // initData();
    }

    //get all tools
    public List<Tool> getAllTools() {
        return toolRepository.findAll();
    }
    //get all tools which contain code
    public List<Tool> getToolByCodeLike(String code) {
        return toolRepository.findAllByCodeLike(code);
    }
    //get tool by code
    public Tool getToolByCode(String code) {
        return toolRepository.findByCode(code);
    }
    //add tool
    public Tool addTool(Tool tool) {
        var newTool = toolRepository.save(tool);
      var sss =  api.getApiTransactions().addTool(mapper.toToolDTO(tool));
        try {
            System.out.println("toolDTO -> " + sss.execute().body());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newTool;
    }


    private void initData(){
        new Thread( () -> {

            var tool1 = Tool.builder()
                    .code("2004-9090")
                    .name("CNMG120404")
                    .description("Inner")
                    .type(ToolType.CUTTING)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool2 = Tool.builder()
                    .code("2004-1010")
                    .name("CNMG120408")
                    .description("Inner")
                    .type(ToolType.CUTTING)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool3 = Tool.builder()
                    .code("2004-3030")
                    .name("DNMG150604")
                    .description("Inner")
                    .type(ToolType.CUTTING)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool4 = Tool.builder()
                    .code("2004-8020")
                    .name("DNMG150604")
                    .description("Inner")
                    .type(ToolType.CUTTING)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool5 = Tool.builder()
                    .code("8700-0001")
                    .name("some tool")
                    .description("Some Tool")
                    .type(ToolType.MEASURE)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool6 = Tool.builder()
                    .code("8700-0002")
                    .name("measure tool")
                    .description("some tool")
                    .type(ToolType.MEASURE)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            var tool7 = Tool.builder()
                    .code("6332-9000")
                    .name("for machine")
                    .description("helper")
                    .type(ToolType.HELPERS)
                    .place(new Place("1","2","3"))
                    .icon("http://ff.ru")
                    .build();

            try {
                Thread.sleep(1500);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            var tools = List.of(tool7,tool1,tool2,tool3,tool4,tool5,tool6);
            tools.forEach(tool -> addTool(tool));
        }
        ).start();
    }
}




