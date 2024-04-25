package com.example.tests;

import lombok.Data;

import java.util.List;

@Data
public class OuterLoop {
    private String title1;
    private List<MyOption> options;
    private String title2;
    private List<MyOption> options2;
}
