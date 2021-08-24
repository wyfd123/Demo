package com.cy.jt.demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 通过此对象演示CRUD请求的处理
 */
@RestController
@RequestMapping("/demo/")
public class DemoController {
    public  DemoController(){
        System.out.println("DemoController()");
    }
    @PutMapping
    public String doUpdate(@RequestBody Map<String,Object> map){
        return map.toString() + "is updated";
    }
    @PostMapping
    public String doCreate(@RequestBody Map<String,Object> map){
        return map.toString() + "is created";
    }

    //查询这个方法被调用了几次
    private AtomicLong counter = new AtomicLong(1);
    @GetMapping("{id}")
    public  String doRetrievById(@PathVariable Integer id) throws InterruptedException {
        String tName = Thread.currentThread().getName();
        Thread.sleep(3000);
        System.out.println(counter.getAndIncrement());
        return tName+ "find result by"+id;
    }
    //考虑了安全性
//    private String count = "c";
//    private StringBuffer sb = new StringBuffer();
//    @GetMapping("{id}")
//    public  String doRetrievById(@PathVariable Integer id){
//        String tName = Thread.currentThread().getName();
//        sb.append(count).length();
//        System.out.println(count);
//        return tName+ "find result by"+id;
//    }
    //没有考虑安全性，效率慢
    //    private int conut;
//    @GetMapping("{id}")
//    public synchronized String doRetrievById(@PathVariable Integer id){
//        String tName = Thread.currentThread().getName();
//        conut++;
//        System.out.println(conut);
//        return tName+ "find result by"+id;
//    }
//    @RequestMapping("{id}")
    @DeleteMapping("{id}")
    public String doDeleterById(@PathVariable Integer ...id){
        return Arrays.toString(id)+"is deleter";
    }
}
