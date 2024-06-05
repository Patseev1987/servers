package ru.patseev.transactionsserver.retrofit;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import retrofit2.Call;
import retrofit2.http.*;
import ru.patseev.transactionsserver.domain.Tool;

import java.util.List;


public interface ApiTools {

    @GET("tools/")
    Call<List<Tool>> getTools();

    @GET("tools/code")
    Call< List<Tool>> getToolsByCode(@Query("code") String code);

    @GET("tools/by_code/{code}")
    Call<Tool> getToolByCode(@Path("code") String code);

    @DELETE("tools/delete")
    Call<Void> deleteTool(@Query("code") String code);

    @PutMapping("tools/update")
    Call<Tool> updateTool(@Body Tool tool);

    @PostMapping("tool/add")
    Call<Tool> addTool(@Body Tool tool);
}
