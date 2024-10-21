package com.mycompany.hotelregistration;


import static com.mycompany.hotelregistration.HotelRegistration.employeeData;
import static com.mycompany.hotelregistration.HotelRegistration.hostData;
import static com.mycompany.hotelregistration.HotelRegistration.roomData;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Clase de las reservas
 * contiene su metodo constructor 
 * y metodos validadores
 * @author natha
 */
public class Reservation {
    
    private LocalDate checkin;
    private LocalDate checkout;
    private HotelRoom reservedRoom;
    private Host host;
    private int hostCompanions;
    private Employee reservationRegisterer;
    
    /**
     * Los assigners en esta calse son metodos privados 
     * que reciben un parametro de tipo string o int
     * y los asocian a un objeto existente en la
     * lista global del programa
     * @param name, id: reciben un nombre o un id para asociarlo a su respectivo objeto
     * @return retorna un objeto del tipo solicitado
     */
    private Host hostAssigner(String name) {
        
        try {
            for(Host el : hostData) {
                
                if(el.getFullName().equals(name)) {
                    return el;
                }
            }
            throw new Exception("Host doesn't exist you must create one or indicate an existing one.");
        
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
        
        return null;
    }
    
    private Employee employeeAssigner(String name) {
        
        try{
            for(Employee el : employeeData) {
                if(el.getFullName().equals(name)) {
                    return el;
                }
            }
            
            throw new Exception("Employee doesn't exist you must create one or indicate an existing one.");
        } catch(Exception e) {
            System.err.println(e.toString());
        }
        
        return null;
    }
    
    private HotelRoom roomAssigner(int roomID) {
        
        try{
            for(HotelRoom el : roomData) {
                if(el.getId() == roomID) {
                    return el;
                } 
            }
            throw new Exception("Room doesn't exist. Register existing one.");
        } catch(Exception e) {
            System.err.println(e.toString());
        }

        return null;
    
    }
    
    public Reservation(String checkin, String checkout, int roomId, String host, int hostCompanions, String reserver){
        this.checkin = LocalDate.parse(checkin, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        this.checkout = LocalDate.parse(checkout, DateTimeFormatter.ofPattern("dd-MM-yyyy")); 
        this.reservedRoom = roomAssigner(roomId);
        this.host = hostAssigner(host);
        this.hostCompanions = hostCompanions;
        this.reservationRegisterer = employeeAssigner(reserver);
    }
    
    public Employee getEmployee() {
        return reservationRegisterer;
    }
    
    public LocalDate getCheckin() {
        return checkin;
    }
    
    public LocalDate getCheckout() {
        return checkout;
    }
    
    public HotelRoom getRoom() {
        return reservedRoom;
    }
    
    public Host getHost(){
        return host;
    }
    
    public int getCompanions() {
        return hostCompanions;
    }
    
    public String getCSV() {
        return checkin.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ", " + checkout.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + ", "
                + Integer.toString(reservedRoom.getId()) + ", " + host.getFullName() + ", " + Integer.toString(hostCompanions) + ", " + reservationRegisterer.getFullName();
    }
    
    public double getEstimatedFare() {
        long days = ChronoUnit.DAYS.between(checkin, checkout);
        
        return reservedRoom.getPrice() * days;
    }

}