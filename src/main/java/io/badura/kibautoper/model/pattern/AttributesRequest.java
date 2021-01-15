package io.badura.kibautoper.model.pattern;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "title"
})
public class AttributesRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("fields")
    private String fields;

    @JsonProperty("fields")
    public String getFields() {
        return fields;
    }
    @JsonProperty("fields")
    public void setFields(String fields) {
        this.fields = fields;
    }

    public AttributesRequest(String title, String fields) {
        this.title = title;
        this.fields = fields;
    }

    public AttributesRequest(String title) {
        this.title = title;
    }
}
