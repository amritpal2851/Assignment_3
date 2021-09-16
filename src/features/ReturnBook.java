package features;
import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReturnBook {
    Logger logger = Logger.getLogger(ReturnBook.class);
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    BorrowBookFeatures borrowBookFeatures=new BorrowBookFeatures();
    String bookName;
    String tempId;
    int id;

    public void setId() {
        while (true) {
            try {
                System.out.println("Please Enter Student Id");
                tempId = bufferedReader.readLine();
                System.out.println("Please enter book name ");
                bookName = bufferedReader.readLine();
                Pattern pattern = Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(tempId);
                if (matcher.find()) {
                    this.id = Integer.parseInt(tempId);
                    break;
                } else {
                    System.out.println("ID in invalid format , please enter again ");
                    continue;
                }
            } catch (Exception e) {
                logger.info(e);
            }
        }
    }

    public void returnBook() {
        try {
            setId();
            borrowBookFeatures.returnBookToLibrary(id,bookName);
        } catch (Exception e) {
            logger.info(e);
        }
    }
}
