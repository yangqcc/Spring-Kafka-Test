package com.yqc.tx.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yangqc
 */
@Data
@AllArgsConstructor
public class MyBook {

  private int id;

  private String title;

  private String author;

  private Date submission_date;
}
