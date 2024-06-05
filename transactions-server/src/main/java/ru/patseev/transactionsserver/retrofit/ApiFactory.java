package ru.patseev.transactionsserver.retrofit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.patseev.transactionsserver.properties.MicroservicesUrls;

@RequiredArgsConstructor
@Component
public class ApiFactory {

    private final String BASE_URL = "http://localhost:8085/";

    Retrofit retrofitTools = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @Getter
    ApiRecords apiTools = retrofitTools.create(ApiRecords.class);


}
