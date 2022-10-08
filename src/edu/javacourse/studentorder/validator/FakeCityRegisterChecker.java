package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Adult;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.exeption.TransportExeption;
import edu.javacourse.studentorder.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exeption.CityRegisterExeption;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    public static final String GOOD_1 = "1000";
    public static final String GOOD_2 = "2000";
    public static final String BAD_1 = "1001";
    public static final String BAD_2 = "2001";
    public static final String ERROR_1 = "1002";
    public static final String ERROR_2 = "2002";
    public static final String ERROR_T_1 = "1003";
    public static final String ERROR_T_2 = "2003";

    public CityRegisterResponse checkPerson (Person person)
            throws CityRegisterExeption, TransportExeption {

        CityRegisterResponse res = new CityRegisterResponse();

        if (person instanceof Adult) {
           Adult t = (Adult) person;
           if (t.getPassportSeria().equals(GOOD_1) || t.getPassportSeria().equals(GOOD_2)) {
               res.setExisting(true);
               res.setTemporal(false);
           }
            if (t.getPassportSeria().equals(BAD_1) || t.getPassportSeria().equals(BAD_2)) {
                res.setExisting(false);
            }
            if (t.getPassportSeria().equals(ERROR_1) || t.getPassportSeria().equals(ERROR_2)) {
                CityRegisterExeption ex = new CityRegisterExeption("101", "Fake error");
                throw ex;
            }
            if (t.getPassportSeria().equals(ERROR_T_1) || t.getPassportSeria().equals(ERROR_T_2)) {
                TransportExeption ex = new TransportExeption("Fake transport error");
                throw ex;
            }


        }

        if (person instanceof Child) {
            res.setExisting(true);
            res.setTemporal(true);
        }

        System.out.println(res);
        return res;
    }
}
