import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    private String name, description;
    Level.Room currentRoom;
    private List<Item> items;
    public Player(String name, String description) {
        items = new ArrayList<>();
        this.name = name;
        this.description = description;

    }



    public void addItem(Item item){
        items.add(item);
    }

    public Item removeItem(String name){
        //check for null when using this method
        Item i = new Item(getItem(name).getName(),getItem(name).getDescription());
        items.remove(getItem(name));
        return i;
    }

    public String displayInventory(){
        String names = " ";
        for (int i = 0; i < items.size(); i++) {
            names = names + ", " + items.get(i).getName();
        }
        return names;
    }

    public Level.Room getCurrentRoom(){
        return currentRoom;

    }

    public void setCurrentRoom(Level.Room newRoom){
        currentRoom = newRoom;


    }

    public boolean moveToRoom(String name){
        if(currentRoom.getNeighbor(name) == null) return false;
        return true;
    }

    public Item getItem(String name){
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i).getName().equals(name)) return items.get(i);
        }
        return null;
    }
}
