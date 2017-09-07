package org.grpcsvrest.rcs.service;

import org.grpcvsrest.content.ContentProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ContentService {

    private final ContentProducer contentProducer;

    public ContentService(@Value("${content.resource:pokemons.txt}") String resource) {
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
