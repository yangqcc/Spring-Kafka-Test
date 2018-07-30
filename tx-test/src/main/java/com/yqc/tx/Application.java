package com.yqc.tx;

import com.yqc.tx.entity.MyBook;
import com.yqc.tx.service.MyService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Date;

@ComponentScan(basePackages = {"com.yqc.tx"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        MyBook myBook = new MyBook(20, "sksknnkl", "sb", new Date());
        context.getBean(MyService.class).add(myBook);
    }
}