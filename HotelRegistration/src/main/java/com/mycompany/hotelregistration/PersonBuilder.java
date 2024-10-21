package com.mycompany.hotelregistration;

import static com.mycompany.hotelregistration.HotelRegistration.keyboard;

/**
 *
 * @author natha
 */
public class PersonBuilder {
    
    /**
     * Funcion privada para facilitar 
     * la verificación de datos.
     * @param s recibe un string a analizar.
     * @return retorna un valor booleano.
     */
    private static boolean inputChecker(String s) {
        return (s.isBlank() || s.isEmpty());
    }
    
    /**
     * Se encarga de construir un nuevo empleado.
     *
     * @return r retrona un objeto de tipo empleado.
     */
    public static Employee employeeBuilder() {
        
        Employee newItem = new Employee(" ", " ", "01-01-0001", " ", " ", " ", " ");
        
        try {
            System.out.println("Let's start registering a new employee! Enter their first name: ");
            String name = keyboard.readLine();
            
            if (inputChecker(name)) {
                throw new Exception("Must enter a name.");
            }
            
            System.out.println("Enter their last name: ");
            String lName = keyboard.readLine();
            
            if (inputChecker(lName)) {
                throw new Exception("Must enter a last name.");
            }
            
            System.out.println("Enter their birth, please follow the dd-mm-yyyy format: ");
            String birth = keyboard.readLine();
            
            if (inputChecker(birth)) {
                throw new Exception("Must enter a valid date.");
            }

            System.out.println("Enter the type of identification provided by employee: ");
            String dni = keyboard.readLine();
            
            if (inputChecker(dni)) {
                throw new Exception("Must enter an identification.");
            }

            
            System.out.println("Enter their address: ");
            String address = keyboard.readLine();
            
            if (inputChecker(address)) {
                throw new Exception("Must enter an address.");
            }
            
            System.out.println("What job will they do on the hotel?: ");
            String role = keyboard.readLine();
            
            if (inputChecker(role)) {
                throw new Exception("Couln't assign role.");
            }
            
            System.out.println("Finally, enter a password so the employee can access the system: ");
            String assignedPassword = keyboard.readLine();
            
            if (assignedPassword == null || inputChecker(assignedPassword)) {
                throw new Exception("Couldn't assign password.");
            }
            
            newItem = new Employee( name, lName, birth, dni, address, role, assignedPassword );
            
        
        } catch (Exception e) {
            System.err.println(e.toString() + " Couldn't create user.");
        }
        
        if(!newItem.getFullName().isBlank()) {
            System.out.println("Employee created, their username is: " + newItem.getUsername());
        }
        
        
        return newItem;
    }
    
    /**
     * Se encarga de construir un nuevo host con inputs del usuario
     * @return retorna un host
     */
        public static Host hostBuilder() {
        Host newItem = new Host(" ", " ", "01-01-0001", " ", " ", " ");
        
        try {
            
            System.out.println("Let's start registering a new host! Enter their first name: ");
            String name = keyboard.readLine();
            
            if (inputChecker(name)) {
                throw new Exception("Must enter a name.");
            }
            
            System.out.println("Enter their last name: ");
            String lName = keyboard.readLine();
            
            if (inputChecker(lName)) {
                throw new Exception("Must enter a last name.");
            }
            
            System.out.println("Enter their birth, please follow the dd-mm-yyyy format: ");
            String birth = keyboard.readLine();
            
            if (inputChecker(birth)) {
                throw new Exception("Must enter a valid date.");
            }
            
            System.out.println("Enter the type of identification provided by employee: ");
            String dni = keyboard.readLine();
            
            if (inputChecker(dni)) {
                throw new Exception("Must enter an identification.");
            }
            
            System.out.println("Enter their address: ");
            String address = keyboard.readLine();
            
            if (inputChecker(address)) {
                throw new Exception("Must enter an address.");
            }

            System.out.println("Enter their profession: ");
            String assignedProfession = keyboard.readLine();
            
            if (inputChecker(assignedProfession)) {
                throw new Exception("Must enter a profession.");
            }
            
            newItem = new Host( name, lName, birth, dni, address, assignedProfession );
        
        } catch (Exception e) {
            System.err.println(e.toString() + " Couldn't create host.");
        }
        
        return newItem;
    }
    
}
