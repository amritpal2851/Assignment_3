package studentRecord;
import features.AddStudentBookDetails;
//import features.StudentDB;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentDataValidation {
    SetStudentData setStudentData=new SetStudentData();
    AddStudentBookDetails addStudentBookDetails=new AddStudentBookDetails();
    public void validateStudentName(StudentData studentData){
        while(true){
            Pattern pattern=Pattern.compile("^[a-zA-Z\\s]*$");
            Matcher matcher=pattern.matcher(studentData.getStudentName());
            if(!matcher.find()){
                System.out.println("You have enter invalid name, only characters allowed");
                setStudentData.setStudentName(studentData);
            }
            else{
                break;
            }
        }
    }
    public void validateClassName(StudentData studentData){
        while(true){
            if(studentData.getClassName()==null){
                System.out.println("Class Name cannot be null, please provide student class name ");
                setStudentData.setClassName(studentData);
            }else{
                break;
            }
        }
    }
    public void validateDateOfBirth(StudentData studentData){
        while(true){
            Pattern pattern=Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
            Matcher matcher= pattern.matcher(studentData.getDateOfBirth());
            if(!matcher.find()){
                System.out.println("Please Enter the DOB in yyyy-MM-dd format only ");
                setStudentData.setDateOfBirth(studentData);
            }else{
                break;
            }
        }
    }
    public void validateStudentData(StudentData studentData){
        validateStudentName(studentData);
        validateClassName(studentData);
        validateDateOfBirth(studentData);
        addStudentBookDetails.addStudentDataToDB(studentData);

    }
}
