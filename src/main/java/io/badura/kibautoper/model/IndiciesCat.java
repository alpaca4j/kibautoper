package io.badura.kibautoper.model;

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
        "health",
        "status",
        "index",
        "uuid",
        "pri",
        "rep",
        "docs.count",
        "docs.deleted",
        "store.size",
        "pri.store.size"
})
public class IndiciesCat {

    @JsonProperty("health")
    private String health;
    @JsonProperty("status")
    private String status;
    @JsonProperty("index")
    private String index;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("pri")
    private String pri;
    @JsonProperty("rep")
    private String rep;
    @JsonProperty("docs.count")
    private String docsCount;
    @JsonProperty("docs.deleted")
    private String docsDeleted;
    @JsonProperty("store.size")
    private String storeSize;
    @JsonProperty("pri.store.size")
    private String priStoreSize;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("health")
    public String getHealth() {
        return health;
    }

    @JsonProperty("health")
    public void setHealth(String health) {
        this.health = health;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("index")
    public String getIndex() {
        return index;
    }

    @JsonProperty("index")
    public void setIndex(String index) {
        this.index = index;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("pri")
    public String getPri() {
        return pri;
    }

    @JsonProperty("pri")
    public void setPri(String pri) {
        this.pri = pri;
    }

    @JsonProperty("rep")
    public String getRep() {
        return rep;
    }

    @JsonProperty("rep")
    public void setRep(String rep) {
        this.rep = rep;
    }

    @JsonProperty("docs.count")
    public String getDocsCount() {
        return docsCount;
    }

    @JsonProperty("docs.count")
    public void setDocsCount(String docsCount) {
        this.docsCount = docsCount;
    }

    @JsonProperty("docs.deleted")
    public String getDocsDeleted() {
        return docsDeleted;
    }

    @JsonProperty("docs.deleted")
    public void setDocsDeleted(String docsDeleted) {
        this.docsDeleted = docsDeleted;
    }

    @JsonProperty("store.size")
    public String getStoreSize() {
        return storeSize;
    }

    @JsonProperty("store.size")
    public void setStoreSize(String storeSize) {
        this.storeSize = storeSize;
    }

    @JsonProperty("pri.store.size")
    public String getPriStoreSize() {
        return priStoreSize;
    }

    @JsonProperty("pri.store.size")
    public void setPriStoreSize(String priStoreSize) {
        this.priStoreSize = priStoreSize;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}