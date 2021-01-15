
package io.badura.kibautoper.model.template;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "_routing",
    "numeric_detection",
    "dynamic_date_formats",
    "dynamic",
    "_source",
    "dynamic_templates",
    "date_detection"
})
public class Mappings {

    @JsonProperty("_routing")
    private Routing routing;
    @JsonProperty("numeric_detection")
    private Boolean numericDetection;
    @JsonProperty("dynamic_date_formats")
    private List<String> dynamicDateFormats = null;
    @JsonProperty("dynamic")
    private Boolean dynamic;
    @JsonProperty("_source")
    private Source source;
    @JsonProperty("dynamic_templates")
    private List<Object> dynamicTemplates = null;
    @JsonProperty("date_detection")
    private Boolean dateDetection;

    @JsonProperty("_routing")
    public Routing getRouting() {
        return routing;
    }

    @JsonProperty("_routing")
    public void setRouting(Routing routing) {
        this.routing = routing;
    }

    public Mappings withRouting(Routing routing) {
        this.routing = routing;
        return this;
    }

    @JsonProperty("numeric_detection")
    public Boolean getNumericDetection() {
        return numericDetection;
    }

    @JsonProperty("numeric_detection")
    public void setNumericDetection(Boolean numericDetection) {
        this.numericDetection = numericDetection;
    }

    public Mappings withNumericDetection(Boolean numericDetection) {
        this.numericDetection = numericDetection;
        return this;
    }

    @JsonProperty("dynamic_date_formats")
    public List<String> getDynamicDateFormats() {
        return dynamicDateFormats;
    }

    @JsonProperty("dynamic_date_formats")
    public void setDynamicDateFormats(List<String> dynamicDateFormats) {
        this.dynamicDateFormats = dynamicDateFormats;
    }

    public Mappings withDynamicDateFormats(List<String> dynamicDateFormats) {
        this.dynamicDateFormats = dynamicDateFormats;
        return this;
    }

    @JsonProperty("dynamic")
    public Boolean getDynamic() {
        return dynamic;
    }

    @JsonProperty("dynamic")
    public void setDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
    }

    public Mappings withDynamic(Boolean dynamic) {
        this.dynamic = dynamic;
        return this;
    }

    @JsonProperty("_source")
    public Source getSource() {
        return source;
    }

    @JsonProperty("_source")
    public void setSource(Source source) {
        this.source = source;
    }

    public Mappings withSource(Source source) {
        this.source = source;
        return this;
    }

    @JsonProperty("dynamic_templates")
    public List<Object> getDynamicTemplates() {
        return dynamicTemplates;
    }

    @JsonProperty("dynamic_templates")
    public void setDynamicTemplates(List<Object> dynamicTemplates) {
        this.dynamicTemplates = dynamicTemplates;
    }

    public Mappings withDynamicTemplates(List<Object> dynamicTemplates) {
        this.dynamicTemplates = dynamicTemplates;
        return this;
    }

    @JsonProperty("date_detection")
    public Boolean getDateDetection() {
        return dateDetection;
    }

    @JsonProperty("date_detection")
    public void setDateDetection(Boolean dateDetection) {
        this.dateDetection = dateDetection;
    }

    public Mappings withDateDetection(Boolean dateDetection) {
        this.dateDetection = dateDetection;
        return this;
    }

}
