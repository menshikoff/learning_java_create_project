package edu.javacourse.studentorder.exeption;

public class CityRegisterExeption extends Exception {

    private String code;

    public CityRegisterExeption(String code, String message) {
        super(message);
        this.code = code;
    }

    public CityRegisterExeption(String code, String message, Throwable cause) {

        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}
