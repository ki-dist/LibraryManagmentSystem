package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Catagory;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRegistrationDto {
    private String title;
   // private String Catagory;
    private Catagory catagory;
}
