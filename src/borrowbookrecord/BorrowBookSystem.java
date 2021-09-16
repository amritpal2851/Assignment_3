package borrowbookrecord;
import features.BorrowBookFeatures;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import features.EstablishConnection;
import org.apache.log4j.Logger;

public class BorrowBookSystem {
    String id;
    String date;
    Logger logger = Logger.getLogger(BorrowBookSystem.class);
    BorrowBookFeatures borrowBookFeatures = new BorrowBookFeatures();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public void setBookIssueDate() {
        try {
            while (true) {
                System.out.println("Please Enter issue Date ");
                date = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
                Matcher matcher = pattern.matcher(date);
                if (!matcher.find()) {
                    System.out.println("Please Enter the issue date in yyyy-MM-dd format only ");
                    continue;
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            logger.info(e);
        }
    }
    public boolean isStudentExist(String id){
        int id1=Integer.parseInt(id);
        boolean check=false;
        int count=0;
        Connection connection= EstablishConnection.setConnection();
        try{
            PreparedStatement preparedStatement=connection.prepareStatement("select 1 from student where id=?");
            preparedStatement.setInt(1,id1);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                count=resultSet.getInt(1);
            }
            System.out.println(id1);
            if(count!=0 ){
                check=true;
            }
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            logger.info(e);
        }return check;
    }

    public void issueBook() {
        System.out.println("Please Enter student ID ");
        while(true){
            try {
                id = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(id);
                if(!matcher.find()){
                    System.out.println("ID provided is not correct, please provide integer only");
                    continue;
                }
                else{
                    if (isStudentExist(id)) {
                        System.out.println("Please enter book Id ");
                        String BookId = bufferedReader.readLine();
                        if(borrowBookFeatures.isBookAvailable(BookId)) {
                            setBookIssueDate();
                            borrowBookFeatures.getBookDetailsFromDB(BookId);
                            borrowBookFeatures.setBorrowBookRecord(id,date);
                            break;
                        } else{
                            System.out.println("Book is not available in library, please come after some days ");
                            break;
                        }
                    } else {
                        System.out.println("Student Does Not Exist ");
                        break;
                    }
                }
            } catch (Exception e) {
                logger.info(e);
                e.printStackTrace();
            }
        }
    }
}
