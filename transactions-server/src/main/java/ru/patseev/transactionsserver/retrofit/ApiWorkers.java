package ru.patseev.transactionsserver.retrofit;

import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.http.*;
import ru.patseev.transactionsserver.domain.Worker;

import java.util.List;

public interface ApiWorkers {

    @GET("/workers")
    Call<List<Worker>> getWorkers();

    @GET("/workers/storage_worker_by_department")
    Call<Worker> getStorageWorkerByDepartment(@Query("department") String department);

    @GET("workers/workers_by_department")
    Call<List<Worker>> getWorkersByDepartment(@Query("department") String department);

    @POST("worker/create")
    Call<Worker> createWorker(@Body Worker worker);

    @PUT("workers/update")
    Call<Worker> updateWorker(@Body Worker worker);

    @DELETE("workers/delete/{id}")
    Call<Void> deleteWorker(@Path("id") Long id);

    @GET
    Call<Worker> getWorker(@Path("id") Long id);
}
