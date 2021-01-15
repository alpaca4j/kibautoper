
package io.badura.kibautoper.model.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "index"
})
public class Settings {

    @JsonProperty("index")
    private Index index;

    @JsonProperty("index")
    public Index getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(Index index) {
        this.index = index;
    }

    public Settings withIndex(Index index) {
        this.index = index;
        return this;
    }

}
