package business;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hieuh
 */
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Mountain;
import model.Student;
import tools.Acceptable;
import tools.Inputter;

public class Students {

    private String pathFile = "Registration.dat";
    private boolean isSaved;
    public Inputter input = new Inputter();
    public Mountains mt = new Mountains();
    public List<Student> studentList;
    public List<Student> studentList1;
    private Scanner scanner = new Scanner(System.in);
    
    public Students() {
        super();
        this.studentList = new ArrayList<>();
        this.studentList1 = new ArrayList<>();
        this.isSaved = false;

//        studentList.add(new Student("SE192251", "Quang Hieu", "0376371939", "1", 3900000));
//        studentList.add(new Student("SE654321", "Quynh Tram", "0932231928", "2", 6000000));
//        studentList.add(new Student("SE654322", "Hieu Tram", "0932231938", "3", 6000000));
//        studentList.add(new Student("SE654323", "Hieu Hieu", "0932231938", "3", 6000000));
//        studentList.add(new Student("SE654324", "Tram Tram", "0932231938", "3", 6000000));
    }

    public Students(String pathFile, boolean isSaved, Students studentsList) {
        this.pathFile = pathFile;
        this.isSaved = false;
        this.studentList = new ArrayList<>();
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = new ArrayList<>();
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void add(Student student) {
        System.out.println("Add a new student registration.");

        String id = input.inputAndLoop("Enter ID: ", Acceptable.STUDENT_ID);

        if (searchById(id) == null) {
            Student x = new Student();
            x.setId(id);
            x.setName(input.inputAndLoop("Enter NAME: ", Acceptable.NAME_VALID));
            x.setPhone(input.inputAndLoop("Enter PHONE: ", Acceptable.PHONE_VALID));
            x.setEmail(input.inputAndLoop("Enter EMAIL: ", Acceptable.EMAIL_VALID));
            String temp = input.inputAndLoop("Enter MOUNTAIN CODE (Please input from 1 to 13): ", Acceptable.CODE_VALID);
            if (mt != null && mt.isValidMountainCode(temp)) {
                Mountain mountain = mt.get(temp);
                x.setMountainCode(mountain.getMountainCode());
            } else {
                System.out.println("Invalid mountain code!");
                return;
            }

            double tuitionFee = 6000000;
            String phone = x.getPhone().substring(0, 3);
            if (Acceptable.VIETTEL_VALID.contains(phone) || Acceptable.VNPT_VALID.contains(phone)) {
                tuitionFee *= 0.65;
            }
            x.setTutionFee(0);

            studentList.add(x);
//            saveToFile();
            this.isSaved = false;
            System.out.println("Student added successfully !!!");
        } else {
            System.out.println("Student with ID " + id + " already exists!");
        }
    }

    public void update() {
        System.out.print("Enter student's ID: ");
        String id = scanner.next();
        Student temp = searchById(id);
        if (temp != null) {
            System.out.println("Update :");
            int choice;

            System.out.println("1. Name");
            System.out.println("2. Phone number");
            System.out.println("3. Email");
            System.out.println("4. Mountain peak code");
            System.out.println("5. Update all");
            System.out.print("Enter number: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    temp.setName(input.inputAndLoop("Update NAME: ", Acceptable.NAME_VALID));
                    System.out.println("Update successfully !");
                    break;

                case 2:
                    temp.setPhone(input.inputAndLoop("Update PHONE: ", Acceptable.PHONE_VALID));
                    temp.calTuitionFee(temp.getPhone());
                    System.out.println("Update successfully !");
                    break;

                case 3:
                    temp.setEmail(input.inputAndLoop("Update EMAIL: ", Acceptable.EMAIL_VALID));
                    System.out.println("Update successfully !");
                    break;

                case 4:
                    temp.setMountainCode(input.inputAndLoop("Update MOUNTAIN CODE: ", Acceptable.CODE_VALID));
                    System.out.println("Update successfully !");
                    break;

                case 5:
                    temp.setName(input.inputAndLoop("Update NAME: ", Acceptable.NAME_VALID));
                    temp.setPhone(input.inputAndLoop("Update PHONE: ", Acceptable.PHONE_VALID));
                    temp.setEmail(input.inputAndLoop("Update EMAIL: ", Acceptable.EMAIL_VALID));
                    temp.setMountainCode(input.inputAndLoop("Update MOUNTAIN CODE: ", Acceptable.CODE_VALID));
                    System.out.println("Update successfully !");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                    return;
            }
        } 
        else {
            System.out.println("This student has not registered yet.");
        } 
    }

    public void delete(String id) {
        boolean found = false;
        if (studentList.isEmpty()) {
           System.out.println("List is empty!");
           return;
       }
        id = input.inputAndLoop("Enter student's ID to delete: ", Acceptable.STUDENT_ID);
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
                System.out.println("------------------------------------------------------------");
                System.out.println("Student ID  : " + studentList.get(i).getId());
                System.out.println("Name        : " + studentList.get(i).getName());
                System.out.println("Phone       : " + studentList.get(i).getPhone());
                System.out.println("Mountain    : " + studentList.get(i).getMountainCode());
                System.out.println("Fee         : " + String.format("%,.0f", studentList.get(i).getTuitionFee()));
                System.out.print("------------------------------------------------------------\n");
                System.out.print("Are you sure you want to delete this registration? (Y/N):");
                String confirm = scanner.next();
                if (confirm.equalsIgnoreCase("y")) {
                    studentList.remove(i);
                    System.out.println("The registration has been successfully deleted.");
                    this.isSaved = false;
                    found = true;
                    break;
                } else if (confirm.equalsIgnoreCase("n")) {
                    return;
                }
            }
        }

