package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudVisionService
{
    @Headers({"Authorization: Bearer ya29.c.Kp8BAAjauHvzPdpSk1PWQWlT5-9gXjg1gdXA5NtcSaB8khsJdrpIWgNNt3QMNnYgORKqph4tirp4vn-oWYX1619z9PczRgZ6r-zvIc_2lHNKm4nE5GEUZ3pAWqf5352tkkU1NVHZ-U0UaAZYwTI_zP2bWuTvElWb_pxipai_xwJfags0cTdg01mZqPt6AHQjbCHassqRR9N2MXD06TLkshYJ",
              "Content-Type: application/json; charset=utf-8"})

    @POST("/v1/images:annotate")

    Single<CloudVisionFeed> getRequest(@Body CloudVisionRequest body);
}

//     command line commands in Google Cloud SDK Shell to get access token:
//     set GOOGLE_APPLICATION_CREDENTIALS=C:\Users\Chana\Documents\Touro\Spring 2021\MCON 152\engaged-carving-311221-f915452605ff.json
//     gcloud auth application-default print-access-token
