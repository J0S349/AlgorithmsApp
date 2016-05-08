package edu.josesanchez1csumb.algorithmsapp;

/**
 * Created by jsanchez-garcia on 5/7/16.
 */
public class Algorithm {

    private int ID;
    private String name;
    private String description;
    private String runtime;

    public Algorithm(){
        ID = 0;
        name = "";
        description = "";
        runtime = "";
    }
    public Algorithm(int ID, String name, String description, String runtime){
        this.ID = ID;
        this.name = name;
        this.description = description;
        this.runtime = runtime;
    }

    public int getID(){return ID; }

    public String getName(){return name;}

    public String getDescription(){return description; }

    public String getRuntime(){return runtime;}

    public void setID(int ID){
        this.ID = ID;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setRuntime(String runtime){
        this.runtime = runtime;
    }

    public String toString(){
        return "[id=" + ID + " name= " + name + " description= '" + description + "' runtime= '" + runtime + "']";
    }
}
