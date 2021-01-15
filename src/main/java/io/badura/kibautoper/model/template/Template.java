
package io.badura.kibautoper.model.template;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "settings",
    "mappings"
})
public class Template {

    @JsonProperty("settings")
    private Settings settings;
    @JsonProperty("mappings")
    private Mappings mappings;

    @JsonProperty("settings")
    public Settings getSettings() {
        return settings;
    }

    @JsonProperty("settings")
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public Template withSettings(Settings settings) {
        this.settings = settings;
        return this;
    }

    @JsonProperty("mappings")
    public Mappings getMappings() {
        return mappings;
    }

    @JsonProperty("mappings")
    public void setMappings(Mappings mappings) {
        this.mappings = mappings;
    }

    public Template withMappings(Mappings mappings) {
        this.mappings = mappings;
        return this;
    }

}
