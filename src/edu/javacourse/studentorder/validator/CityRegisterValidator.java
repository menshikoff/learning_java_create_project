package edu.javacourse.studentorder.validator;

import edu.javacourse.studentorder.domain.Person;
import edu.javacourse.studentorder.exeption.TransportExeption;
import edu.javacourse.studentorder.register.AnswerCityRegisteer;
import edu.javacourse.studentorder.domain.Child;
import edu.javacourse.studentorder.register.AnswerCityRegisterItem;
import edu.javacourse.studentorder.register.CityRegisterResponse;
import edu.javacourse.studentorder.domain.StudentOrder;
import edu.javacourse.studentorder.exeption.CityRegisterExeption;

public class CityRegisterValidator {

    private static final String IN_CODE = "NO GRN";

    private CityRegisterChecker personChecker;

    public CityRegisterValidator() {
        personChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegisteer checkCityRegister(StudentOrder so) {
        AnswerCityRegisteer ansCity = new AnswerCityRegisteer();

        ansCity.addItem(checkPerson(so.getHusband()));
        ansCity.addItem(checkPerson(so.getWife()));
        for (Child child : so.getChildren()) {
            ansCity.addItem((checkPerson(child)));
                    }

        return ansCity;
    }


    private AnswerCityRegisterItem checkPerson(Person person) {
        AnswerCityRegisterItem.CityStatus status = null;
        AnswerCityRegisterItem.CityError error = null;

        try {
            CityRegisterResponse tmp = personChecker.checkPerson(person);
            status = tmp.isExisting() ?
                    AnswerCityRegisterItem.CityStatus.YES :
                    AnswerCityRegisterItem.CityStatus.NO;

        } catch (CityRegisterExeption ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
        } catch (TransportExeption ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
            status = AnswerCityRegisterItem.CityStatus.ERROR;
            error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
        }

        AnswerCityRegisterItem ans =
                new AnswerCityRegisterItem(status, person, error);
        return ans;
    }
}
