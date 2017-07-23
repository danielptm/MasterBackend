package com.globati.utildb.HelpObjects;

import javafx.scene.control.TextFormatter;

/**
 * Created by daniel on 1/23/17.
 */
public class ChangePassword {

    Long employeeId;
    String oldPassword;
    String newPassword;


    public ChangePassword(Long employeeId, String oldPassword, String newPassword){
        this.employeeId = employeeId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePassword(){}

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "ChangePassword{" +
                "employeeId=" + employeeId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

}
