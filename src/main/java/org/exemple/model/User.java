package org.exemple.model;

public class User {
    private final int id;
    private final String name;
    private final Integer age;
    private final String bio;
    private final String login;
    private final String  password;
    private final String mail;

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
    public String getPassword() {
        return password;
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