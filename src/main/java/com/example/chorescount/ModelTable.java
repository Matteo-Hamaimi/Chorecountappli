package com.example.chorescount;

public class ModelTable {

    String username, choreName, category, description, done;
    String idChore;

    public ModelTable(String idChore, String username, String choreName, String category, String description, String done){
        this.username = username;
        this.choreName = choreName;
        this.category = category;
        this.description = description;
        this.done = done;
        this.idChore = idChore;
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

    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getDone(){
        return done;
    }
    public void setDone(String done){
        this.done = done;
    }

    public String getIdChore(){
        return idChore;
    }
    public void setIdChore(String idChore){
        this.idChore = idChore;
    }

}