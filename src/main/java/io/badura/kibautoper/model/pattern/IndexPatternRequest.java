package io.badura.kibautoper.model.pattern;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "attributes",
        "fields"
})
public class IndexPatternRequest {

    @JsonProperty("attributes")
    private AttributesRequest attributes;

    @JsonProperty("attributes")
    public AttributesRequest getAttributes() {
        return attributes;
    }

    @JsonProperty("attributes")
    public void setAttributes(AttributesRequest attributes) {
        this.attributes = attributes;
    }

    public IndexPatternRequest(AttributesRequest attributes) {
        this.attributes = attributes;
    }


}