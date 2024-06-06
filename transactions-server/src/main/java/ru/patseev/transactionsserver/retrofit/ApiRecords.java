package ru.patseev.transactionsserver.retrofit;

import retrofit2.Call;
import retrofit2.http.*;
import ru.patseev.transactionsserver.domain.enums.Department;
import ru.patseev.transactionsserver.domain.enums.ToolType;
import ru.patseev.transactionsserver.dto.StorageRecordDTO;
import ru.patseev.transactionsserver.dto.ToolDTO;
import ru.patseev.transactionsserver.dto.WorkerDTO;

import java.util.List;

public interface ApiRecords {

    @POST("records/add")
    Call<Void> addRecord(@Body StorageRecordDTO record);

    @GET("records/worker_lastname")
    Call<List<StorageRecordDTO>> getRecordsByWorkerLastName(
            @Query("workerLastName") String workerLastName,
            @Query("department") Department department
    );

    @GET("records")
    Call<List<StorageRecordDTO>> getRecords();

    @GET("records/amount")
    Call<Integer> getAmountByWorkerIdAndToolCode(
            @Query("workerId") Long workerId,
            @Query("toolCode") String toolCode
    );

    @GET("records/workerId")
    Call<List<StorageRecordDTO>> getRecordsByWorkerId(
            @Query("workerId") Long workerId,
            @Query("toolType") ToolType toolType,
            @Query("toolCode") String toolCode
    );

    @GET("records/record_by_worker_id_an_tool_code")
    Call<StorageRecordDTO> getRecordByWorkerIdAndToolCode(
            @Query("workerId") Long workerId,
            @Query("toolCode") String toolCode
    );

    @GET("records/workers/{id}")
    Call<WorkerDTO> getWorkerById(@Path("id") Long workerId);

    @GET("records/tools/{code}")
    Call<ToolDTO> getToolByCode(@Path("code") String toolCode);

    @PUT("records/update")
    Call<Void> updateRecord(@Body StorageRecordDTO record);
}
