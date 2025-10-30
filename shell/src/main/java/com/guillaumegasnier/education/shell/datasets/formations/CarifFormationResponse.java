package com.guillaumegasnier.education.shell.datasets.formations;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CarifFormationResponse {

    @JsonProperty("_scroll_id")
    private String scrollId;

    private CarifFormationResponse.HitsWrapper hits;

    @Getter
    @Setter
    @ToString
    public static class HitsWrapper {
        private CarifFormationResponse.Total total;
        private List<CarifFormationResponse.Hit> hits;
    }

    @Getter
    @Setter
    @ToString
    public static class Total {
        private int value;
        private String relation;
    }

    @Getter
    @Setter
    @ToString
    public static class Hit {
        @JsonProperty("_id")
        private String id;

        @JsonProperty("_score")
        private double score;

        @JsonProperty("_source")
        private CarifFormationDataset source;
    }
}
