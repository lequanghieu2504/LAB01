package tools;

import java.util.Scanner;
import java.util.List;
import model.Student;
import business.*;
import java.util.InputMismatchException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hieuh
 */
public class Menu {

    private Scanner scanner = new Scanner(System.in);
    public Mountains mt = new Mountains();
    public Students stu = new Students();
    public Student st = new Student();
    public Inputter input = new Inputter();
    public List<Student> stl = stu.getStudentList();

    public void showMenu() {
        mt.readFromFile();
        stu.readFromFile();
        
        int choice;

        do {
            System.out.println("====== MENU ======");
            System.out.println("1. New Registration");                                                        // Add a new student registration
            System.out.println("2. Update Registration Information");                                 // Modify existing registration details
            System.out.println("3. Display Registered List");                                              // Show the list of all registered students
            System.out.println("4. Delete Registration Information");                                  // Remove a student's registration record
            System.out.println("5. Search Participants by Name");                                    // Find registered students based on their names
            System.out.println("6. Filter Data by Campus");                                              // Display registrations specific to a campus
            System.out.println("7. Statistics of Registration Numbers by Location");          // Generate statistics on the number of registrations for each location.
            System.out.println("8. Save Data to File");                                                      // Store registration data in a file
            System.out.println("9. Exit the Program");                                                      // End the program execution
            System.out.print("Choose your option: ");
            
            choice = getInput();

            switch (choice) {
                case 1:
                    addNewRegistration();
                    break;

                case 2:
                    update();
                    break;

                case 3:
                    display();
                    break;

                case 4:
                    remove();
                    break;

                case 5:
                    searchStudentByName();
                    break;

                case 6:
                    filterDataByCampusCode();
                    break;

                case 7:
                    statisticalizeByMountainPeak();
                    break;
                    
                case 8:
                    saveToFile();
                    break;
                    
                 case 9:
                      exit();
                      return;
            }
        } while (choice !=9);
    }

    private int getInput() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("This function is not available.");
            scanner.next();     // xóa dữ liệu không hợp lệ
            return -1;
        }
    }

    private void addNewRegistration() {
        Student student = new Student();
        stu.add(student);
    }

    public void update() {
        stu.update();
    }

    public void display() {
        stu.showAll(stu.studentList);
    }

    public void remove() {
        stu.delete(st.getPhone());
    }

    public void searchStudentByName() {
        stu.searchByName(st.getPhone());
    }

    public void filterDataByCampusCode() {
        System.out.println("Here is Campus Map:");
        System.out.println("SE : HO CHI MINH");
        System.out.println("CE : CAN THO");
        System.out.println("DE : DA NANG"); 
        System.out.println("HE : HA NOI");
        System.out.println("QE : QUY NHON");
        System.out.print("Enter campus code [HE-CE-DE-SE-QE]:");
        stu.showAll(stu.filterByCampusCode(st.getPhone()));
    }
    
    public void statisticalizeByMountainPeak(){
        stu.statisticalizeByMountainPeak();
    }
    
    public void saveToFile(){
        stu.saveToFile();
    }
    
    public void exit(){
        stu.exit();
    }
    
    
    

}
