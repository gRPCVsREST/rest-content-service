package org.grpcsvrest.rcs.service;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class ContentDto {
    private final int index;
    private final String content;
    private final boolean hasNext;

    public ContentDto(int index, String content, boolean hasNext) {
        this.index = index;
        this.content = content;
        this.hasNext = hasNext;
    }

    public int getIndex() {
        return index;
    }

    public String getContent() {
        return content;
    }

    public boolean hasNext() {
        return hasNext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentDto)) return false;
        ContentDto that = (ContentDto) o;
        return index == that.index &&
                hasNext == that.hasNext &&
                Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, content, hasNext);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("index", index)
                .add("content", content)
                .add("hasNext", hasNext)
                .toString();
    }
}
