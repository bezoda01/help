package models;

import lombok.Data;

@Data
public class AuthorModel {
    private  int id;
    private  String name;
    private  String login;
    private  String email;

    public AuthorModel(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
    }
    public AuthorModel(){}
}
