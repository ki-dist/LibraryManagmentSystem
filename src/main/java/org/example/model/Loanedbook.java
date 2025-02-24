package org.example.model;
// Generated Jan 27, 2023, 3:03:53 PM by Hibernate Tools 5.2.11.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Loanedbook generated by hbm2java
 */
@Entity
@Table(name="loanedbook"
    ,catalog="librarysys"
)
public class Loanedbook  implements java.io.Serializable {


     private int id;
     private Book book;
     private Users users;
     private Date dateloaned;
     private Date duedate;
     private Date datereturned;
     private String loanstatus;
     private boolean confirmation;
     private String loan;

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public String getLoan() {
        return loan;
    }

    public void setLoan(String loan) {
        this.loan = loan;
    }

    public Loanedbook() {
    }

    public Loanedbook(int id, Book book, Users users, Date dateloaned, Date duedate, Date datereturned, String loanstatus, boolean confirmation,String loan) {
       this.id = id;
       this.book = book;
       this.users = users;
       this.dateloaned = dateloaned;
       this.duedate = duedate;
       this.datereturned = datereturned;
       this.loanstatus = loanstatus;
       this.confirmation =confirmation;
       this.loan=loan;
    }
   
     @Id 

    
    @Column(name="id", unique=true, nullable=false)
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="book_id", nullable=false)
    public Book getBook() {
        return this.book;
    }
    
    public void setBook(Book book) {
        this.book = book;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    public Users getUsers() {
        return this.users;
    }
    
    public void setUsers(Users users) {
        this.users = users;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="dateloaned", nullable=false, length=10)
    public Date getDateloaned() {
        return this.dateloaned;
    }
    
    public void setDateloaned(Date dateloaned) {
        this.dateloaned = dateloaned;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="duedate", nullable=false, length=10)
    public Date getDuedate() {
        return this.duedate;
    }
    
    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    
    @Column(name="datereturned", nullable=false)
    public Date getDatereturned() {
        return this.datereturned;
    }
    
    public void setDatereturned(Date datereturned) {
        this.datereturned = datereturned;
    }

    
    @Column(name="loanstatus", nullable=false, length=64)
    public String getLoanstatus() {
        return this.loanstatus;
    }
    
    public void setLoanstatus(String loanstatus) {
        this.loanstatus = loanstatus;
    }




}


