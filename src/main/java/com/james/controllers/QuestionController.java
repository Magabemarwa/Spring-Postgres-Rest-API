/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james.controllers;

import com.james.demo.QuestionsRepository;
import com.james.model.Question;
import java.awt.PageAttributes;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author james
 */
@RestController
public class QuestionController {
    
    @Autowired
    private QuestionsRepository qr;
    
    
    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/questions")
    public List<Question> getAllQuestions(){
        return qr.findAll();
    }
    
    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question q){
        return qr.save(q);
    }
}