        if (!found) {
            System.out.println("This student has not registered yet.");
        }
    }

    public Student searchById(String id) {
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getId().equalsIgnoreCase(id)) {
                return studentList.get(i);
            }
        }
        System.out.println("This student has not registered yet.");
        return null;
    }

    public void searchByName(String name) {
        name = input.inputAndLoop("Enter student Name: ", Acceptable.NAME_VALID);
        boolean found = false;
        studentList1 = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                studentList1.add(studentList.get(i));
                found = true;
            }
        }
        if (studentList1 == null) {
            System.out.println("No one matches the search criteria!");
        } else {
            showAll(studentList1);
        }
    }

    public void showAll(List<Student> list) {
        if (list == null || list.isEmpty()) {
            System.out.println("No students have registered yet.");
            return;
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println("| Student ID   | Name            | Phone           | PeakCode                  | Fee             |");
        System.out.println("--------------------------------------------------------------------------------------------------");
        for (Student student : list) {
            System.out.println(student);
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.println();
    }

    public List<Student> filterByCampusCode(String campusCode) {
        campusCode = input.inputAndLoop("", Acceptable.CAMPUS_CODE);
        List<Student> filtered = new ArrayList<>();
        for (Student student : studentList) {
            if (student.getId().startsWith(campusCode)) {
                filtered.add(student);
            }
        }
        return filtered;
    }

    public void statisticalizeByMountainPeak() {

        Map<String, Integer> particinputantCount = new HashMap<>();
        Map<String, Double> totalCost = new HashMap<>();

        for (Student s : studentList) {
            particinputantCount.put(s.getMountainCode(), particinputantCount.getOrDefault(s.getMountainCode(), 0) + 1);
            totalCost.put(s.getMountainCode(), totalCost.getOrDefault(s.getMountainCode(), 0.0) + s.getTuitionFee());
        }

        // Hiển thị kết quả
        System.out.println("Statistics of Registration by Mountain Peak:");
        System.out.println("----------------------------------------------------------");
        System.out.printf("%-10s | %-22s | %-15s%n", "Peak Name", "Number of Particinputants", "Total Cost");
        System.out.println("----------------------------------------------------------");

        for (String mountain : particinputantCount.keySet()) {
            System.out.printf("%-10s | %-25d | %,10.0f%n", mountain, particinputantCount.get(mountain), totalCost.get(mountain));
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("");
    }

    public void saveToFile() {

        if (this.isSaved) {
            return;
        }
        try {
            File f = new File(this.pathFile);
            FileOutputStream fos = null;

            fos = new FileOutputStream(f);
            // Tạo objectoutpúttream để chuyển dữ liệu xuống thiết bị
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // 4. Lặp để ghi dữ liệu
            for (Student student : studentList) {
                oos.writeObject(student);
            }

            oos.close();
            // cập nhật trang thái file
            this.isSaved = true;
            System.out.println("Registration data has been successfully saved to '" + this.pathFile + "'.");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
//    public void readFromFile() {
//        FileInputStream fis = null;
//        try {
//            File f = new File(this.pathFile);
//            if (!f.exists()) {
//                System.out.println("Registration.dat file is not found !.");
//                return;
//            }
//            fis = new FileInputStream(f);
//            ObjectInputStream ois = new ObjectInputStream(fis);
//
//            if (studentList == null) {
//                studentList = new ArrayList<>();
//            }
//
//            while (fis.available() > 0) {
//                Student x = (Student) ois.readObject();
//                studentList.add(x);
//            }
//
//            // Dong object sau ki doc xong
//            ois.close();
//            this.isSaved = true;
//        } catch (FileNotFoundException e) {
//            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, e);
//        } catch (IOException e) {
//            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, e);
//        } catch (ClassNotFoundException e) {
//            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, e);
//        } catch (Exception e) {
//            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, e);
//        } finally {
//            try {
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (IOException e) {
//                Logger.getLogger(Students.class.getName()).log(Level.SEVERE, null, e);
//            }
//        }
//    }
//    
    
    public void readFromFile() {
    FileInputStream fis = null;
    ObjectInputStream ois = null;
    try {
        File f = new File(this.pathFile);
        if (!f.exists()) {
            System.out.println("Registration.dat file is not found!");
            return;
        }

        fis = new FileInputStream(f);
        ois = new ObjectInputStream(fis);

        if (studentList == null) {
            studentList = new ArrayList<>();
        }

        // Đọc các đối tượng từ tệp cho đến khi gặp EOF
        while (true) {
            try {
                Student x = (Student) ois.readObject();
                studentList.add(x);
            } catch (EOFException e) {
                break;  // Dừng khi gặp EOF (End Of File)
            }
        }

        this.isSaved = true;
//        showAll(studentList);

    } catch (FileNotFoundException e) {
        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, "File not found", e);
    } catch (IOException e) {
        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, "Error reading the file", e);
    } catch (ClassNotFoundException e) {
        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, "Class not found", e);
    } catch (Exception e) {
        Logger.getLogger(Students.class.getName()).log(Level.SEVERE, "Unexpected error", e);
    } finally {
        try {
            if (ois != null) {
                ois.close();  // Đảm bảo ObjectInputStream luôn được đóng
            }
            if (fis != null) {
                fis.close();  // Đảm bảo FileInputStream luôn được đóng
            }
        } catch (IOException e) {
            Logger.getLogger(Students.class.getName()).log(Level.SEVERE, "Error closing the streams", e);
        }
    }
}

    public void exit() {
        if (!isSaved) {
            String text = input.inputAndLoop("Do you want to save changes before exit? (Y/N): ", Acceptable.YN_VALID);
            if (text.equalsIgnoreCase("Y")) {
                saveToFile();
            } else {
                return;
            }
        }
        return;
    }


}
