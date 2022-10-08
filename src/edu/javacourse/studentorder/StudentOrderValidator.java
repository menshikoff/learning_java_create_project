package edu.javacourse.studentorder;

import edu.javacourse.studentorder.domain.*;
import edu.javacourse.studentorder.mail.MailSender;
import edu.javacourse.studentorder.register.AnswerCityRegisteer;
import edu.javacourse.studentorder.validator.AnswerChildrenValidator;
import edu.javacourse.studentorder.validator.AnswerStudentValidator;
import edu.javacourse.studentorder.validator.AnswerWeddingValidator;
import edu.javacourse.studentorder.validator.CityRegisterValidator;

import java.util.LinkedList;
import java.util.List;

public class StudentOrderValidator {

    private CityRegisterValidator cityRegisterVal;
    private AnswerWeddingValidator weddingVal;
    private AnswerChildrenValidator childrenVal;
    private AnswerStudentValidator studentVal;
    private MailSender mailSender;

    public StudentOrderValidator() {
        cityRegisterVal = new CityRegisterValidator();
        weddingVal = new AnswerWeddingValidator();
        childrenVal = new AnswerChildrenValidator();
        studentVal = new AnswerStudentValidator();
        mailSender = new MailSender();

    }

    public static void main(String[] args) {
        StudentOrderValidator sov = new StudentOrderValidator();
        sov.checkAll();

    }

    public void checkAll() {
        List<StudentOrder> soList = readStudentOrders();

        for(StudentOrder so : soList) {
            checkOneOrder(so);
        }
    }

    public List<StudentOrder> readStudentOrders() {
        List<StudentOrder> soList = new LinkedList<>();

        for(int i = 0; i < 5; i++) {
            StudentOrder so = SaveStudentOrder.buildStudentOrder(i);
            soList.add(so);
        }

        return soList;
    }

    public void checkOneOrder(StudentOrder so) {
        AnswerCityRegisteer cityAnswer = checkCityRegister(so);
//        AnswerWedding wedAnswer = checkWedding(so);
//        AnswerChildren childrenAnswer = checkChildren(so);
//        AnswerStudent studentAnswer = checkStudent(so);
//        sendMail(so);
    }

    public AnswerCityRegisteer checkCityRegister(StudentOrder so) {
        return cityRegisterVal.checkCityRegister(so);
    }

    public AnswerWedding checkWedding(StudentOrder so) {
        return weddingVal.checkWedding(so);
    }

    public AnswerChildren checkChildren(StudentOrder so) {
        return childrenVal.checkChildren(so);
    }

    public AnswerStudent checkStudent(StudentOrder so) {
        return studentVal.checkStudent(so);

    }
    public void sendMail(StudentOrder so) {
        mailSender.sendMail(so);
    }
}
