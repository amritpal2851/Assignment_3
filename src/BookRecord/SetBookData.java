package BookRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

public class SetBookData {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    Logger log = Logger.getLogger(SetBookData.class);

    public void setBookName(BookData bookdata) {
        try {
            System.out.println("Please Enter Book Name ");
            String bookName = bufferedReader.readLine();
            bookdata.setBookName(bookName);
        } catch (IOException e) {
            log.info("Exception in Class SetBookData " + e);
        }
    }

    public void setAuthorName(BookData bookData) {
        try {
            System.out.println("Please Enter Author Name ");
            String authorName = bufferedReader.readLine();
            bookData.setAuthorName(authorName);
        } catch (IOException e) {
            log.info("Exception in Class SetBookData " + e);
        }
    }

    public void seNumberOfCopies(BookData bookData) {
        try {
            System.out.println("Please Enter Number of Copies, only integers allowed ");
            String numberOfCopies = bufferedReader.readLine();
            bookData.setNumberOfCopies(numberOfCopies);
        } catch (IOException e) {
            log.info("Exception in Class SetBookData " + e);
        }
    }

    public void setBookPublishingDate(BookData bookData) {
        try {
            System.out.println("Please Enter Publishing Date of book in yyyy-MM-dd format only ");
            String publishingDate = bufferedReader.readLine();
            bookData.setDateOfPublishing(publishingDate);
        } catch (IOException e) {
            log.info("Exception in Class SetBookData " + e);
        }
    }

    public void setBookData(BookData bookData) {
        setBookName(bookData);
        setAuthorName(bookData);
        seNumberOfCopies(bookData);
        setBookPublishingDate(bookData);
    }
}
