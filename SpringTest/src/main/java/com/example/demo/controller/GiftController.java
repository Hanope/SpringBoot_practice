package com.example.demo.controller;

import com.example.demo.model.Gift;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GiftController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Gift> get() {
        Gift gift = new Gift();
        gift.setColor("green");
        gift.setName("watch");

        return new ResponseEntity<Gift>(gift, HttpStatus.OK);
    }
}
