package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.exeption.TransportExeption;
import edu.javacourse.studentorder.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exeption.CityRegisterExeption;

public interface CityRegisterChecker {

    CityRegisterResponse checkPerson (Person person)
            throws CityRegisterExeption, TransportExeption;
}
