package com.mycompany.hotelregistration;

import static com.mycompany.hotelregistration.HotelRegistration.keyboard;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Esta clase es encargada de todas las verificaciones que se precisan
 * realizar para efectuar una reservacion
 * 
 * Sus atributos son las colecciones de datos de reservaciones y habitaciones
 * @author natha
 */
public class ReservationBuilder {
        private List<Reservation> reservationData;
        private List<HotelRoom> roomData;
        
        public ReservationBuilder(List<Reservation> reservationData, List<HotelRoom> roomData) {
            this.reservationData = reservationData;
            this.roomData = roomData;
        }
        
        /**
         * Valida que los datos de una reserva no sean nulos
         * arroja exceptiones
         * @param reservationData: recibe la reservacion efectuada
         */
        private void validateData(Reservation reservationData) {
        
        try {
            
            LocalDate dummyDate = LocalDate.parse("01-01-0001", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            
            if(reservationData.getCheckin().equals(dummyDate) || reservationData.getCheckout().equals(dummyDate)) {
                throw new Exception("You must provide a Check-in and Check-out date.");
            }
            
            if( reservationData.getRoom().getRoomTheme().isBlank() || reservationData.getRoom().getRoomTheme().isEmpty()) {
                throw new Exception("Room must be available and existent.");
            }
            
            if( reservationData.getHost().getFullName().isBlank() || reservationData.getHost().getFullName().isEmpty() ) {
                throw new Exception("Host must be registered.");
            }
            
            if( reservationData.getEmployee().getFullName().isBlank() || reservationData.getEmployee().getFullName().isEmpty() ) {
                throw new Exception("The registration must be done by an existing employee.");
            }
        
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        
    }
    
        /**
         * Checa si una habitacion esta disponible 
         * a partir de los parametros ingresados
         * @param firstDate: fecha de checkin deseada
         * @param secondDate: fecha de checkout deseada
         * @param roomNum: numero de habitacion deseada
         * @param reservations: colección de reservaciones
         * @return: retorna un valor booleano para indicar si la reserva esta disponible o no
         */
    private boolean roomAvailability( LocalDate firstDate, LocalDate secondDate, int roomNum, List<Reservation> reservations ) {
        
        for(Reservation el : reservations) {
            if( el.getRoom().getId() == roomNum ) {
                if(firstDate.isAfter(el.getCheckin()) && firstDate.isBefore(el.getCheckout()) || secondDate.isAfter(el.getCheckin()) && secondDate.isBefore(el.getCheckout())) {
                    return false;
                }
            }
        }
        
        return true;
        
    }
    
    /**
     * Realiza una reservacion, obteniendo el input del usuario para
     * construir el objeto que retornara
     * 
     * @return: objeto de clase Reservation
     */
    public Reservation makeReservation( ) {
        
        String selectedCheckin = "01-01-00001";
        String selectedCheckout = "01-01-00001";
        int registerCompanions = 0;
        int selectedRoom = 0;
        String providedHost= " ";
        String registerer = " ";
        
        try{

            while(true) {
                System.out.println("Let's register a new reservation! Start by indicating the desired room: ");
                selectedRoom = Integer.parseInt(keyboard.readLine());
                
                 System.out.println("Now, please provide the check-in date in 'dd-MM-yyyy' numeric format:");
                 selectedCheckin = keyboard.readLine();

                 System.out.println("Write the checkout date in 'dd-MM-yyyy' numeric format:");
                 selectedCheckout = keyboard.readLine();
                 
                 if(LocalDate.parse(selectedCheckout, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(LocalDate.parse(selectedCheckin, DateTimeFormatter.ofPattern("dd-MM-yyyy"))) ) {
                     throw new Exception("Checkout date must be after checkin date.");
                 }

                 System.out.println("Checking for room availability...");
                 
                 if (roomAvailability(LocalDate.parse(selectedCheckin, DateTimeFormatter.ofPattern("dd-MM-yyyy")), LocalDate.parse(selectedCheckout, DateTimeFormatter.ofPattern("dd-MM-yyyy")), selectedRoom, reservationData) != true) {
                     
                     System.out.println("Room's not available, try another one? (Y/N)");
                     String choice = keyboard.readLine();
                     
                     if(choice.equalsIgnoreCase("N")) {
                         return new Reservation(selectedCheckin, selectedCheckout, selectedRoom, providedHost, registerCompanions, registerer);
                     }
                 
                 } else {
                     System.out.println("Room's available! Please proceed with registration.");
                     break;
                 }
                
            }
            
            System.out.println("Is the host already registered on the system? (Y/N):");
            String registerHost = keyboard.readLine();
            
            if( registerHost.equalsIgnoreCase("Y") ) {
                System.out.println("Please provide host name: ");
                providedHost = keyboard.readLine();
                
            } else { 
                providedHost = PersonBuilder.hostBuilder().getName();
            }
            
            System.out.println("How many companions is the host bringing?:");
            registerCompanions = Integer.parseInt(keyboard.readLine());
            
            for(HotelRoom el: roomData) {
                if(registerCompanions > el.getCapacity()) {
                    throw new Exception("Companions exceed room capacity.");
                }
            }
           
            System.out.println("Finally, what's the name of the employee that's registering this reservation? ");
            registerer = keyboard.readLine();
            
        } catch(Exception e) {
            System.err.println(e.toString() + "Can't make reservation.");
        }
        
        Reservation newItem = new Reservation(selectedCheckin, selectedCheckout, selectedRoom, providedHost, registerCompanions, registerer);
        validateData(newItem);
        return newItem;
    }
}
