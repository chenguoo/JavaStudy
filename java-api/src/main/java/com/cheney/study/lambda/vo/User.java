package com.cheney.study.lambda.vo;

import java.io.Serializable;

/**
 * @version V1.0
 * @className: User
 * @author: Cheney
 * @date 2019-09-25 11:04
 */
public class User implements Serializable {
    private String name;
    private String sex;
    private int age;
    //是否上班
    private boolean isWorking;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }
}
