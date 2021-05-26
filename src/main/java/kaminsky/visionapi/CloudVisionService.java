package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface CloudVisionService
{
    @Headers({"Authorization: Bearer ya29.c.Kp8BAAjJ6t048XJP8Hh2YRDsPXbfa13LcoRzc4cVjylIPcl5-7LuPVpqb65gj0iZv3Sj1yekZSvy_bCCW__Z6egkn-N-xwZnjMKSA7dpkxwvachBwf_FXMKizfApYCGMo0ru96ouHGIR3tKCLBL9FzJidiD5uTHzwZXU2BD4ooU_BZ35D7V91izzQCxQ265QgcUgyo0j2aCn8Ve0kUzdel8i",
              "Content-Type: application/json; charset=utf-8"})

    @POST("/v1/images:annotate")

    Single<CloudVisionFeed> getRequest(@Body CloudVisionRequest body);
}

/*
     command line commands in Cloud SDK Shell to get access token:
     set GOOGLE_APPLICATION_CREDENTIALS=KEY_PATH
     gcloud auth application-default print-access-token

     See README for more details.
*/