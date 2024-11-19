public class Wumpus extends GenericEntity{

    public Wumpus(Level.Room startRoom) {
        super(startRoom,"wumpus","a wumpus");
    }

    @Override
    public void move() {
        Player p = Main.getPlayer();
        Level.Room playerRoom = p.getCurrentRoom();
        Level.Room currentRoom = getRoom();
        Level.Room nextRoom = null;
        if(playerRoom.isNeighborOf(currentRoom)){
            nextRoom = currentRoom.getRandomNeighbor(playerRoom);
        }
        if(nextRoom != null) currentRoom = nextRoom;

        setCurrentRoom(currentRoom);
    }
    
}
