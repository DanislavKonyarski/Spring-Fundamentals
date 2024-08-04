package com.dictionaryapp.config;

import com.dictionaryapp.model.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@SessionScope
public class LoggedUser {

    private long id;
    private String username;


    public void login(User user) {

        this.id = user.getId();
        this.username = user.getUsername();

    }

    public String username(){
        return this.username;
    }

    public boolean isLogin(){

        return this.id != 0 ;
    }

    public void logout() {
        this.id = 0;
        this.username="";
    }

    public long getId() {
        return this.id;
    }
}
