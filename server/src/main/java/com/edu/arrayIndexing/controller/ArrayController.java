package com.edu.arrayIndexing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/find-avg")
    public double findAvg() {
        return arrayService.findAverage();
    }

    @GetMapping("find-duplicate")
    public ResponseEntity findDuplicate() {
        try {
            return ResponseEntity.ok().body(arrayService.findDuplicate());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("find-duplicate-faster")
    public ResponseEntity findDuplicateFaster() {
        try {
            return ResponseEntity.ok().body(arrayService.findDuplicateFaster());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("find-duplicate-fastest")
    public ResponseEntity findDuplicatesFasterHash() {
        try {
            return ResponseEntity.ok().body(arrayService.findDuplicatesFasterHash());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("find-target")
    public ResponseEntity findTarget(@RequestParam int target) {
        try {
            return ResponseEntity.ok().body(arrayService.findIndexesForTarget(target));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}