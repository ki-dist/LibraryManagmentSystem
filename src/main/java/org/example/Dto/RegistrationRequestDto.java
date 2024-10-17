package org.example.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cassiomolin.security.domain.Authority;
//import org.example.cassiomolin.security.domain.Authority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequestDto {

        private String username;
        private String password;
        private String confirmPassword;
        private String gender;
       private String authorities;


}
