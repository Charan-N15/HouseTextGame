import sun.net.www.content.text.Generic;

import java.util.ArrayList;
import java.util.List;

public abstract class GenericEntity implements Entity{
    private String name, description;
    private Level.Room currentRoom;
    public GenericEntity(Level.Room currentRoom, String name, String description) {
        this.name = name;
        this.description = description;
        this.currentRoom = currentRoom;

    }

    public Level.Room getRoom(){
        return currentRoom;
    }
    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public void setCurrentRoom(Level.Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public GenericEntity setName(String name) {
        this.name = name;
        return this;
    }

    public GenericEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public abstract void move();




}
