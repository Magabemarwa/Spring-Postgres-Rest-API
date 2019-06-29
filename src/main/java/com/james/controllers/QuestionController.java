/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james.controllers;

import com.james.demo.QuestionsRepository;
import com.james.model.Question;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author james
 */
@RestController
@RequestMapping("api/v1")
@Api(value="Q&A Management System", description="CRUD Operations concerning Q&A Managament Restful web service in spring boot")
public class QuestionController {

    @Autowired
    private QuestionsRepository qr;

   
    @GetMapping("/questions")
    @ApiOperation(value="View a list of all questions", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public List<Question> getAllQuestions() {
        return qr.findAll();
    }

    @ApiOperation(value="Create a question in the database")
    @PostMapping("/questions")
    public Question createQuestion(@Valid @RequestBody Question q) {
        return qr.save(q);
    }

    /**
     *
     * @param questionid
     * @param question
     * @return
     */
    @ApiOperation(value="Edit an existing question in the database")
    @PutMapping("/questions/{questionid}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionid,
             @RequestBody Question question) {
        
        Optional<Question> questionOptional = qr.findById(questionid);
        
        if(!questionOptional.isPresent())
            return ResponseEntity.notFound().build();
        
        question.setId(questionid);
        
        qr.save(question);
        
        return ResponseEntity.accepted().build();
    }
    
    @GetMapping("/question/{questionid}")
    public Optional<Question> getOneQuestion(@PathVariable Long questionid) {
        
        Optional<Question> q = qr.findById(questionid);
        
        return q;
    }
}
