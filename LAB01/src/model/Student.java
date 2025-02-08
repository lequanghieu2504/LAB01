package model;
import java.io.Serializable;
import tools.Acceptable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hieuh
 */
public class Student implements Serializable{
    private String id;
    private String name;
    private String phone;
    private String email;
    private String mountainCode;
    private double tuitionFee;

    public Student() {
    }

    public Student(String id, String name, String phone, String email, String mountainCode, double tuitionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.mountainCode = mountainCode;
        this.tuitionFee = tuitionFee;
    }
    
     public Student(String id, String name, String phone, String mountainCode, double tuitionFee) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.mountainCode = mountainCode;
        this.tuitionFee = calTuitionFee(getPhone());
    }

    public String getId() {
        return id.substring(0, 2).toUpperCase() + id.substring(2);
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        String[] temp = name.split(" ");
        String result = "";
        for (String string : temp) {
            result = result + string.substring(0,1).toUpperCase() + string.substring(1)+ " ";
        }
        return result.trim();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
            this.phone = phone;
            setTutionFee(calTuitionFee(phone));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public String getMountainCode() {
        if (Integer.parseInt(mountainCode)<=9){
            return "MT0" + mountainCode;
        }
        else {
            return "MT" + mountainCode;
        }
    }

    public void setMountainCode(String mountainCode) {
        this.mountainCode = mountainCode;
    }

    public double getTuitionFee() {
        return tuitionFee;
    } 

    public void setTutionFee(double tuitionFee) {
        if (getPhone().matches(Acceptable.VNPT_VALID)||getPhone().matches(Acceptable.VIETTEL_VALID)){
            this.tuitionFee = 6000000*0.65;
        }
        else{
            this.tuitionFee=6000000;
        }
    }
    
    public double calTuitionFee(String phone){
         if (phone.matches(Acceptable.VNPT_VALID)||phone.matches(Acceptable.VIETTEL_VALID)){
            return 6000000*0.65;
        }
        else{
            return 6000000;
        }
    }

    @Override
    public String toString() {
        String fee = String.format("%,.0f", tuitionFee);
        return String.format("| %-12s | %-15s | %-15s | %-25s | %-15s |",
                truncateOrPad(getId(), 12),
                truncateOrPad(getName(), 15),
                truncateOrPad(getPhone(), 15),
                truncateOrPad(getMountainCode(), 25),
                truncateOrPad(fee, 15));
    }
    // Hàm hỗ trợ để cắt chuỗi hoặc điền thêm khoảng trắng
    

    private String truncateOrPad(String value, int length) {
        if (value == null) {
            value = ""; // Xử lý null
        }
        if (value.length() > length) {
            return value.substring(0, length); // Cắt chuỗi
        }
        return String.format("%-" + length + "s", value); // Điền thêm khoảng trắng
    }


}
