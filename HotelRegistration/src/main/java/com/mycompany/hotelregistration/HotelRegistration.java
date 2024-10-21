package com.mycompany.hotelregistration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * El metodo main llama los metodos de otras clases e integra el sistema de
 * manera iterativa para que tenga continuidad
 * @author natha
 */
public class HotelRegistration {
    
    public static BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
    
    public static List<Employee> employeeData = new ArrayList<>();
    public static List<Host> hostData = new ArrayList<>();
    public static List<HotelRoom> roomData = new ArrayList<>();
    public static List<Reservation> reservationData = new ArrayList<>();
    
    public static void excecuter(String action) {
        
        MainSystem systemCaller = new MainSystem();
        ReservationBuilder reservationMaker = new ReservationBuilder(reservationData, roomData);
        
        if(action.equalsIgnoreCase("A")) {
            
            Employee newEmployee = PersonBuilder.employeeBuilder();
            
            employeeData.add(newEmployee);
            DataManager.dataWriter("EmployeeData.txt", newEmployee.csvWriter());
        
        } else if(action.equalsIgnoreCase("B")) {
            Host newHost = PersonBuilder.hostBuilder();
            
            hostData.add(newHost);
            DataManager.dataWriter("HostData.txt", newHost.csvWriter());
        
        } else if(action.equalsIgnoreCase("C")) {
            HotelRoom newRoom = HotelRoom.hotelRoomBuilder();
            
            roomData.add(newRoom);
            DataManager.dataWriter("RoomData.txt", newRoom.csvWriter());
        
        } else if(action.equalsIgnoreCase("D")) {
            Reservation newReservation = reservationMaker.makeReservation();
            
            reservationData.add(newReservation);
            DataManager.dataWriter("RegistrationData.txt", newReservation.getCSV());
        } else if(action.equalsIgnoreCase("E")) {
            
            try {
                System.out.println("Enter the name of the employee you want to obtain the record: ");
                String name = keyboard.readLine();
            
                List <Reservation> filteredData = systemCaller.reservationEmployeeFilter(name, reservationData);
                
                if(filteredData == null) {
                    throw new Exception("Data not found.");
                }
                
                systemCaller.getReservationData(filteredData);
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            
        
        } else if(action.equalsIgnoreCase("F")) {
            
            try{
                System.out.println("Enter host name:");
                String name = keyboard.readLine();
                
                System.out.println("Enter upperbound date as dd-MM-yyyy:");
                String upper = keyboard.readLine();
                
                System.out.println("Enter lowerbound date as dd-MM-yyyy:");
                String lower = keyboard.readLine();
                
                if(LocalDate.parse(lower, DateTimeFormatter.ofPattern("dd-MM-yyyy")).isBefore(LocalDate.parse(upper, DateTimeFormatter.ofPattern("dd-MM-yyyy"))) ) {
                     throw new Exception("Lowerbound date must be after upperbound date.");
                }
                
                List<Reservation> filteredData = systemCaller.reservationHostDateFilter(name, upper, lower, reservationData);
                
                if(filteredData == null) {
                    throw new Exception("Data not found.");
                }
                
                systemCaller.getReservationData(filteredData);
                
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        
        } else if(action.equalsIgnoreCase("G")) {
            
            systemCaller.getRoomData(roomData);
        
        } else if(action.equalsIgnoreCase("H")) {
            
            systemCaller.getReservationData(reservationData);
        
        } else if(action.equalsIgnoreCase("I")) {
            
            try {
                System.out.println("Pleas enter the name of the employee you want the payment report: ");
                String name = keyboard.readLine();
                
                for(Employee el : employeeData) {
                    if(el.getFullName().equalsIgnoreCase(name)) {
                        el.getPaymentCheck(reservationData);
                        break;
                    }
                }
                
                throw new Exception("Employee not found.");
                
            } catch (Exception e) {
                System.err.println(e.toString());
            }
            
        } else if(action.equalsIgnoreCase("J")) {
            
            systemCaller.getEmployeeData(employeeData);
            
        } else if(action.equalsIgnoreCase("K")) {
            
            systemCaller.getHostData(hostData);
            
        } else if(action.equalsIgnoreCase("L")) {
            try{
                
                System.out.println("Enter date on format dd-MM-yyyy: ");
                String date = keyboard.readLine();
                
                List<Reservation> filteredData = systemCaller.reservationsDayFilter(date, reservationData);
                
                if(filteredData == null) {
                    throw new Exception("Data not found.");
                }
                
                systemCaller.getReservationData(filteredData);
            
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
    }
    

    public static void main(String[] args) throws Exception {
        DataManager.employeesInitializer("EmployeeData.txt");
        DataManager.hostsInitializer("HostData.txt");
        DataManager.roomsInitializer("RoomData.txt"); 
        DataManager.reservationsInitializer("RegistrationData.txt");
        
        while(true) {
            
            System.out.println("""
                               ------------------------------------------------------------------------------
                                                WELCOME TO THE HOTEL REGISTRATION SYSTEM
                               ------------------------------------------------------------------------------""");
            System.out.println("""
                                                                  /\\
                                                             /\\  //\\\\
                                                      /\\    //\\\\///\\\\\\        /\\
                                                     //\\\\  ///\\////\\\\\\\\  /\\  //\\\\
                                        /\\          /  ^ \\/^ ^/^  ^  ^ \\/^ \\/  ^ \\
                                       / ^\\    /\\  / ^   /  ^/ ^ ^ ^   ^\\ ^/  ^^  \\
                                      /^   \\  / ^\\/ ^ ^   ^ / ^  ^    ^  \\/ ^   ^  \\       *
                                     /  ^ ^ \\/^  ^\\ ^ ^ ^   ^  ^   ^   ____  ^   ^  \\     /|\\
                                    / ^ ^  ^ \\ ^  _\\___________________|  |_____^ ^  \\   /||o\\
                                   / ^^  ^ ^ ^\\  /______________________________\\ ^ ^ \\ /|o|||\\
                                  /  ^  ^^ ^ ^  /________________________________\\  ^  /|||||o|\\
                                 /^ ^  ^ ^^  ^    ||___|___||||||||||||___|__|||      /||o||||||\\
                                / ^   ^   ^    ^  ||___|___||||||||||||___|__|||          | |
                               / ^ ^ ^  ^  ^  ^   ||||||||||||||||||||||||||||||oooooooooo| |ooooooo
                               ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
                               """);
            System.out.println("\n------------------------------------------------------------------------------\n");
            System.out.print("What's your username?> ");
            String username = keyboard.readLine();
            System.out.print("What's your password?> ");
            String password = keyboard.readLine();
            

            boolean verification = Employee.login(password, username, employeeData);

            if(verification) {
                break;
            } else {
                System.out.println("Try again? (Y/N)");
                String option = keyboard.readLine();
                
                if(option.equalsIgnoreCase("N")) {
                    System.out.println("Thank you for using our system :)! See you later.");
                    return;
                }
            }
        }
        
        while(true) {
            System.out.println("What do you want to do?");
            System.out.println("""
                               ---------------------------------------
                               PRESS THE KEY ASIGNED TO THE TASK YOU WANT TO PERFORM:
                               A -- ADD A NEW EMPLOYEE
                               B -- ADD A NEW HOST
                               C -- ADD A NEW ROOM
                               D -- MAKE A NEW RESERVATION
                               E -- VIEW EMPLOYEE RECORD PER RESERVATION
                               F -- VIEW HOST RESERVATIONS RECORD PER DATE RANGE
                               G -- VIEW ROOM RECORD
                               H -- VIEW RESERVATIONS RECORD
                               I -- MAKE EMPLOYEE PAYMENT REPORT
                               J -- VIEW EMPLOYEE DATA
                               K -- VIEW HOST DATA
                               L -- VIEW RESERVATIONS MADE ON A CERTAIN DAY
                               ---------------------------------------""");
            String[] existentAction = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L"};
            String userAction = keyboard.readLine();
            try {
                for(String el : existentAction){
                    if( userAction.equalsIgnoreCase(el) ) {
                        excecuter(userAction);
                        break;
                    }
                }

                throw new Exception("Action not existent on the system.");

            } catch (Exception e) {
                System.out.println(e.toString());
            }
            
            System.out.println("Want to do something else? (Y/N):");
            String choice = keyboard.readLine();
            
            if(choice.equalsIgnoreCase("N")) {
                break;
            }
        }
        
    }
}
