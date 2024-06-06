package ru.patseev.recordsserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.patseev.recordsserver.domain.Place;
import ru.patseev.recordsserver.domain.StorageRecord;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.domain.enums.Department;
import ru.patseev.recordsserver.domain.enums.ToolType;
import ru.patseev.recordsserver.domain.enums.WorkerType;
import ru.patseev.recordsserver.repositoryies.StorageRecordRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class StorageRecordService {
    private final StorageRecordRepository storageRecordRepository;
    private final ToolService toolService;
    private final WorkerService workerService;

    {
           initData();
    }

    //get all records
    public List<StorageRecord> getAllRecords() {
        return storageRecordRepository.findAll();
    }

    //get records with param
    public List<StorageRecord> getStorageRecordsByWorkerIdWithParam(Long workerId, ToolType toolType, String toolCode) {
        return storageRecordRepository.findAllByWorkerIdWithToolTypeAndCode(workerId, toolType, toolCode);
    }

    //get records by worker lastname
    public List<StorageRecord> getAllStorageRecordsByWorkerLastName(Department department, String lastName) {
        return storageRecordRepository.findAllByWorkerLastname(department, lastName);
    }

    //add record
    public StorageRecord save(StorageRecord storageRecord) {
        return storageRecordRepository.save(storageRecord);
    }

    //get amount by worker id and tool code
    public Integer getAmountByWorkerIdAndToolCode(Long workerId, String toolCode) {

        var records = storageRecordRepository.findAllByWorkerId(workerId);
        records = records.stream()
                .filter(record -> record.getTool().getCode().equals(toolCode))
                .toList();
        if (records.isEmpty()) {
            return -1;
        } else {
            return records.get(0).getAmount();
        }
    }

    //get record by workerId and toolCode
    public Optional<StorageRecord> getStorageRecordByWorkerAndTool(Long workerId, String toolCode) {
        var worker = workerService.getWorkerById(workerId);
        var tool = toolService.getToolByCode(toolCode);
        return storageRecordRepository.findStorageRecordByWorkerAndTool(worker, tool);
    }

    private void initData() {

        new Thread(() -> {
            try {


                var worker3 = Worker.builder()
                        .id(3L)
                        .firstName("kladovaya")
                        .lastName("kladovaya")
                        .login("kkk")
                        .type(WorkerType.STORAGE_WORKER)
                        .department(Department.DEPARTMENT_19)
                        .joinDate(LocalDate.now())
                        .patronymic("kladovaya")
                        .build();

                var tool1 = Tool.builder()
                        .code("2004-9090")
                        .name("CNMG120404")
                        .description("Inner")
                        .type(ToolType.CUTTING)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool2 = Tool.builder()
                        .code("2004-1010")
                        .name("CNMG120408")
                        .description("Inner")
                        .type(ToolType.CUTTING)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool3 = Tool.builder()
                        .code("2004-3030")
                        .name("DNMG150604")
                        .description("Inner")
                        .type(ToolType.CUTTING)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool4 = Tool.builder()
                        .code("2004-8020")
                        .name("DNMG150604")
                        .description("Inner")
                        .type(ToolType.CUTTING)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool5 = Tool.builder()
                        .code("8700-0001")
                        .name("some tool")
                        .description("Some Tool")
                        .type(ToolType.MEASURE)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool6 = Tool.builder()
                        .code("8700-0002")
                        .name("measure tool")
                        .description("some tool")
                        .type(ToolType.MEASURE)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();

                var tool7 = Tool.builder()
                        .code("6332-9000")
                        .name("for machine")
                        .description("helper")
                        .type(ToolType.HELPERS)
                        .place(new Place("1", "2", "3"))
                        .icon("http://ff.ru")
                        .build();
                Thread.sleep(3000);


                var record1 = StorageRecord.builder()
                        .amount(100)
                        .tool(tool1)
                        .worker(worker3)
                        .build();

                var record2 = StorageRecord.builder()
                        .amount(200)
                        .tool(tool2)
                        .worker(worker3)
                        .build();

                var record3 = StorageRecord.builder()
                        .amount(50)
                        .tool(tool3)
                        .worker(worker3)
                        .build();

                var record4 = StorageRecord.builder()
                        .amount(1000)
                        .tool(tool4)
                        .worker(worker3)
                        .build();

                var record5 = StorageRecord.builder()
                        .amount(10)
                        .tool(tool5)
                        .worker(worker3)
                        .build();

                var record6 = StorageRecord.builder()
                        .amount(20)
                        .tool(tool6)
                        .worker(worker3)
                        .build();

                var record7 = StorageRecord.builder()
                        .amount(4)
                        .tool(tool7)
                        .worker(worker3)
                        .build();


                var records = List.of(record1, record2, record3, record4, record5, record6, record7);
                Thread.sleep(3000);
                storageRecordRepository.saveAll(records);
            } catch (InterruptedException e) {

            }
        }).start();

    }
}
