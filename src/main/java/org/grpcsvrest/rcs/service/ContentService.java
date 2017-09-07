package org.grpcsvrest.rcs.service;

import org.grpcvsrest.content.ContentProducer;

import java.io.IOException;

public class ContentService {

    private final ContentProducer contentProducer;

    public ContentService(String resource) {
        try {
            contentProducer = new ContentProducer(resource);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ContentDto getByIndex(int i) {
        return new ContentDto(i, contentProducer.content().get(i), i < contentProducer.content().size() - 1);
    }
}
