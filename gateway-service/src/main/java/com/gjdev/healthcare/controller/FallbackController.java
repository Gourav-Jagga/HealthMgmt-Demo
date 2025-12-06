package com.gjdev.healthcare.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fallback")
public class FallbackController {
    @GetMapping("/{serviceName}")
    public String fallback(@PathVariable String serviceName) {
        return serviceName.substring(0, 1).toUpperCase() +
                serviceName.substring(1) +
                " Service is temporarily unavailable. Please try again later.";
    }
}