package kaminsky.visionapi;

import java.util.List;

public class CloudVisionFeed
{
    List<AnnotateImageResponse> responses;

    public static class AnnotateImageResponse
    {
        List<LabelAnnotations> labelAnnotations;

        public static class LabelAnnotations
        {
            String description;
            double score;

            public String getDescription()
            {
                return description;
            }

            public double getScore()
            {
                return score;
            }
        }

    }
}
