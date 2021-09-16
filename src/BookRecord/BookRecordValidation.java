package BookRecord;
import features.AddStudentBookDetails;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookRecordValidation {
    AddStudentBookDetails addStudentBookDetails =new AddStudentBookDetails();
    public void validateBookName(BookData bookData, SetBookData setBookData){
        while(true){
            Pattern pattern= Pattern.compile("^[a-zA-Z\\s]*$");
            Matcher matcher= pattern.matcher(bookData.getBookName());
            if(!matcher.find()){
                System.out.println("You have enter wrong book name, only characters are allowed ");
                setBookData.setBookName(bookData);
            }
            else{
                break;
            }
        }

    }
    public void validateAuthorName(BookData bookData, SetBookData setBookData){
        while(true){
            Pattern pattern=Pattern.compile("^[a-zA-Z\\s]*$");
            Matcher matcher=pattern.matcher(bookData.getAuthorName());
            if(!matcher.find()){
                System.out.println("You have enter wrong author name, only characters are allowed ");
                setBookData.setAuthorName(bookData);
            }else{
                break;
            }
        }

    }
    public void validateNumberOfCopies(BookData bookData,SetBookData setBookData){
        while(true){
            Pattern pattern=Pattern.compile("^[0-9]+$", Pattern.CASE_INSENSITIVE);
            Matcher matcher=pattern.matcher(bookData.getNumberOfCopies());
            if(!matcher.find()){
                System.out.println("You have Number of copies in invalid format, only number are allowed ");
                setBookData.seNumberOfCopies(bookData);
            }else{
                bookData.setNumberOfBooks(Integer.parseInt(bookData.getNumberOfCopies()));
                break;
            }
        }
    }
    public void validatePublishingDate(BookData bookData,SetBookData setBookData){
        while(true){

            Pattern pattern=Pattern.compile("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$");
            Matcher matcher= pattern.matcher(bookData.getDateOfPublishing());
            if(!matcher.find()){
                System.out.println("Please Enter Date of publishing in right in yyyy-MM-dd format only ");
                setBookData.setBookPublishingDate(bookData);
            }else{
                break;
            }
        }
    }
    public void validateBookData(BookData bookData,SetBookData setBookData){
        validateBookName(bookData,setBookData);
        validateAuthorName(bookData,setBookData);
        validateNumberOfCopies(bookData,setBookData);
        validatePublishingDate(bookData,setBookData);
        addStudentBookDetails.AddLibraryRecordToDB(bookData);
        System.out.println("Book Record Saved Successfully ");
    }
}
