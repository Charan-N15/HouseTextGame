public class Popstar extends GenericEntity{
    public Popstar(Level.Room startRoom) {
        super(startRoom, "popstar", "a popstar");
    }

    @Override
    public void move() {
        Player p = Main.getPlayer();
        Level.Room playerRoom = p.getCurrentRoom();
        Level.Room currentRoom = getRoom();
        if(currentRoom.getNeighbor(playerRoom.getName()) != null && currentRoom.isNeighborOf(playerRoom)){
            currentRoom = playerRoom;
        }
        setCurrentRoom(currentRoom);

    }
}
