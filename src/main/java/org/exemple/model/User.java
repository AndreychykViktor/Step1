package org.exemple.model;

public class User {
    private final int id;
    private final String name;
    private final Integer age;
    private final String bio;
    private String login;
    private String password;
    private String mail;


    public User(int id, String login, String password, String mail) {
        this(id, null, null, null, login, password, mail);
    }
    public User(int id, String name, Integer age, String bio) {
        this(id, name, age, bio, null, null, null);
    }
    public User(int id, String name, Integer age, String bio, String login, String password, String mail) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.bio = bio;
        this.login = login;
        this.password = password;
        this.mail = mail;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getBio() {
        return bio;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserId() {
        return mail;
    }
    public void setUserId(String userId) {
        this.mail = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", bio='" + bio + '\'' +
                '}';
    }
}