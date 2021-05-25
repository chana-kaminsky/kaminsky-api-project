package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudVisionService
{
    @Headers({"Authorization: Bearer ya29.c.Kp8BAAiWsMYkkdfhH8ob52fKegX9LCzlH59_DQWHpWMlXrvNe58U4DsTS064iLwsg1qlqddZ6l_tFVO_oFuyyoOBLxt_zlU8UOBvLX67BSXNSs0uMpnkiyiPJSWlqkrS0EQnAXFiW_l5rugbP1Hf44WnbJBwJIxznzpnC-zFYio2ip1TkFjg394UdFVJQB5dCxSuqgkwrzanS5a8CGe4_msK",
              "Content-Type: application/json; charset=utf-8"})

    @POST("/v1/images:annotate")

    Single<CloudVisionFeed> getRequest(@Body CloudVisionRequest body);
}

//     command line commands in Google Cloud SDK Shell to get access token:
//     set GOOGLE_APPLICATION_CREDENTIALS=C:\Users\Chana\Documents\Touro\Spring 2021\MCON 152\engaged-carving-311221-f915452605ff.json
//     gcloud auth application-default print-access-token
