package org.example.service;

import org.example.Dto.*;

import org.example.cassiomolin.user.domain.User;
import org.example.dao.BookDao;
import org.example.dao.LoanedbookDao;

import org.example.dao.UserDao;
import org.example.dao.UsersDao;

import org.example.model.Book;
import org.example.model.Loanedbook;



import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.time.LocalDate;

//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.TimeUnit;


@Stateless
public class AdminService {
    @EJB
    LoanedbookDao loanedBookDao;
    @EJB
    UsersDao userDao1;
  @EJB
    BookDao bookDao;
      @EJB
      UserDao userDao;

/*public Book saveBook(Book book){

}*/
    public List<LoanDto> ViewLoanData(int start, int max) {
        List<LoanDto> loanDtos = new ArrayList<>();
        List<Loanedbook> loanedBooks = loanedBookDao.listAll(start, max);

        loanedBooks.forEach(loanedBook -> {

            LoanDto loanDto = new LoanDto();
            loanDto.setBookTitle(loanedBook.getBook().getTitle());
            loanDto.setBookCatagory(loanedBook.getBook().getCatagory().getCatagoryName());
            loanDto.setDateLoaned(loanedBook.getDateloaned());
            loanDto.setBookStatus(loanedBook.getLoanstatus());
            loanDto.setDateReturned(loanedBook.getDatereturned());
            loanDto.setDueDate(loanedBook.getDuedate());
            loanDtos.add(loanDto);
        });
        return loanDtos;
    }
   public RegistrationResponseDto RegisteringBook(BookRegistrationDto bookRegistrationDto ){
    Book book =new Book();
    book.setTitle(bookRegistrationDto.getTitle());
    book.setCatagory(bookRegistrationDto.getCatagory());
       bookDao.create(book);
       return new RegistrationResponseDto(true,"registered");
   }
    public List<UserLoanedDto> AllBoarowedList(int start, int max) {
        List<UserLoanedDto> userLoanedDtos = new ArrayList<>();
        List<Loanedbook> loanedBooks = loanedBookDao.listAll(start, max);

        loanedBooks.forEach(loanedBook -> {

            UserLoanedDto userLoanedDto = new UserLoanedDto();
            if (loanedBook.getDateloaned().equals(LocalDate.now())) {
                userLoanedDto.setUser(Users(start,max));
                userLoanedDto.setDateLoaned(loanedBook.getDateloaned());
                userLoanedDto.setBookTitle(loanedBook.getBook().getTitle());
                userLoanedDto.setBookCatagory(loanedBook.getBook().getCatagory().getCatagoryName());
                userLoanedDto.setDueDate(loanedBook.getDuedate());
            }

            userLoanedDtos.add(userLoanedDto);

        });

        return userLoanedDtos;
    }
    public List<userDto> Users(int start, int max) {
        List<userDto> userDtos = new ArrayList<>();
        List<User> users =userDao.listAll(start, max);
        users.forEach(user ->{
            userDto userDto =new userDto();
            userDto.setUserName(user.getUsername());
            userDtos.add(userDto);
        } );
        return userDtos;

    }


public  Loanedbook Confirmation(ConfirmationDto confirmationDto){
   Loanedbook loanedbook =new Loanedbook();
    if(loanedbook.getLoanstatus().equals("available") && loanedbook.getLoan().equals("0")){
        loanedbook.setConfirmation(true);
       confirmationDto.setConfirmation(true);
    }else {
        loanedbook.setConfirmation(false);
        //confirmationDto.setConfirmation(false);
    }
    loanedBookDao.create(loanedbook);
    return loanedbook;
}
    public Loanedbook Overdue() {

        Loanedbook loanedBook1 = new Loanedbook();
      long timeDiff =Math.abs(loanedBook1.getDatereturned().getTime()-loanedBook1.getDuedate().getTime());
      long dayDiff =TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);

        if (loanedBook1.getDatereturned().after(loanedBook1.getDuedate())) {
            if(dayDiff==1){
                loanedBook1.setLoan("2birr");
            }else if(dayDiff>=2)
            loanedBook1.setLoan("5birr");
        }else if(loanedBook1.getDatereturned().equals(loanedBook1.getDuedate())){
            loanedBook1.setLoanstatus("available");
            loanedBook1.setLoan("0");
        }

        return loanedBook1;
    }

}
