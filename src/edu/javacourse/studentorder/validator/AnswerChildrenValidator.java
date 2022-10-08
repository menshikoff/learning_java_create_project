package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.AnswerChildren;
import edu.javacourse.studentorder.domain.StudentOrder;

public class AnswerChildrenValidator {

    public AnswerChildren checkChildren(StudentOrder so) {

        System.out.println("Children is running");
        return new AnswerChildren();
    }

}
