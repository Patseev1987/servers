package ru.patseev.recordsserver.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import ru.patseev.recordsserver.domain.StorageRecord;
import ru.patseev.recordsserver.domain.Tool;
import ru.patseev.recordsserver.domain.Worker;
import ru.patseev.recordsserver.dto.ToolDTO;
import ru.patseev.recordsserver.dto.WorkerDTO;

import java.util.List;

public interface ApiTransactions {

    @POST("transactions/tools/add")
    Call<ToolDTO> addTool(@Body ToolDTO tool);

    @POST("transactions/workers/add")
    Call<WorkerDTO> addWorker(@Body WorkerDTO worker);

    @PUT("transactions/tools/update")
    Call<ToolDTO> updateTool(@Body ToolDTO tool);

    @PUT("transaction/workers/update")
    Call<ToolDTO> updateWorker(@Body WorkerDTO worker);

    @GET("transactions/tools")
    Call<List<ToolDTO>> getTools();

    @GET("transactions/workers")
    Call<List<WorkerDTO>> getWorkers();

    @GET("transactions/workers/{id}")
    Call<WorkerDTO> getWorkerById(@Path("id") Long workerId);

    @GET("transactions/tools/{code}")
    Call<ToolDTO> getToolByCode(@Path("code") String toolCode);
}
