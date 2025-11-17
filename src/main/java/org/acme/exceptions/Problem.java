package org.acme.exceptions;

import org.hibernate.exception.ConstraintViolationException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Problem {

    public int status;
    public OffsetDateTime timestamp;
    public String title;
    public String detail;
    public List<ProblemObject> messages;

    public Problem(){

    }


    public Problem(ConstraintViolationException e){
        this.status = 400;
        this.timestamp = OffsetDateTime.now();
        this.title = "Invalid data";
        this.detail  = "Dados inválidos";
        this.messages = new ArrayList<>();
    }

    public Problem(jakarta.validation.ConstraintViolationException e) {
        this.status = 400;
        this.timestamp = OffsetDateTime.now();
        this.title = "Invalid data";
        this.detail  = "Dados inválidos";
        this.messages = new ArrayList<>();
    }
}