package com.mycompany.hotelregistration;

import static com.mycompany.hotelregistration.HotelRegistration.keyboard;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Esta clase es la constructora de los empleados
 * estos objetos poseen un puesto de trabajo,
 * usuario y contraseña
 * @author natha
 */
public class Employee extends Person {
    
    private Map<String, Double> hotelRoles = new HashMap<>();
        
    private String username;
    private String password;
    private String companyRole;
    
    private String setPassword(String password) {
        
        if( password.length() < 5 ) {
            return null;
        } else {
            return password;
        }
        
    }
    
    /**
     * Se encarga de construir el diccionario con
     * los puestos de trabajo que se encuentran en el
     * hotel, la llave corresponde al puesto de
     * trabajo y el valor al sueldo que tiene por dia
     * verifica que el input corresponda a una de las
     * llaves del diccionario
     * 
     * @param role: recibe un string con el puesto a asignar
     * @return retorna un string con el rol
     */
    private String roleVerifier(String role) {
        hotelRoles.put("Receptionist", 311.44);
        hotelRoles.put("Bellhop", 348.16);
        hotelRoles.put("Waitress", 207.52);
        hotelRoles.put("Manager", 738.48);
        hotelRoles.put("Housekeeper", 492.32);
        hotelRoles.put("Chef", 492.32);
        
        String assignedRole = null;
        
        for (Map.Entry<String, Double> entry : hotelRoles.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(role)) {
                assignedRole = entry.getKey();
            }
        }
        
        return assignedRole;
        
    }
    
    public Employee(String name, String lastnm, String birth, String dni, String address, String role, String password) {
        super(name, lastnm, birth, dni, address);
        
        this.username = name.concat(Integer.toString(super.getId()));
        this.companyRole = roleVerifier(role);
        this.password = setPassword(password);
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getRole(){
        return companyRole;
    }
    
    
    /**
     * Se encarga de efectuar el login de
     * usuario, se coloco en esta clase para que
     * esta sea la unica parte del archivo que tenga acceso a la
     * contraseña
     * @param password
     * @param username
     * @param employeeData: es la colección de datos donde se almacenan los empleados del sistema
     * @return retrona un valor boolean para indicar si el usuario esta autenticado o no
     */
    public static boolean login(String password, String username, List<Employee> employeeData) {
        
        try {
            
            for (Employee employee : employeeData) {
                if(employee.username.equals(username)) {
                    if ( employee.username.equals(username) && employee.password.equals(password) ) {
                        System.out.println("---------------------------------------\nWELCOME "+ employee.getFullName().toUpperCase() +"\n---------------------------------------");
                        return true;
                    } else {
                        throw new Exception("Username and password do not mach.");
                    }
                } 
            }
        
        throw new Exception("Username not found");
        
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
        return false;
    }
    
    /**
     * Retorna un csv con los atributos de la clase
     * @return 
     */
    public String csvWriter() {
        return (super.getName() + ", " + super.getLastNm() + ", " + super.getBirth() + ", " + super.getDNI() + ", " + super.getAddress() + ", " + companyRole + ", " + password);
    }
    
    /**
     * Se encarga de calcular la paga de un empledo
     * de acuerdo al mes y sus días trabajados
     * tambien toma en cuenta el bono asignado por reserva que realice
     * @param reservations: recibe la colección de reservas existentes en el sistema
     */
    public void getPaymentCheck(List<Reservation> reservations){
        
        final Double PERCENTAJE_BONUS = 8.5;
        Double dailyPayment = hotelRoles.get(companyRole);
        Double calculatedPayment;
        
        try {
            System.out.println("To calculate employee " + super.getFullName().toUpperCase() + " payment enter quantity of days worked>");
            int qtyDays = Integer.parseInt(keyboard.readLine());
            
            System.out.println("Which month do you wish to calculate? (Enter in 'MM' number format)");
            String month = keyboard.readLine();
            
            System.out.println("Of which year? (Enter in 'yyyy' number format)");
            String year = keyboard.readLine();
            
            LocalDate date = LocalDate.parse("01-" + month + "-" + year, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            int numReservations = 0;
            
            for (Reservation element : reservations) {
                if( element.getEmployee().getFullName().equals(super.getFullName()) &&  element.getCheckin().getMonth().equals(date.getMonth()) && element.getCheckin().getYear() == date.getYear() ) {
                    numReservations++;
                }
            }
            
            Double bPercent = PERCENTAJE_BONUS * numReservations;       
            Double preBonus = dailyPayment * qtyDays;
            calculatedPayment = preBonus + (preBonus * (bPercent / 100));
            
                               
                           
            System.out.println("---------------------------------------\n" + "PAYMENT REPORT FOR" + super.getFullName().toUpperCase() + " ON " + month + "/" + year
                               + "\nDAILY PAYMENT: " + dailyPayment
                               + "\nNUM. OF RESERVATIONS:" + numReservations
                               + "\nBONUS PERCENTAJE: " + bPercent
                                
                               + "\nTOTAL PAYMENT: " + calculatedPayment
                               + "\n---------------------------------------");
            
        } catch (IOException | NumberFormatException e) {
            System.err.println(e.toString());
        }

    }
    
    
}
