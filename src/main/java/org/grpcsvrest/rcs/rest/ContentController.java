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
    private final String urlPrefix;

    @Autowired
    public ContentController(
            ContentService contentService,
            @Value("${url_prefix:http://localhost:8080}") String urlPrefix) {
        this.contentService = contentService;
        this.urlPrefix = urlPrefix;

    }

    @GetMapping("/content/{id}")
    public Content getContent(@PathVariable("id") int id) {
        ContentDto contentDto = contentService.getByIndex(id);
        return new Content(id, contentDto.getContent(), contentDto.hasNext() ? urlPrefix + "/content/" + (id+1) : null);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleBadIndex() {}
}
