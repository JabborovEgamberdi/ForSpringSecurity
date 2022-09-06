package uz.pdp.apprestjwt.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public HttpEntity<?> loginToSystem(@RequestBody LoginDTO loginDTO) {
        UserDetails userDetails = myAuthService.loadUserByUsername(loginDTO.getUsername());
        boolean matches = passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword());
//        boolean existUser = userDetails.getPassword().equals(loginDTO.getPassword());
        if (matches) {
            String token = jwtProvider.generateToken(loginDTO.getUsername());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Login yoki parol xato!");
    }

}