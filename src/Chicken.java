public class Chicken extends GenericEntity{

    public Chicken(Level.Room startRoom) {
        super(startRoom,"chicken","a chicken");
    }

    @Override
    public void move() {
        Level.Room currentRoom = getRoom();
        Level.Room nextRoom = currentRoom.getRandomNeighbor();
        if(nextRoom != null){
            currentRoom = nextRoom;
        }
        setCurrentRoom(currentRoom);
    }





}
