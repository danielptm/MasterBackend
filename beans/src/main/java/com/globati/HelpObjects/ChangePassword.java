package com.globati.HelpObjects;

/**
 * Created by daniel on 1/23/17.
 */
public class ChangePassword {

    Long propertyId;
    String oldPassword;
    String newPassword;


    public ChangePassword(Long employeeId, String oldPassword, String newPassword){
        this.propertyId = employeeId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public ChangePassword(){}

    public Long getPropertyId() {
        return propertyId;
    }

    public void setEmployeeId(Long employeeId) {
        this.propertyId = employeeId;
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
                "employeeId=" + propertyId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }

}
