package ru.patseev.transactionsserver.retrofit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@RequiredArgsConstructor
@Component
public class ApiFactory {

    private final String BASE_URL = "http://localhost:8085/";

    Retrofit retrofitTools = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build();
    @Getter
    ApiRecords apiRecords = retrofitTools.create(ApiRecords.class);


}
