package kaminsky.visionapi;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

public class CloudVisionServiceTest
{
    CloudVisionServiceFactory factory = new CloudVisionServiceFactory();

    @Test
    public void getRequest() throws IOException
    {
        // given
        CloudVisionService service = factory.newInstance(false);

        // when
        String image = Base64.getEncoder().encodeToString(Files.readAllBytes(Paths.get("images/tree.jpg")));
        CloudVisionRequest body = new CloudVisionRequest(image, new String[] {"LABEL_DETECTION"}, new int[] {20});
        CloudVisionFeed feed = service.getRequest(body).blockingGet();

        // then
        assertNotNull(body);
        assertNotNull(body.requests);
        assertNotNull(body.requests.get(0).features);
        assertNotNull(body.requests.get(0).features.get(0).type);
        assertTrue(body.requests.get(0).features.get(0).maxResults > 0);
        assertNotNull(body.requests.get(0).image);
        assertNotNull(body.requests.get(0).image.content);

        assertNotNull(feed);
        assertNotNull(feed.responses);
        assertNotNull(feed.responses.get(0).labelAnnotations);
        assertNotNull(feed.responses.get(0).labelAnnotations.get(0).description);
        assertTrue(feed.responses.get(0).labelAnnotations.get(0).score > 0);
    }
}
