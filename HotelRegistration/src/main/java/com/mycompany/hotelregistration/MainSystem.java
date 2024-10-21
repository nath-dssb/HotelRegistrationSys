package com.mycompany.hotelregistration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Sistema principal, se encarga de realizar 
 * el filtrado y presentacion de datos de las colecciones establecidas
 * @author natha
 */
public class MainSystem {
    
    /**
     * Los getters de tipo void imprimen los datos de cada objeto a forma de string
     * @param: reciben las colecciones de datos a revisar
     */
    
    public void getEmployeeData(List<Employee> employeeCollection) {
        for(Employee el : employeeCollection) {
            System.out.println("\n---------------------------------\n" 
            + "ID: " + el.getId() + " NAME OF EMPLOYEE: " + el.getFullName()
            + "\nBIRTHDAY: " + el.getBirth() + " ROLE: " + el.getRole()
            + "\nTYPE OF DOI: " + el.getDNI() +  "\nADDRESS: " + el.getAddress()
            + "\nUSERNAME: " + el.getUsername()
            + "\n---------------------------------\n");
        }
    }
    
    public void getHostData(List<Host> hostCollection) {
       for(Host el : hostCollection) {
           System.out.println("\n---------------------------------\n" 
            + "ID: " + el.getId() + " NAME OF EMPLOYEE: " + el.getFullName()
            + "\nBIRTHDAY: " + el.getBirth() + " ROLE: " + el.getProfession()
            + "\nTYPE OF DOI: " + el.getDNI() +  "\nADDRESS: " + el.getAddress()
            + "\n---------------------------------\n");
        }
    }
    
    public void getRoomData(List<HotelRoom> roomCollection) {
        for(HotelRoom el : roomCollection) {
            System.out.println("\n---------------------------------\n"
            + "ROOM #" + el.getId() + " ON FLOOR " + el.getFloor()
            + "\nTHEME: " + el.getRoomTheme() + "TYPE: " + el.getType()
            + "\nCAPACITY: " + el.getCapacity() + " FARE: " + el.getPrice());
        }
    }
    
    public void getReservationData(List<Reservation> reservationCollection) {
        for(Reservation el : reservationCollection) {
            System.out.println(
            "\n---------------------------------\n"
            + "\nCHECK-IN DATE: " + el.getCheckin() + " CHECK-OUT DATE: " + el.getCheckout()
            + "\nOCCUPIED ROOM: " + el.getRoom().getId() 
            + "\nRESERVED BY HOST " + el.getHost().getFullName() + " ACCOMPANIED BY " + el.getCompanions() + " PEOPLE"
            + "\nRESERVATION MADE BY EMPLOYEE " + el.getEmployee().getFullName()
            + "\nCALCULATED FARE: $" + el.getEstimatedFare()
            );
        }
    }
    
    
    /**
     * Este metodo realiza un filtrado de reservas de acuerdo a un día ingresado
     * @param date
     * @param reservationCollection
     * @return 
     */
    
    public List<Reservation> reservationsDayFilter(String date, List<Reservation> reservationCollection) {
        List<Reservation> filteredData = new ArrayList<>();
        
            for( Reservation el : reservationCollection ) {
                if( el.getCheckin().equals(LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"))) ) {
                    filteredData.add(el);
                }
            }
            
        return filteredData;
    }
    
    /**
     * Este metodo realiza un filtrado de datos de reservaciones 
     * de acuerdo al nombre del empleado
     * @param employee
     * @param reservationCollection
     * @return 
     */
    
    public List<Reservation> reservationEmployeeFilter(String employee, List<Reservation> reservationCollection) {
        List<Reservation> filteredData = new ArrayList<>();
        
            for( Reservation el : reservationCollection ) {
                if( el.getEmployee().getFullName().equalsIgnoreCase(employee)) {
                    filteredData.add(el);
                }
            }
        
        return filteredData;
    }
    
    /**
     * Este metodo realiza un filtrado de datos de reservaciones
     * de acuerdo al huesped y un rango de flechas 
     * 
     * @param host
     * @param upperDate
     * @param laterDate
     * @param reservationCollection
     * @return 
     */
    
    public List<Reservation> reservationHostDateFilter(String host, String upperDate, String laterDate, List<Reservation> reservationCollection) {
        List<Reservation> filteredData = new ArrayList<>();
        
        LocalDate upper = LocalDate.parse(upperDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate later = LocalDate.parse(laterDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
            for( Reservation el : reservationCollection ) {
                if( el.getHost().getFullName().equalsIgnoreCase(host) ) {
                    if(el.getCheckin().isEqual(upper) || el.getCheckin().isAfter(upper) && el.getCheckin().isBefore(later)) {
                        filteredData.add(el);
                    }
                }
            }
        
        return filteredData;
    }
    
    
}
