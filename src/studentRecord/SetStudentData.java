package studentRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import org.apache.log4j.Logger;

public class SetStudentData {
    Scanner scanner=new Scanner(System.in);
    Logger log=Logger.getLogger(SetStudentData.class);
    BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(System.in));
    public void setStudentName(StudentData studentData){
        try{
            System.out.println("Please Enter Student Name ");
            String studentName=bufferedReader.readLine();
            studentData.setStudentName(studentName);;
        }catch (IOException e){
            log.info("Exception in Class SetStudentData " + e);
        }
    }
    public void setClassName(StudentData studentData){
        try{
            System.out.println("Please enter Student class name ");
            String className= bufferedReader.readLine();
            studentData.setClassName(className);
        }catch (IOException e){
            log.info("Exception in Class SetStudentData " + e);
        }
    }
    public void setDateOfBirth(StudentData studentData){
        try{
            System.out.println("Please enter the date of birth of student in yyyy-MM-dd format only ");
            String studentDOB= bufferedReader.readLine();
            studentData.setDateOfBirth(studentDOB);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void studentData(StudentData studentData){
        setStudentName(studentData);
        setClassName(studentData);
        setDateOfBirth(studentData);
    }
}
