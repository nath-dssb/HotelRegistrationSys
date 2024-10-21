package com.mycompany.hotelregistration;

import static com.mycompany.hotelregistration.HotelRegistration.keyboard;
import java.util.HashMap;
import java.util.Map;

/**
 *  Clase para registrar las habitaciones del hotel
 *  contiene su metodo constructor, getters y un builder
 * @author natha
 */
public class HotelRoom {
    
    private static Map<String, Double[]> roomTypes = new HashMap<>();
    
    private int ID;
    private int floor;
    private String roomTheme;
    private Map.Entry<String, Double[]> roomType;
    
    /**
     * Se declara un diccionario 
     * para tener los tipos de habitacion de manera fija,
     * esta funcion se encarga de asociar un input con un dato existente
     * el diccionario almacena el tipo de habitación como llave
     * y un arreglo númerico que corresponde a la capacidad de 
     * la habitación y su costo
     * 
     * @param type: recibe el tipo de habitacion como tipo string
     * @return retorna la entrada correspondiente del diccionario
     */
    private static Map.Entry<String, Double[]> typeVerifier(String type) {
        roomTypes.put("Single", new Double[]{1.0, 830.0});
        roomTypes.put("Doble", new Double[]{2.0, 965.0});
        roomTypes.put("Triple", new Double[]{3.0, 1225.0});
        roomTypes.put("Multiple", new Double[]{8.0,2365.0});
        
        Map.Entry<String, Double[]> assignedType = null;
        
        for (Map.Entry<String, Double[]> entry : roomTypes.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(type)) {
                assignedType = entry;
            }
        }
        
        return assignedType;
        
    }
    
    public HotelRoom(int ID, int floor, String roomTheme, String roomType) {
        this.ID = ID;
        this.floor = floor;
        this.roomTheme = roomTheme;
        this.roomType = typeVerifier(roomType);
    }
    
    /**
     * Construye un csv con los datos del objeto para poder registarlo en un archivo
     * @return 
     */
    public String csvWriter() {
        return (ID + ", " + floor + ", " + roomTheme + ", " + roomType);
    }
    
    /**
     * Metodo builder construye una habitación con el
     * input del usuario
     * @return retorna un objeto de tipo HotelRoom
     */
    public static HotelRoom hotelRoomBuilder() {
        HotelRoom newItem = null;
        
        try{
            
            System.out.println("Let's register a new room! First, what's the number of the room?");
            int idRoom = Integer.parseInt(keyboard.readLine());
            
            if(idRoom == 0) {
                throw new Exception("You must enter the number of room.");
            }
            
            System.out.println("What's the floor of the room?:");
            int floorRoom = Integer.parseInt(keyboard.readLine());
            
            if(floorRoom == 0) {
                throw new Exception("You must enter the floor of room.");
            }
            
            System.out.println("What's the room theme?:");
            String themeRoom = keyboard.readLine();
            
            if(themeRoom == null) {
                throw new Exception("You must enter the theme of room.");
            }
            
            System.out.println("Finally, what's the room type?:");
            Map.Entry<String, Double[]> typeRoom = typeVerifier(keyboard.readLine());
            
            if(typeRoom == null) {
                throw new Exception("You must enter the type of room.");
            }
            
            newItem = new HotelRoom(idRoom, floorRoom, themeRoom, typeRoom.getKey());
        
        } catch(Exception e) {
            System.err.println(e.toString() + "Couldn't register new room.");
        }
        
        return newItem;
    }
    
    public int getId() {
        return ID;
    }
    
    public int getFloor() {
        return floor;
    }
    
    public String getRoomTheme() {
        return roomTheme;
    }
    
    public String getType() {
        return roomType.getKey();
    }
    
    public Double getPrice() {
        return roomType.getValue()[1];
    }
    
    public int getCapacity() {

        return roomType.getValue()[0].intValue();
    }
    
    
}
