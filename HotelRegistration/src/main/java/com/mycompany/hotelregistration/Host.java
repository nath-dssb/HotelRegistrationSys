package com.mycompany.hotelregistration;

/**
 * Clase de representación de los huespedes que seran registrados en el sistema
 * contiene unicamente su metodo constructor y getters
 * @author natha
 */
public class Host extends Person {
    
    private String profession;
    
    public Host(String name, String lastnm, String birth, String dni, String address, String profession) {
        super(name, lastnm, birth, dni, address);
        
        this.profession = profession;
    }
    
    /**
     * Escribe los datos del objeto 
     * a manera de csv para registrarlo en el sistema
     * @return 
     */
    public String csvWriter() {
        return (super.getName() + ", " + super.getLastNm() + ", " + super.getBirth() + ", " + super.getDNI() + ", " + super.getAddress() + ", " + profession);
    }
    
    public String getProfession() {
         return profession;
    }
    
}
