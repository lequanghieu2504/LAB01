/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.util.Scanner;

/**
 *
 * @author hieuh
 */
public class Inputter {

    private Scanner ndl;

    public Inputter() {
        ndl = new Scanner(System.in);
    }

    // dùng để nhập dữ liệu dạng chuỗi, tự động loại bỏ khoảng trắng đầu cuối
    public String getString(String mess) {
        System.out.print(mess);
        return ndl.nextLine().trim();
    }

//    dùng để nhập dữ liệu dạng số
    public int getInt(String mess) {
        int rs = 0;
        boolean isValid = false;
        do {
            try {
                String temp = getString(mess);
                if (Acceptable.isValid(temp, Acceptable.INTEGER_VALID)) {
                    rs = Integer.parseInt(temp);
                    isValid = true;
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        } while (!isValid);
        return rs;
    }

    public double getDouble(String mess) {
        double rs = 0;
        boolean isValid = false;
        do {
            try {
                String temp = getString(mess);
                if (Acceptable.isValid(temp, Acceptable.DOUBLE_VALID)) {
                    rs = Double.parseDouble(temp);
                    isValid = true;
                } else {
                    System.out.println("Invalid input! Please enter a valid integer.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer.");
            }
        } while (!isValid);
        return rs;
    }

    public String inputAndLoop(String mess, String pattern) {
        String result = "";
        boolean more = true;
        do {
            result = getString(mess);
            more = !Acceptable.isValid(result, pattern);
            if (more) {
                System.out.println("Data is invalid ! Please re-enter ...");
            }
        } while (more);
        return result.trim();
    }

    public String inputName() {
        String temp = "";
        boolean loopMore = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Input name (min:2 - max:20 characters): ");
            temp = sc.nextLine();
            if (temp.length() < 2 || temp.length() > 20) {
                System.out.println("Name in invalid. Re-enter ...");
            } else {
                loopMore = false;
            }
        } while (loopMore);
        return temp;
    }
    
        public int inputAndLoopInt(String mess, String pattern) {
        int rs;
        boolean more = true;
        do {
            rs = getInt(mess);
            more = !Acceptable.isValid(String.valueOf(rs), pattern);
            if (more) {
                System.out.println("Data is invalid!. Re-enter...");
            }
        } while (more);
        return rs;
    }

}
