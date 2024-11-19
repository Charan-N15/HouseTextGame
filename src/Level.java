
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Level {
    private List<Room> nodes;
    private static List<Entity> entityList;

    public Level(){
        nodes = new ArrayList<Room>();
        entityList = new ArrayList<>();
    }

    public void createItems(String item, String description, String room){
        getNode(room).addItem(item,description);
    }

    public static List<Entity> getList(){
        return entityList;
    }

    public static void moveAllCreatures(){
        for (Entity e : entityList  ) {
            e.move();
        }
    }

    public void addEntity(String type, String roomName) {
        if (type.equals("chicken")) {
            entityList.add(new Chicken(getNode(roomName)).setName("chicken").setDescription("a chicken"));
        }
        if(type.equals("wumpus")){
            entityList.add(new Wumpus(getNode(roomName)).setName("wumpus").setDescription("a wumpus"));
        }
        if(type.equals("popstar")){
            entityList.add(new Popstar(getNode(roomName)).setName("popstar").setDescription("a popstar"));
        }
    }

    public void addNode(String name){
        Room n = new Room(name);
        nodes.add(n);
    }

    public void addDirectedEdge(String name1, String name2){
        getNode(name1).addNeighbor(getNode(name2));

    }

    public void addUndirectedEdge(String name1, String name2){
        getNode(name1).addNeighbor(getNode(name2));
        getNode(name2).addNeighbor(getNode(name1));

    }


    public Level.Room getNode(String name){
        for (int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).getName().equals(name)){
                return nodes.get(i);
            }
        }
        return null;
    }



    public class Room{
        private List<Item> items;
        private String name;
        private ArrayList<Room> neighbors;


        private Room(String name) {
            items = new ArrayList<Item>();
            neighbors = new ArrayList<>();
            this.name = name;
        }

        public boolean isNeighborOf(Level.Room other){
            if(getNeighbor(other.getName()) == null) return false;
            return true;
        }

        public Level.Room getRandomNeighbor(Level.Room exclusion){
            if(neighbors.size() < 2) return null;
            int index = -1;
            ArrayList<Level.Room> nonExcludedRooms = new ArrayList<>();
            Level.Room nextRoom;
            for (int i = 0; i < neighbors.size(); i++) {
                if(!exclusion.getName().equals(neighbors.get(i).getName())) nonExcludedRooms.add(neighbors.get(i));
            }
            int rand = (int)(Math.random() * nonExcludedRooms.size());
            return nonExcludedRooms.get(rand);
        }

        public Level.Room getRandomNeighbor(){
            if(neighbors.size() == 0) return null;
            int index = (int)(Math.random() * neighbors.size());
            return neighbors.get(index);
        }

        private void addNeighbor(Room n){
            neighbors.add(n);
        }

        public String getNeighborNames(){
            String names = "";
            for (int i = 0; i < neighbors.size(); i++) {
                names = names + ", " +neighbors.get(i).getName();
            }

            return names;
        }

        public Room getNeighbor(String name){

            for (int i = 0; i < neighbors.size(); i++) {
                if(neighbors.get(i).getName().equals(name)){
                    return neighbors.get(i);
                }
            }
            return null;
        }

        public String displayItems(){

            String names = " ";
            for (int i = 0; i < items.size(); i++) {
                names = names + ", " + items.get(i).getName();
            }
            return names;
        }

        public void addItem(String name){
            Item i = new Item(name," ");
            items.add(i);

        }
        public void addItem(String name, String description){
            Item i = new Item(name,description);
            items.add(i);

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

        public Item getItem(String name){
            for (int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals(name)) return items.get(i);
            }
            return null;
        }

        public String getName() {
            return name;
        }

    }
}
