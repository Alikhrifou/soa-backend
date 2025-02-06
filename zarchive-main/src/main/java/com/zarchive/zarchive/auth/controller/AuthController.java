package com.zarchive.zarchive.auth.controller;


import com.zarchive.zarchive.auth.dto.AuthenticationRequest;
import com.zarchive.zarchive.auth.entity.AppUser;
import com.zarchive.zarchive.auth.service.AppUserDetailsService;
import com.zarchive.zarchive.auth.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AppUserDetailsService appUserDetailsService;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/allUsers")
    public String welcome(){
        return appUserDetailsService.getAllUserss().toString();
    }

    @PostMapping("/adduser")
    public String addNewUser(@RequestBody AppUser appUser){
        return appUserDetailsService.addUser(appUser);
    }
    @GetMapping("/protected")
    public String userProfile(){
        return "Welcome to the protected link";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthenticationRequest authenticationRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(authenticationRequest.getUsername());
        } else{
            throw new UsernameNotFoundException("Invalide request");
        }
    }


}
