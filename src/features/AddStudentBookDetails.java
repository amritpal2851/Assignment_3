package features;

import BookRecord.BookData;
import org.apache.log4j.Logger;
import studentRecord.StudentData;

import java.sql.*;

public class AddStudentBookDetails {
    Logger log = Logger.getLogger(AddStudentBookDetails.class);
    Connection connection = EstablishConnection.setConnection();

    public void AddLibraryRecordToDB(BookData bookData) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into book_record values(DEFAULT,?,?,?,?)");
            preparedStatement.setString(1, bookData.getBookName());
            preparedStatement.setString(2, bookData.getAuthorName());
            preparedStatement.setInt(3, bookData.getNumberOfBooks());
            preparedStatement.setString(4, bookData.getDateOfPublishing());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("Exception in AddStudentBookDetails " + e);
        }
    }
    public void addStudentDataToDB(StudentData studentData) {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/library", "postgres", "admin");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into student values(DEFAULT,?,?,?)");
            preparedStatement.setString(1, studentData.getStudentName());
            preparedStatement.setString(2, studentData.getClassName());
            preparedStatement.setString(3, studentData.getDateOfBirth());
            int check = preparedStatement.executeUpdate();
            if (check == 1) {
                System.out.println("Data Saved Successfully ");
            } else {
                System.out.println("Data not saved successfully ");
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            log.info(e);
            e.printStackTrace();
        }
    }

    public void bookLeftWithMinimumCopies() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Select id,book_name,author_name, number_of_copies from book_record where number_of_copies<5");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.print(resultSet.getInt(1) + "     ");
                System.out.print(resultSet.getString(2) + "  ");
                System.out.print(resultSet.getString(3) + "  ");
                System.out.println(resultSet.getInt(4) + "   ");
            }
        } catch (Exception e) {
            log.info(e);
        }
    }
}


