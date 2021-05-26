This program uses Google Cloud's [Vision API](https://cloud.google.com/vision) to get descriptions of images of your 
choice. Images can be added to the images folder to be used in the application.


The service of this application requires an authorization token in its header which expires every 30 minutes or so.
Follow the [instructions](https://cloud.google.com/vision/docs/setup) on Google's website to create a project, download
a JSON with an API key, and install Google's Cloud SDK. 
Run the following commands in the Cloud SDK Shell to receive an authorization token:
    set GOOGLE_APPLICATION_CREDENTIALS=YOUR_KEY_PATH
    gcloud auth application-default print-access-token 
Then paste the token after Authorization: Bearer in the header of the service. When it expires, run those two commands
again to receive a new token and paste the new token in the header. 

Complaints about incorrect image labeling can be directed at Google. 

Enjoy!



![Application Image](images/ApplicationScreenshot.PNG)