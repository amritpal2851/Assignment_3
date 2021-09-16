package main;

import BookRecord.BookData;
import BookRecord.BookRecordValidation;
import BookRecord.SetBookData;
import borrowbookrecord.BorrowBookSystem;
import features.AddStudentBookDetails;
import features.BorrowBookFeatures;
import features.ReturnBook;
import studentRecord.SetStudentData;
import studentRecord.StudentData;
import studentRecord.StudentDataValidation;

import java.util.Scanner;

public class LibraryManagementSystem {
    static Scanner scanner= new Scanner(System.in);
    static BorrowBookFeatures borrowBookFeatures =new BorrowBookFeatures();
    static AddStudentBookDetails addStudentBookDetails =new AddStudentBookDetails();
    public static void main(String[] args) {
        while(true){
            System.out.println("1-> Add Book Record");
            System.out.println("2-> Add Student Details");
            System.out.println("3-> Issue Books to Student");
            System.out.println("4-> Issued Book Record ");
            System.out.println("5-> Return Books");
            System.out.println("6-> Record of Students who didn't return books after due date");
            System.out.println("7-> Books left with minimum number of Copies");
            System.out.println("8-> Exit");
            int option=scanner.nextInt();
            switch (option){
                case 1:
                    BookData bookData=new BookData();
                    SetBookData setBookData=new SetBookData();
                    BookRecordValidation bookRecordValidation=new BookRecordValidation();
                    setBookData.setBookData(bookData);
                    bookRecordValidation.validateBookData(bookData,setBookData);
                    continue;
                case 2:
                    StudentData studentData=new StudentData();
                    SetStudentData setStudentData=new SetStudentData();
                    StudentDataValidation studentDataValidation=new StudentDataValidation();
                    setStudentData.studentData(studentData);
                    studentDataValidation.validateStudentData(studentData);
                    continue;
                case 3:
                    BorrowBookSystem borrowBookSystem=new BorrowBookSystem();
                    borrowBookSystem.issueBook();
                    continue;
                case 4:
                    System.out.println("Book Name     |  Student Name   |  Class Name   | Return Date");
                    borrowBookFeatures.borrowedBookRecord();
                    continue;
                case 5:
                    ReturnBook returnBook=new ReturnBook();
                    returnBook.returnBook();
                    continue;
                case 6:
                    System.out.println("Book Name     |  Student Name   |  Class Name   | Return Date");
                    borrowBookFeatures.studentsExceed15Days();
                    continue;
                case 7:
                    addStudentBookDetails.bookLeftWithMinimumCopies();
                    System.out.println("Book ID   |   Book Name  |  Author Name  | Number of Copies");
                    continue;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("You have choose wrong value, please choose from below options ");
                    continue;

            }
        }
    }
}
