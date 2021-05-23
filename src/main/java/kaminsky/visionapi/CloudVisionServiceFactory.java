package kaminsky.visionapi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Create CloudVisionService classes using Retrofit.
 */
public class CloudVisionServiceFactory
{
    public CloudVisionService newInstance(boolean logging)
    {
        OkHttpClient client = new OkHttpClient();

        if (logging)
        {
            // adapted from https://futurestud.io/tutorials/retrofit-2-log-requests-and-responses
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://vision.googleapis.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .client(client)
                .build();

        CloudVisionService service = retrofit.create(CloudVisionService.class);

        return service;
    }
}