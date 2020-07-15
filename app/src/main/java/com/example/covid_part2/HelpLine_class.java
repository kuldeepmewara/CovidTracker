package com.example.covid_part2;

/**
 * Created by Mom on 06-04-2020.
 */

public class HelpLine_class {

    String  state_name="c";
    String number="0";

    public HelpLine_class(String state_name, String number) {
        this.state_name = state_name;
        this.number = number;
    }

    public String getState_name() {
        return state_name;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
