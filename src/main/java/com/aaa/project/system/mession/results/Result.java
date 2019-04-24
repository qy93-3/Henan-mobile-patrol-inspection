package com.aaa.project.system.mession.results;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public class Result<T> {
    private  Integer code;
    private  String msg;
    private List<T>  data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
