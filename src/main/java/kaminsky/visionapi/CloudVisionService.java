package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudVisionService
{
    @Headers({"Authorization: Bearer ya29.c.Kp8BAAil1IpoHv1-r8mUiGa0VHAOgawOBn00LE5lAdM5xNDoxr3skwBxTcAauprhnTFHM1l550yea7fzJHMtsXvzA8KAI23Ylyou2Sjc1OtNnsA_fhEPTAyZUJtWiIK2hyVSnyiVrdHkHvC1x-JohLkEMOl0GK6fml57If_f5AWrSWw63ZZHRwglUlDxrXTC9wYr3TkezbRAZNBSrk5ZCr3B",
              "Content-Type: application/json; charset=utf-8"})

    @POST("/v1/images:annotate")

    Single<CloudVisionFeed> getRequest(@Body CloudVisionRequest body);
}

//     command line commands in Google Cloud SDK Shell to get access token:
//     set GOOGLE_APPLICATION_CREDENTIALS=C:\Users\Chana\Documents\Touro\Spring 2021\MCON 152\engaged-carving-311221-f915452605ff.json
//     gcloud auth application-default print-access-token
