package com.bonappetit.config;

import com.bonappetit.model.entity.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
@SessionScope
public class LoggedUser {

    public long id;
    public String username;


    public void login(User user){
        this.id = user.getId();
        this.username =user.getUsername();
    }

    public boolean isLogin(){
        if(id==0){
            return false;
        }
            return true;
    }
    public void logout(){
        this.id = 0;
        this.username = "";
    }

    public long getId(){
        return id;
    }

    public String getUsername(){
        return this.username;
    }
}