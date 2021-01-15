
package io.badura.kibautoper.model.template;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "excludes",
    "includes",
    "enabled"
})
public class Source {

    @JsonProperty("excludes")
    private List<Object> excludes = null;
    @JsonProperty("includes")
    private List<Object> includes = null;
    @JsonProperty("enabled")
    private Boolean enabled;

    @JsonProperty("excludes")
    public List<Object> getExcludes() {
        return excludes;
    }

    @JsonProperty("excludes")
    public void setExcludes(List<Object> excludes) {
        this.excludes = excludes;
    }

    public Source withExcludes(List<Object> excludes) {
        this.excludes = excludes;
        return this;
    }

    @JsonProperty("includes")
    public List<Object> getIncludes() {
        return includes;
    }

    @JsonProperty("includes")
    public void setIncludes(List<Object> includes) {
        this.includes = includes;
    }

    public Source withIncludes(List<Object> includes) {
        this.includes = includes;
        return this;
    }

    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Source withEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

}
