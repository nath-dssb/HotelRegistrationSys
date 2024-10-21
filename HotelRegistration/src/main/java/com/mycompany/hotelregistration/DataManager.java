package com.mycompany.hotelregistration;

import static com.mycompany.hotelregistration.HotelRegistration.employeeData;
import static com.mycompany.hotelregistration.HotelRegistration.hostData;
import static com.mycompany.hotelregistration.HotelRegistration.reservationData;
import static com.mycompany.hotelregistration.HotelRegistration.roomData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author natha
 */
public class DataManager {
    
    
    /**
     * <h3>Escritor de datos</h3>
     * Se encarga de escribir un string 
     * recibido como parametro en un archivo 
     * indicado por el path.
     * ----------------------------------------
     * @param path dirección del archivo sobre el cual se escribirá.
     * @param csvData string de forma csv 
     * que se escribirá en el archivo.
     */
    public static void dataWriter(String path, String csvData) {
        try(
                FileWriter writer = new FileWriter(path, true);
                BufferedWriter buffer = new BufferedWriter(writer);
                PrintWriter pen = new PrintWriter(buffer)
           )
        {
            
            pen.println("\n" + csvData);
            
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
    }
    
    
    /**
     * <h3>Lector de datos</h3>
     * dataReader se encarga de leer un archivo 
     * y generar una lista de strings 
     * con los elementos del mismo.
     * ---------------------------------
     * @param path recibe la dirección del archivo.
     * @return regresa una lista de strings.
     */
    private static List<String> dataReader(String path) {
        List<String> data = new ArrayList<>();
        
        try{
            BufferedReader fileIn = new BufferedReader(new FileReader(path));
            String cad = fileIn.readLine();
            
            if(cad == null) {
                throw new Exception("File is empty.");
            }
            
            while (cad != null) {
                data.add(cad);
                cad = fileIn.readLine();
            }
        
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
        
        return data;
        
    }
    
    /**
     * <h1>Métodos inicializadores</h1>
     * Se encargan de leer un archivo existente y
     * añadir a cada colección los datos que 
     * se han mantenido guardados. 
     * Llama al método dataReader() para
     * obtener la lista de strings a leer.
     * -----------------------------------
     * @param path: se refiere a la dirección 
     * donde se esta guardando la 
     * información pertinente a cada metodo.
     */
    
    public static void employeesInitializer(String path) {
        List<String> initialData = dataReader(path);
        
        try {
            
            for(String el : initialData) {
                String[] tokens = el.split(", ");
                
                if(tokens.length < 7) {
                    throw new Exception("Incomplete data.");
                }
                
                String fName = tokens[0];
                String lName = tokens[1];
                String birth = tokens[2];
                String dni = tokens[3];
                String address = tokens[4];
                String role = tokens[5];
                String password = tokens[6];
                
                employeeData.add(new Employee(fName, lName, birth, dni, address, role, password));
            }
            
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
        
    }
    
    public static void hostsInitializer(String path) {
        
        List<String> initialData = dataReader(path);
        
        try {
            
            for(String el : initialData) {
                String[] tokens = el.split(", ");
                
                if(tokens.length < 6) {
                    throw new Exception("Incomplete data." + el);
                }
                
                String fName = tokens[0];
                String lName = tokens[1];
                String birth = tokens[2];
                String dni = tokens[3];
                String address = tokens[4];
                String profession = tokens[5];
                
                hostData.add(new Host(fName, lName, birth, dni, address, profession));
            }
            
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
    
    }
    
    public static void roomsInitializer(String path) {
        
        List<String> initialData = dataReader(path);
        
        try {
            
            for(String el : initialData) {
                String[] tokens = el.split(", ");
                
                if(tokens.length < 4) {
                    throw new Exception("Incomplete data.");
                }
                
                int id = Integer.parseInt(tokens[0]);
                int floor = Integer.parseInt(tokens[1]);
                String theme = tokens[2];
                String type = tokens[3];
                
                roomData.add(new HotelRoom(id, floor, theme, type));
            }
            
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
    
    }
    
    public static void reservationsInitializer(String path) {
        
          List<String> initialData = dataReader(path);
        
        try {
            
            for(String el : initialData) {
                String[] tokens = el.split(", ");
                
                if(tokens.length < 6) {
                    throw new Exception("Incomplete data.");
                }
                
                String checkin = tokens[0];
                String checkout = tokens[1];
                int room = Integer.parseInt(tokens[2]);
                String host = tokens[3];
                int companions = Integer.parseInt(tokens[4]);
                String employee = tokens[5];
                
                reservationData.add(new Reservation(checkin, checkout, room, host, companions, employee));
            }
            
        } catch (Exception e) {
            System.err.println(e.toString() + " " + path);
        }
    
    }
    
}
