import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Player player = new Player("Charan", "i");
    static Level g = new Level();

    public static void main(String[] args) {
        ArrayList<Item> items = new ArrayList<>();
        g.addNode("hall");
        g.addNode("closet");
        g.addNode("bedroom");
        g.addNode("stairs");
        g.addNode("dungeon");
        g.addNode("ladder");

        g.addDirectedEdge("hall", "bedroom");
        g.addUndirectedEdge("hall", "closet");
        g.addDirectedEdge("bedroom", "stairs");
        g.addUndirectedEdge("stairs", "dungeon");
        g.addDirectedEdge("stairs", "ladder");
        g.addDirectedEdge("ladder", "hall");
        g.addDirectedEdge("dungeon","bedroom");
        g.addUndirectedEdge("ladder","closet");

        player.setCurrentRoom(g.getNode("hall"));

        initItemsAndEntities(g);

        String response = "";
        Scanner in = new Scanner(System.in);
        do {

            System.out.println("You are currently in the " + player.getCurrentRoom().getName());
            System.out.println("What do you want to do? Type \"commands\" to see your options >");

            response = in.nextLine();
            String[] words = response.split(" ");
            String firstWord = words[0];

            if (firstWord.equals("go")) {
                Level.moveAllCreatures();
                if (player.moveToRoom(words[1]) == true) player.setCurrentRoom(player.getCurrentRoom().getNeighbor(words[1]));
                 else System.out.println("You can't go to " + words[1] + ". Try again");

            } else if (firstWord.equals("look")) {
                System.out.println("You can go to the: " + player.getCurrentRoom().getNeighborNames());
                System.out.println("Entities in the room with you: " + checkRoom() );
                System.out.println("These are the items currently in the room: " + player.getCurrentRoom().displayItems());
                System.out.println("These are the item(s) in your inventory: " + player.displayInventory());

            } else if (firstWord.equals("add")) {
                if (words.length == 3 && words[1].equals("room")) {
                    if (g.getNode(words[2]) == null) {
                        g.addNode(words[2]);
                        g.addDirectedEdge(player.getCurrentRoom().getName(), words[2]);
                        System.out.println("Room " + words[2] + " has been added");
                    } else {
                        g.addDirectedEdge(player.getCurrentRoom().getName(), words[2]);
                        System.out.println("Connection between " + words[2] + " and " + player.getCurrentRoom().getName() + " has been added.");
                    }
                } else System.out.println("Type \"add room <roomname>\" to add a new room or a connection to a pre-existing room");

            } else if (firstWord.equals("quit")) {
                System.out.println("\n *** \n");
                return;
            } else if (firstWord.equals("take")) {
                if (player.getCurrentRoom().getItem(words[1]) == null) System.out.println("That item is not in this room/doesn't exist");
                else{
                    player.addItem(player.getCurrentRoom().removeItem(words[1]));
                    System.out.println("The item " + player.getItem(words[1]).getName() + " has been removed from the " + player.getCurrentRoom().getName() + " and added to your inventory.");

                }


            } else if (firstWord.equals("drop")) {
                if (player.getItem(words[1]) == null) System.out.println("That item is not in your inventory");
                else{
                    player.getCurrentRoom().addItem(player.removeItem(words[1]));
                    System.out.println("The item " + player.getCurrentRoom().getItem(words[1]).getName() + " has been removed from your inventory and added to the " + player.getCurrentRoom().getName());
                }

            } else {
                System.out.println("Commands: \n Type \"look\" to see all the rooms you can go to and all the items in your current room");
                System.out.println("Type \"go <roomname>\" to go to a neighboring room");
                System.out.println("Type \"add room <roomname>\" to add a new room or a connection to a pre-existing room");
                System.out.println("Type \" take <itemname> \" to take an item from a room");
                System.out.println("type \" drop <itemname> \" to drop an item from a room");
                System.out.println("Type \"quit\" to quit");
            }

            System.out.println("\n *** \n");


        } while (!response.equals("quit"));


    }

    private static void initItemsAndEntities(Level g){
        g.createItems("sword","sharp","dungeon");
        g.createItems("painting","cool","hall");
        g.createItems("clock","10:00pm","bedroom");
        g.createItems("mop","dirty","closet");
        g.createItems("wrench","steel","ladder");

        g.addEntity("chicken","stairs");
        g.addEntity("wumpus","ladder");
        g.addEntity("popstar","stairs");
    }

    public static String checkRoom(){
        String names = " ";
        for (int i = 0; i < Level.getList().size(); i++) {
            if(Level.getList().get(i).getRoom().getName().equals(player.getCurrentRoom().getName())) names = names + ", " +  Level.getList().get(i).getName();
        }
        return names;
    }

    public static Player getPlayer(){
        return player;
    }

    public static Level getLevel(){
        return g;
    }
}
