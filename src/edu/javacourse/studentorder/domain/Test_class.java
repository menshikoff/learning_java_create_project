package edu.javacourse.studentorder.domain;

public class Test_class {

    Integer qqq;

    public static void main(String[] args) {
        int num = Integer.valueOf("34");
        System.out.println(num);

        Integer numInt = 560;
        Integer numInt2 = 440;
        System.out.println(numInt.toString()+numInt2.toString());
        System.out.println("\n");

    }

    String test_st(int param) {

        return "hello";
    }

    void test_st(Object q) {
        qqq = (Integer) q;
        System.out.println(qqq);
    }

}
