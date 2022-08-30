package uz.pdp.apprestjwt.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @GetMapping
    public HttpEntity<?> getReports(){
        return new ResponseEntity<>("Reports Sent", HttpStatus.OK);
    }

}
