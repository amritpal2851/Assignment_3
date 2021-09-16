package features;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BorrowBookFeatures {
    String book_name;
    String author_name;
    int numberOfCopies;
    Logger logger=Logger.getLogger(BorrowBookFeatures.class);

    public boolean isBookAvailable(String id) {
        int id1=Integer.parseInt(id);
        int columnId = 0;
        int numberOfBookAvailable = 0;
        boolean check = false;
        Connection connection=EstablishConnection.setConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from book_record where id=?");
            preparedStatement.setInt(1, id1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                columnId = resultSet.getInt(1);
                numberOfBookAvailable = resultSet.getInt(4);
            }
            if (columnId != 0 && numberOfBookAvailable != 0) {
                check = true;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            logger.info(e);
        }
        return check;
    }

    public void getBookDetailsFromDB(String id) {
        Connection connection=EstablishConnection.setConnection();
        try {
            int id1=Integer.parseInt(id);
            PreparedStatement preparedStatement = connection.prepareStatement("select book_name,number_of_copies from book_record where id=?");
            preparedStatement.setInt(1, id1);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                book_name = resultSet.getString(1);
                numberOfCopies = resultSet.getInt(2);
            }
            PreparedStatement preparedStatement1 = connection.prepareStatement("update book_record set number_of_copies=" + (numberOfCopies-1) +" where id=?");
            preparedStatement1.setInt(1, id1);
            preparedStatement1.executeUpdate();
            preparedStatement.close();
            preparedStatement1.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("Exception in AddStudentBookDetails " + e);
        }
    }

    public void setBorrowBookRecord(String id,String date){
        Connection connection=EstablishConnection.setConnection();
        int id1=Integer.parseInt(id);
        try{
            PreparedStatement preparedStatement= connection.prepareStatement("insert into borrow_book values(DEFAULT,?,?,?,Null)");
            preparedStatement.setString(1,book_name);
            preparedStatement.setInt(2,id1);
            preparedStatement.setDate(3,java.sql.Date.valueOf(date));
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1=connection.prepareStatement("update borrow_book set return_date=issue_date+interval '15 days' where student_id=?");
            preparedStatement1.setInt(1,id1);
            preparedStatement1.executeUpdate();
            preparedStatement.close();
            preparedStatement1.close();
            connection.close();
        }catch (Exception e){
            logger.info(e);
        }

    }
    public void studentsExceed15Days(){
        Connection connection=EstablishConnection.setConnection();
        try{
            PreparedStatement preparedStatement= connection.prepareStatement("select book_name,student_name,student_class,return_date from borrow_book b inner join student s on b.student_id=s.id where current_date-return_date>10;");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.print(resultSet.getString(1)+"       ");
                System.out.print(resultSet.getString(2)+"       ");
                System.out.print(resultSet.getString(3)+"       ");
                System.out.println(resultSet.getString(4)+"       ");
            }
            preparedStatement.close();
            connection.close();
        }catch(Exception e){
            logger.info(e);
        }
    }
    public void borrowedBookRecord(){
        Connection connection=EstablishConnection.setConnection();
        try{
            PreparedStatement preparedStatement= connection.prepareStatement("select book_name,student_name,student_class,return_date from borrow_book b inner join student s on b.student_id=s.id");
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.print(resultSet.getString(1)+"       ");
                System.out.print(resultSet.getString(2)+"       ");
                System.out.print(resultSet.getString(3)+"        ");
                System.out.println(resultSet.getString(4)+"     ");
            }
            preparedStatement.close();
            connection.close();
        }catch (Exception e){
            logger.info(e);
        }
    }
    public void returnBookToLibrary(int id,String bookName){
        Connection connection=EstablishConnection.setConnection();
        try{
            int count=0;
            PreparedStatement preparedStatement=connection.prepareStatement("select 1 from borrow_book where student_id=? and book_name=?");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,bookName);
            ResultSet resultSet=preparedStatement.executeQuery();
            while(resultSet.next())
            {
                count=resultSet.getInt(1);
            }
            if(count!=0){
                PreparedStatement preparedStatement1=connection.prepareStatement("delete from borrow_book where student_id=? and book_name=?");
                preparedStatement1.setInt(1,id);
                preparedStatement1.setString(2,bookName);
                preparedStatement1.executeUpdate();
                preparedStatement.close();
                preparedStatement1.close();
                connection.close();
                System.out.println("Book Returned Successfully ");
            }else{
                System.out.println("Sorry! No Data Found ");
            }
        }catch(Exception e){
            logger.info(e);
        }

    }

}
