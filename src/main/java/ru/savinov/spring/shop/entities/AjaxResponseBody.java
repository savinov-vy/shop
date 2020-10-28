package ru.savinov.spring.shop.entities;

import java.util.List;


public class AjaxResponseBody {

    String msg;
    List<UserA> result;

    //getters and setters

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserA> getResult() {
        return result;
    }

    public void setResult(List<UserA> result) {
        this.result = result;
    }
}
