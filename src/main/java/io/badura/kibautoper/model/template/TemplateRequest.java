
package io.badura.kibautoper.model.template;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "template",
    "index_patterns",
    "composed_of"
})
public class TemplateRequest {

    @JsonProperty("template")
    private Template template;
    @JsonProperty("index_patterns")
    private List<String> indexPatterns = null;
    @JsonProperty("composed_of")
    private List<Object> composedOf = null;

    @JsonProperty("template")
    public Template getTemplate() {
        return template;
    }

    @JsonProperty("template")
    public void setTemplate(Template template) {
        this.template = template;
    }

    public TemplateRequest withTemplate(Template template) {
        this.template = template;
        return this;
    }

    @JsonProperty("index_patterns")
    public List<String> getIndexPatterns() {
        return indexPatterns;
    }

    @JsonProperty("index_patterns")
    public void setIndexPatterns(List<String> indexPatterns) {
        this.indexPatterns = indexPatterns;
    }

    public TemplateRequest withIndexPatterns(List<String> indexPatterns) {
        this.indexPatterns = indexPatterns;
        return this;
    }

    @JsonProperty("composed_of")
    public List<Object> getComposedOf() {
        return composedOf;
    }

    @JsonProperty("composed_of")
    public void setComposedOf(List<Object> composedOf) {
        this.composedOf = composedOf;
    }

    public TemplateRequest withComposedOf(List<Object> composedOf) {
        this.composedOf = composedOf;
        return this;
    }

    private TemplateRequest(String lifecycleName, Boolean mappingRoutingRequered, Boolean numericDetection, Boolean dynamicMapping, Boolean sourceEnabled,
                           Boolean dateDetection, List<String> indexPatterns) {
        Lifecycle lifecycle = new Lifecycle().withName(lifecycleName);
        Index index = new Index().withLifecycle(lifecycle);
        Settings settings = new Settings().withIndex(index);
        List<String> dynamicDateFormats = List.of("strict_date_optional_time","yyyy/MM/dd HH:mm:ss Z||yyyy/MM/dd Z");

        Routing routing = new Routing().withRequired(mappingRoutingRequered);
        Source source = new Source().withEnabled(sourceEnabled);
        Mappings mappings = new Mappings().withRouting(routing)
                .withNumericDetection(numericDetection)
                .withDynamicDateFormats(dynamicDateFormats)
                .withDynamic(dynamicMapping)
                .withDynamicTemplates(List.of())
                .withSource(source)
                .withDateDetection(dateDetection);
        this.template = new Template().withSettings(settings).withMappings(mappings);
        this.indexPatterns = indexPatterns;
    }

    public TemplateRequest(String indexPattern){
        this("7_days_to_drop",
                false,
                false,
                true,
                true,
                true,List.of(indexPattern+"*"));
    }
}
