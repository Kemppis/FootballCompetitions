package com.example.footballcompetitions;

import java.io.Serializable;

public class Area implements Serializable {
    private String name;
    private int ID;

    public Area(int ID, String name)
    {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
