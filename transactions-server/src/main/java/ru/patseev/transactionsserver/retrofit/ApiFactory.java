package ru.patseev.transactionsserver.retrofit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.patseev.transactionsserver.properties.MicroservicesUrls;

@RequiredArgsConstructor
@Component
public class ApiFactory {

    private final MicroservicesUrls urls;

    Retrofit retrofitTools = new Retrofit.Builder()
            .baseUrl(urls.getToolServerUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Retrofit retrofitWorkers = new Retrofit.Builder()
            .baseUrl(urls.getWorkerServerUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Getter
    ApiTools apiTools = retrofitTools.create(ApiTools.class);

    @Getter
    ApiWorkers apiWorkers = retrofitWorkers.create(ApiWorkers.class);
}
