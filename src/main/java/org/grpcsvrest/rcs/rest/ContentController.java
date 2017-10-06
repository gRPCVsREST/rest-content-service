package org.grpcsvrest.rcs.rest;

import org.grpcsvrest.rcs.service.ContentDto;
import org.grpcsvrest.rcs.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class ContentController {

    private final ContentService contentService;

    @Autowired
    public ContentController(ContentService contentService) {
        this.contentService = contentService;
    }

    @GetMapping(value = "/content/{id}", produces = "application/json")
    public Content getContentJson(@PathVariable("id") int id) {
        return content(id);
    }

    @GetMapping(value = "/content/{id}", produces = "application/xml", consumes = "application/xml")
    public Content getContentXml(@PathVariable("id") int id) {
        return content(id);
    }

    private Content content(@PathVariable("id") int id) {
        ContentDto contentDto = contentService.getByIndex(id);
        return new Content(id, contentDto.getContent(), contentDto.hasNext() ? id + 1 : 1);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleBadIndex() {
    }
}
