package com.backend.controllers;

import com.backend.dtos.Response;
import com.backend.services.apis.StatApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
public class StatController {
    @Autowired
    private StatApi statApi;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getGeneralStat() {
        Response response = statApi.getGeneralStat();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/popular-posts")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getPopularPostStat() {
        Response response = statApi.getPopularPostStat();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/top-users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getTopUsersStat() {
        Response response = statApi.getTopUsersStat();

        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}