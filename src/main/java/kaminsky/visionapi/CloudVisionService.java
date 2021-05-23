package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudVisionService
{
    @Headers({"Authorization: Bearer ya29.c.Kp8BAAgvGc5eSSSYoYrMyAGG_RH77fScMJ9a5p11yHYQh3LUT7Ct7V23Jz463XNzyJ1nSfeZPEmHJOj1op-YKM8wGkyyEUF4n-vWr76SRrcRq-DiTRyPu_PmR8IEAPeL7j67QuklZTZfdDYulbOVnVQr0d1_WFl9l3zhVCjA54qfOgaxbZX3-_w482WoICouszgw2_vErdNf-MvIn5uint11",
              "Content-Type: application/json; charset=utf-8"})

    @POST("/v1/images:annotate")

    Single<CloudVisionFeed> getRequest(@Body CloudVisionRequest body);
}

//     command line commands in Google Cloud SDK Shell to get access token:
//     set GOOGLE_APPLICATION_CREDENTIALS=C:\Users\Chana\Documents\Touro\Spring 2021\MCON 152\engaged-carving-311221-f915452605ff.json
//     gcloud auth application-default print-access-token
