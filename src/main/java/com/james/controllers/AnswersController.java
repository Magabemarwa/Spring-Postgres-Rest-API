/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james.controllers;

import com.james.demo.AnswersRepository;
import com.james.demo.QuestionsRepository;
import com.james.model.Answers;
import com.james.model.Question;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author james
 */
@RestController
@RequestMapping("api/v1")
public class AnswersController {
    
    @Autowired
    private AnswersRepository ar;
    
    @Autowired
    private QuestionsRepository qr;
    
    @GetMapping("/questions/{questionid}/answers")
    public List<Answers> getAnswersByQuestionId(@PathVariable Long questionid) {
        return ar.findByQuestionId(questionid);
    }
    
    @GetMapping("/answers")
    public List<Answers> getAllAnswers(){
        return ar.findAll();
    }
    
    
    @PostMapping("/questions/{questionid}/answers")
    public Answers addAnswer(@PathVariable Long questionid,
                            @Valid @RequestBody Answers answer) {
        
        Optional<Question> q = qr.findById(questionid);
        
        answer.setQuestion(q);
        
        return ar.save(answer);
    }
}
