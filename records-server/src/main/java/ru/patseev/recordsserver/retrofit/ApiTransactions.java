package ru.patseev.recordsserver.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import ru.patseev.recordsserver.dto.ToolDTO;
import ru.patseev.recordsserver.dto.WorkerDTO;

public interface ApiTransactions {
    //add tool to transaction server
    @POST("transactions/tools/add")
    Call<ToolDTO> addTool(@Body ToolDTO tool);
    //add worker to transaction server
    @POST("transactions/workers/add")
    Call<WorkerDTO> addWorker(@Body WorkerDTO worker);
}
