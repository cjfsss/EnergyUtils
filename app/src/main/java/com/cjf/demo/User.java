package com.cjf.demo;

import java.io.Serializable;

/**
 * <p>Title: User </p>
 * <p>Description:  </p>
 * <p>Company: www.mapuni.com </p>
 *
 * @author : 蔡俊峰
 * @version : 1.0
 * @date : 2022/4/3 10:02
 */
public class User  implements Serializable {
    private long id;
    private String userName;
    private String password;
    private boolean isFlag;

    public User(long id, String userName, String password, boolean isFlag) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.isFlag = isFlag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", isFlag=" + isFlag +
                '}';
    }
}
