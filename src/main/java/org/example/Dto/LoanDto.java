package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Book;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private String bookTitle;
    private String bookCatagory;
    private Date dateLoaned;
    private Date dueDate;
    private Date dateReturned;
    private String bookStatus;
}
