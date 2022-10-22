package com.example.chorescount;

public class CatChoreTable {

    String username, choreName,  done;

    public CatChoreTable( String username, String choreName,  String done){
        this.username = username;
        this.choreName = choreName;
        this.done = done;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getChoreName(){
        return choreName;
    }
    public void setChoreName(String choreName){
        this.choreName = choreName;
    }

    public String getDone(){
        return done;
    }
    public void setDone(String done){
        this.done = done;
    }


}