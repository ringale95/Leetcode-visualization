package com.edu.arrayIndexing.service;

import org.springframework.stereotype.Service;

@Service
public class ArrayService {

    private int count;

    public int increamentCount() {
        return count++;
    }
}
