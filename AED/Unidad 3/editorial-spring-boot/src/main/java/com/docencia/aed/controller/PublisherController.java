package com.docencia.aed.controller;

import com.docencia.aed.entity.Author;
import com.docencia.aed.entity.Publisher;
import com.docencia.aed.service.IPublisherService;
import com.docencia.aed.service.impl.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publishers")
@Tag(name = "Publishers", description = "Operaciones sobre editoriales")
public class PublisherController {
    private IPublisherService publisherService;

    @Autowired
    public void setPublisherService(IPublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @Operation(summary = "Get all publishers")
    @GetMapping("")
    public List<Publisher> getAllPublisher() {
        return publisherService.findAll();
    }

    @Operation(summary = "Save publisher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Publisher saved"),
            @ApiResponse(responseCode = "500", description = "Error occurred while saving the publisher")
    })
    @PostMapping("")
    public Publisher createPublisher(@RequestBody Publisher publisher) {
        return publisherService.create(publisher);
    }
}
