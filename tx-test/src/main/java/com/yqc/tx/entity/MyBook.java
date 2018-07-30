package com.yqc.tx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class MyBook {

    private int id;

    private String title;

    private String author;

    private Date submission_date;
}
