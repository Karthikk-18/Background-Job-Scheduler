package com.example.Never.controller;

import com.example.Never.Repository.UserRepository;
import com.example.Never.dto.*;
import com.example.Never.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam AuthRequest authRequest){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getUsername(),
                            authRequest.getPassword()
                    )
            );

            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

            final String jwt = jwtUtil.generateToken(userDetails.getUsername());

            System.out.println("Login Successful for user " + authRequest.getUsername());
            return ResponseEntity.ok(new AuthResponse(jwt,authRequest.getUsername()));
        } catch (BadCredentialsException e){
            System.err.println("Login failed: Invalid Credentials ");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error","Invalid Username or Password"));
        } catch (Exception e){
            System.err.println("Login error: "+e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error","Login failed: "+e.getMessage()));
        }
    }

}
