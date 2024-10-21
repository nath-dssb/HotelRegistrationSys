package com.mycompany.hotelregistration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Esta es la clase padre de las clases
 * Employee y Host, 
 * contiene unicamente un metodo constructor y 
 * getters.
 * @author natha
 */
public class Person {
    private static int ID_COLLECTION = 0;
    
    private int id;
    private String docOfIdentity;
    private String name;
    private String lastName;
    private LocalDate birth;
    private String address;
    
    public Person(String name, String lastnm, String birth, String dni, String address) {
        this.id = ID_COLLECTION++;
        this.name = name;
        this.lastName = lastnm;
        this.birth = LocalDate.parse(birth, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.docOfIdentity = dni;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public String getDNI() {
        return docOfIdentity;
    }
    
    public String getFullName() {
        return name + " " + lastName;
    }
    
    public String getLastNm() {
        return lastName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getBirth() {
        return birth.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
    
    public String getAddress() {
        return address;
    }
}
  