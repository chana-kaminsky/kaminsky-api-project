package kaminsky.visionapi;

import java.util.ArrayList;
import java.util.List;

public class CloudVisionRequest
{
    List<AnnotateImageRequest> requests = new ArrayList<>();

    public CloudVisionRequest(String content, String[] type, int[] maxResults)
    {
        this.requests.add(new AnnotateImageRequest(content, type, maxResults));
    }

    public static class AnnotateImageRequest
    {
        Image image;
        List<Features> features = new ArrayList<>();

        public AnnotateImageRequest(String content, String[] type, int[] maxResults)
        {
            this.image = new Image(content);
            for (int i = 0; i < type.length; i++)
            {
                this.features.add(new Features(type[i], maxResults[i]));
            }

        }

        public static class Image
        {
            String content;

            public Image(String content)
            {
                this.content = content;
            }

            public String getContent()
            {
                return content;
            }
        }

        public static class Features
        {
            String type;
            int maxResults = 1;

            public Features(String type, int maxResults)
            {
                this.type = type;
                this.maxResults = maxResults;
            }

            public String getType()
            {
                return type;
            }

            public int getMaxResults()
            {
                return maxResults;
            }
        }
    }
}
