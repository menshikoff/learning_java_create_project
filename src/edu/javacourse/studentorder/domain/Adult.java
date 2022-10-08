package edu.javacourse.studentorder.domain;

import java.time.LocalDate;

public class Adult extends Person {

    private String passportSeria;
    private String passportNumber;
    private LocalDate issueDate;
    private PassportOffice issueDepartment;
    private String studentID;
    private University university;

    public Adult(String firstName, String lastName, String patronymic, LocalDate dateOfBirth) {
        super(firstName, lastName, patronymic, dateOfBirth);
    }


    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public String getPassportSeria() {
        return passportSeria;
    }

    public void setPassportSeria(String passportSeria) {
        this.passportSeria = passportSeria;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public PassportOffice getIssueDepartment() {
        return issueDepartment;
    }

    public void setIssueDepartment(PassportOffice issueDepartment) {
        this.issueDepartment = issueDepartment;
    }
}
