/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.james.demo;

import com.james.repositories.*;
import com.james.model.Answers;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author james
 */
@Repository
public interface AnswersRepository extends JpaRepository<Answers, Long> {
    
    List<Answers> findByQuestionId(Long questionid);
}
