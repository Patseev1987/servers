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
            .baseUrl(urls.getServerUrl())
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    @Getter
    ApiRecords apiTools = retrofitTools.create(ApiRecords.class);


}
