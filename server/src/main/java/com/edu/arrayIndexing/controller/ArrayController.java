package com.edu.arrayIndexing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.arrayIndexing.service.ArrayService;

@RestController
public class ArrayController {

    @Autowired
    private ArrayService arrayService;

    @PostMapping("/create-array")
    public void createArray(@RequestParam(name = "array-count") int arrayCount) {
        arrayService.createArray(arrayCount);
    }

    @GetMapping("/search-by-index/{arrayIndex}")
    public String searchByIndex(@PathVariable int arrayIndex) {
        try {
            return arrayService.searchFromArray(arrayIndex);

        } catch (Exception e) {
            return "Array index out of bound";
        }
    }

    @GetMapping("/even-index-array")
    public int[] evenIndexArray() {
        return arrayService.evenIndexArray();
    }

    @PutMapping("/update-array")
    public void updateArray(@RequestBody int[] newArr) {
        arrayService.updateArray(newArr);
    }

    @GetMapping("/smallest-element")
    public int smallestNumber() {
        return arrayService.findIndexSmallest();
    }

    @GetMapping("/largest-element")
    public int largest() {
        return arrayService.findLargestIndex();
    }

}