package tools;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hieuh
 */
public interface Acceptable {
    public final String STUDENT_ID = "^[CcDdHhSsQq][Ee]\\d{6}$";
    public final String CAMPUS_CODE = "^[CcDdHhSsQq][Ee]";
    public final String NAME_VALID = "^.{2,20}$";
    public final String DOUBLE_VALID = "-?\\d+(\\.\\d+)?";
    public final String INTEGER_VALID = "\\d+" ;
    public final String PHONE_VALID = "^(081|082|083|084|085|088|091|094|032|033|034|035|036|037|038|039|098|097|096|086|070|079|077|076|078|089|090|093|092|052|056|058|099|059|087)\\d{7}$"; 
    public final String VIETTEL_VALID = "^(032|033|034|035|036|037|038|039|098|097|096|086)\\d{7}$" ;
    public final String VNPT_VALID = "^(081|082|083|084|085|088|091|094)\\d{7}$";
    public final String EMAIL_VALID = "^[\\w-\\.]+@[\\w-]+\\.com$" ;
    public final String CODE_VALID = "^[1-9]|1[0-3]";
    public final String YN_VALID = "^[YyNn]$";
    
    public static boolean isValid(String data, String pattern){
        return data.matches(pattern);
    }
}
