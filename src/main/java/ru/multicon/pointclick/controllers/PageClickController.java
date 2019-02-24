package ru.multicon.pointclick.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.multicon.pointclick.controllers.request.PageClickRequest;
import ru.multicon.pointclick.domain.UserTop;
import ru.multicon.pointclick.domain.UserUniq;
import ru.multicon.pointclick.services.PageClickService;

import javax.validation.Valid;
import java.time.ZonedDateTime;

@Slf4j
@RestController
@RequestMapping("/pointclick/api/v1/pageclick")
public class PageClickController {

    private final PageClickService pageClickService;

    @Autowired
    public PageClickController(PageClickService pageClickService) {
        this.pageClickService = pageClickService;
    }

    @PostMapping(value = "/",
            consumes = "application/json",
            produces = "application/json")
    public ResponseEntity<UserUniq> create(@RequestBody @Valid PageClickRequest pageClickRequest) {
        return ResponseEntity
                .ok()
                .body(pageClickService.create(pageClickRequest.getUserId(), pageClickRequest.getPageId()));

    }

    @GetMapping(value = "/statistics",
            produces = "application/json")
    public ResponseEntity<UserTop> statistics(@RequestParam("dateStart")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime dateStart,
                                              @RequestParam("dateEnd")
                                              @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime dateEnd) {
        return ResponseEntity
                .ok()
                .body(pageClickService.statistics(dateStart, dateEnd));

    }
}
