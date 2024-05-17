package com.edu.arrayIndexing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edu.arrayIndexing.service.ArrayService;

@RestController
public class ArrayController {

    @Autowired
    private ArrayService arrayService;

    @GetMapping("/get-count")
    public int add() {
        return arrayService.increamentCount();
    }
}