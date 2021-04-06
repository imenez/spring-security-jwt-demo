package com.imenez.springsecurityjwt.controller;


import com.imenez.springsecurityjwt.model.AuthenticationRequest;
import com.imenez.springsecurityjwt.model.AuthenticationResponse;
import com.imenez.springsecurityjwt.service.MyUserDetailsService;
import com.imenez.springsecurityjwt.util.JwtUtil;
import com.imenez.springsecurityjwt.util.MynetFetchStocksUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MynetFetchStocksUtil sahibindenModelMarkaUtil;




    @RequestMapping("/hellow")
    public String hello(){

        return "hellow";
    }


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new Exception("username or password invalid!", e);
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUserName());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));

    }


    @GetMapping("/getStocks")
    public List<String> getStocks(){

        return sahibindenModelMarkaUtil.getStocks();
    }

}
