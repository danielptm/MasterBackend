package com.globati.HelpObjects;

import java.util.List;

/**
 * Created by daniel on 12/11/16.
 * An Email object, this class will probably be moved to the application level
 * of the new Spring project
 *
 */
public class Email {

    Long id;
    private List<String> emails;

    public Email(Long id, List<String> emails) {
        this.id = id;
        this.emails = emails;
    }

    public Email(){}

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", emails=" + emails +
                '}';
    }

}
