package org.grpcsvrest.rcs.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Content {
    @JsonProperty("id")
    private final int id;
    @JsonProperty("content")
    private final String content;
    @JsonProperty("next_id")
    private final Integer nextId;

    @JsonCreator
    public Content(
            @JsonProperty("id") int id,
            @JsonProperty("content") String content,
            @JsonProperty("next_id") Integer nextId) {
        this.id = id;
        this.content = content;
        this.nextId = nextId;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Integer getNextId() {
        return nextId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Content)) return false;
        Content content1 = (Content) o;
        return id == content1.id &&
                Objects.equals(content, content1.content) &&
                Objects.equals(nextId, content1.nextId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, nextId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("content", content)
                .add("nextId", nextId)
                .toString();
    }
}
