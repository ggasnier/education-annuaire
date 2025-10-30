package com.guillaumegasnier.education.shell.datasets;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
public class CarifResponse<T extends CarifDataset> {

    @JsonProperty("_scroll_id")
    protected String scrollId;

    protected HitsWrapper hits;

    @Getter
    @Setter
    @ToString
    public static class HitsWrapper {
        protected Total total;
        protected List<CarifResponse.Hit> hits;
    }

    @Getter
    @Setter
    @ToString
    public static class Total {
        protected int value;
        protected String relation;
    }

    @Getter
    @Setter
    @ToString
    public static class Hit<T extends CarifDataset> {
        @JsonProperty("_id")
        private String id;

        @JsonProperty("_score")
        private double score;

        @JsonProperty("_source")
        private T source;
    }
}
