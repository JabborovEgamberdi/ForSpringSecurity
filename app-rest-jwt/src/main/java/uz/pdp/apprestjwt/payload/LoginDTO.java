package uz.pdp.apprestjwt.payload;

import lombok.Data;

@Data
public class LoginDTO {

    private String username;

    private String password;
}

