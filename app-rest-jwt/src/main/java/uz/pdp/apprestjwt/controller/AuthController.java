package uz.pdp.apprestjwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.apprestjwt.payload.LoginDTO;
import uz.pdp.apprestjwt.security.JwtProvider;
import uz.pdp.apprestjwt.services.MyAuthService;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final MyAuthService myAuthService;

    private final JwtProvider jwtProvider;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDTO.getPassword());
        boolean existUser = userDetails.getPassword().equals(loginDTO.getPassword());
        if (existUser) {
            String token = jwtProvider.generateToken(loginDTO.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Login yoki parol xato!");
    }


}
