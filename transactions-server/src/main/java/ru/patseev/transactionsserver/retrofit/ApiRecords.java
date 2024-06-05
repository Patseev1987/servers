package ru.patseev.transactionsserver.retrofit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.patseev.transactionsserver.domain.Department;
import ru.patseev.transactionsserver.domain.StorageRecord;
import ru.patseev.transactionsserver.domain.ToolType;

import java.util.List;

public interface ApiRecords {

    @POST("records/add")
    Call<StorageRecord> addRecord(@RequestBody StorageRecord record);

    @GET("records/worker_lastname")
    Call<List<StorageRecord>> getRecordsByWorkerLastName(
            @Query("workerLastName") String workerLastName,
            @Query("department") Department department
    );

    @GET("records")
    Call<List<StorageRecord>> getRecords();

    @GET("records/amount")
    Call<Integer> getAmountByWorkerIdAndToolCode(
            @Query("workerId") Long workerId,
            @Query("toolCode") String toolCode
    );

    @GET("records/workerId")
    Call<List<StorageRecord>> getRecordsByWorkerId(
            @Query("workerId") Long workerId,
            @Query("toolType") ToolType toolType,
            @Query("toolCode") String toolCode
    );

    @GET("/records/record_by_worker_id_an_tool_code")
    Call<StorageRecord> getRecordByWorkerIdAndToolCode(
            @Query("workerId") Long workerId,
            @Query("toolCode") String toolCode
    );
}
