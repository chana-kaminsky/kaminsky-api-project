package kaminsky.visionapi;

import io.reactivex.rxjava3.core.Single;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Base64;

import static org.mockito.Mockito.*;

public class CloudVisionControllerTest
{
    private CloudVisionController controller;
    private CloudVisionService service;
    private Label descriptions;
    private Label scores;
    private Label errorMessage;
    private ImageView imageView;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void initialize()
    {
        // given
        givenCloudVisionController();
        CloudVisionServiceFactory factory = mock(CloudVisionServiceFactory.class);
        controller.factory = factory;
        doReturn(service).when(factory).newInstance(false);

        // when
        controller.initialize();

        // then
        verify(factory).newInstance(false);
        Assert.assertEquals(service, factory.newInstance(false));
    }

    @Test
    public void getImageDescription() throws IOException
    {
        // given
        givenCloudVisionController();

        CloudVisionRequest body = mock(CloudVisionRequest.class);
        body.requests = Arrays.asList(
                mock(CloudVisionRequest.AnnotateImageRequest.class),
                mock(CloudVisionRequest.AnnotateImageRequest.class)
        );
        body.requests.get(0).image = mock(CloudVisionRequest.AnnotateImageRequest.Image.class);
        body.requests.get(0).features = Arrays.asList(
                mock(CloudVisionRequest.AnnotateImageRequest.Features.class),
                mock(CloudVisionRequest.AnnotateImageRequest.Features.class)
        );

        String image = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get("images/tree.jpg")));
        doReturn(image).when(body.requests.get(0).image).getContent();
        doReturn("LABEL_DETECTION").when(body.requests.get(0).features.get(0)).getType();
        doReturn(20).when(body.requests.get(0).features.get(0)).getMaxResults();
        doReturn(Single.never()).when(service).getRequest(any());

        // when
        controller.getImageDescription();

        // then
        Assert.assertEquals(body.requests.get(0).image.getContent(), image);
        Assert.assertEquals(body.requests.get(0).features.get(0).getType(), "LABEL_DETECTION");
        Assert.assertEquals(body.requests.get(0).features.get(0).getMaxResults(), 20);
        verify(service).getRequest(any());
    }

    @Test
    public void onCloudVisionRun()
    {
        // given
        givenCloudVisionController();

        CloudVisionFeed feed = mock(CloudVisionFeed.class);
        feed.responses = Arrays.asList(
                mock(CloudVisionFeed.AnnotateImageResponse.class),
                mock(CloudVisionFeed.AnnotateImageResponse.class));
        feed.responses.get(0).labelAnnotations = Arrays.asList(
                mock(CloudVisionFeed.AnnotateImageResponse.LabelAnnotations.class),
                mock(CloudVisionFeed.AnnotateImageResponse.LabelAnnotations.class));

        String newDescription = feed.responses.get(0).labelAnnotations.get(0).getDescription();
        double newScore = Math.round(feed.responses.get(0).labelAnnotations.get(0).getScore() * 10000.0)/100.0;

        // when
        controller.onCloudVisionRun(feed);

        // then
        verify(errorMessage).setText("");
        verify(descriptions).setText("Image Descriptions:\n----------------------\n");
        verify(scores).setText("Confidence(%):\n-----------------\n");
        verify(imageView).setImage(any(Image.class));
        verify(descriptions, atLeast(1)).setText(descriptions.getText() + newDescription + "\n");
        verify(scores, atLeast(1)).setText(scores.getText() + newScore + "\n");
    }

    @Test
    public  void onErrorRun()
    {
        // given
        givenCloudVisionController();
        Throwable throwable = mock(Throwable.class);

        // when
        controller.onErrorRun(throwable);

        // then
        verify(errorMessage).setText("Error, invalid filename. Please try again.");
        verify(imageView).setImage(null);
        verify(descriptions).setText("");
        verify(scores).setText("");
        verify(throwable).printStackTrace();
    }

    private void givenCloudVisionController()
    {
        CloudVisionService service = mock(CloudVisionService.class);
        controller = new CloudVisionController(service);
        this.service = service;

        descriptions = mock(Label.class);
        scores = mock(Label.class);
        errorMessage = mock(Label.class);
        imageView = mock(ImageView.class);
        TextField filename = mock(TextField.class);

        controller.descriptions = descriptions;
        controller.scores = scores;
        controller.errorMessage = errorMessage;
        controller.imageView = imageView;
        controller.filename = filename;
    }
}
