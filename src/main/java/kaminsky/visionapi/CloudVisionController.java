package kaminsky.visionapi;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * CloudVisionController is a class that initializes and
 * retrieves data from a service for Google Cloud Vision API.
 */
public class CloudVisionController
{
    @FXML
    Label descriptions, scores, errorMessage;

    @FXML
    ImageView imageView;

    @FXML
    TextField filename;

    CloudVisionServiceFactory factory = new CloudVisionServiceFactory();
    CloudVisionService service;

    public CloudVisionController(CloudVisionService service)
    {
        this.service = service;
    }

    /**
     * Method called when the application is first started.
     * Initializes a CloudVisionService.
     */
    public void initialize()
    {
        service = factory.newInstance(false);
    }

    /**
     * Method called when Go! button on cloudvision_application.fxml
     * is clicked. Disposable requests data from the API with body
     * request json and works with the feed as the data gets downloaded.
     */
    public void getImageDescription()
    {
        String image = getImageString(filename.getText());
        // length of detectionTypes must equal length of maxResults
        String[] detectionTypes = {"LABEL_DETECTION"};
        int[] maxResults = {20};

        CloudVisionRequest body = new CloudVisionRequest(image, detectionTypes, maxResults);

        Disposable disposableFeed = service.getRequest(body)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribe(this::onCloudVision, this::onError);
    }

    /**
     * Takes a filename of an image as a String
     * @return the image as a base64 encoded image String
     */
    private String getImageString(String filename)
    {
        String image = "";
        try
        {
            Path path = Paths.get("images/"+filename);
            image = Base64.getEncoder().encodeToString(Files.readAllBytes(path));
        }
        catch (Exception exception)
        {
            onError(exception);
        }
        return image;
    }

    /**
     * Gets the image descriptions and confidence scores
     * from the feed and displays them in the application.
     * @param feed
     */
    public void onCloudVision(CloudVisionFeed feed)
    {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                onCloudVisionRun(feed);
            }
        });
    }

    public void onCloudVisionRun(CloudVisionFeed feed)
    {
        errorMessage.setText("");
        descriptions.setText("Image Descriptions:\n----------------------\n");
        scores.setText("Confidence(%):\n-----------------\n");
        imageView.setImage(new Image(new File("images/" + filename.getText()).toURI().toString()));
        for (int i = 0; i < feed.responses.get(0).labelAnnotations.size(); i++)
        {
            String newDescription = feed.responses.get(0).labelAnnotations.get(i).getDescription();
            double newScore = Math.round(feed.responses.get(0).labelAnnotations.get(i).getScore() * 10000.0)/100.0;
            descriptions.setText(descriptions.getText() + newDescription + "\n");
            scores.setText(scores.getText() + newScore + "\n");
        }
    }

    /**
     * Method called when attempt to call API with invalid filename.
     * @param throwable
     */
    public void onError(Throwable throwable)
    {
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                onErrorRun(throwable);
            }
        });
    }

    public void onErrorRun(Throwable throwable)
    {
        errorMessage.setText("Error, invalid filename. Please try again.");
        imageView.setImage(null);
        descriptions.setText("");
        scores.setText("");
        throwable.printStackTrace();
    }
}
