package pojo;

public class Room {
    // Room unique identifier
    private int roomId;
    // Associated hotel ID
    private String hotelId;
    // Type of room (e.g., SINGLE, DOUBLE)
    private String roomType;
    // Status of room (e.g., AVAILABLE, NOTAVAILABLE)
    private String roomStatus;
    // Price of the room
    private double roomPrice;

    // Getter for roomId
    public int getRoomId() {
        return roomId;
    }

    // Setter for roomId
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    // Getter for hotelId
    public String getHotelId() {
        return hotelId;
    }

    // Setter for hotelId
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    // Getter for roomType
    public String getRoomType() {
        return roomType;
    }

    // Setter for roomType
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Getter for roomStatus
    public String getRoomStatus() {
        return roomStatus;
    }

    // Setter for roomStatus
    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    // Getter for roomPrice
    public double getRoomPrice() {
        return roomPrice;
    }

    // Setter for roomPrice
    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }
}
