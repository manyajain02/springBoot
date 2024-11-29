package com.springboot.SpringSecurity.securityApp.controllers;

import com.springboot.SpringSecurity.securityApp.dto.LoginDto;
import com.springboot.SpringSecurity.securityApp.dto.SignUpDto;
import com.springboot.SpringSecurity.securityApp.dto.UserDto;
import com.springboot.SpringSecurity.securityApp.services.AuthService;
import com.springboot.SpringSecurity.securityApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto signUpDto) {
        UserDto userDto = userService.signUp(signUpDto);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletResponse response) {
       String token = authService.login(loginDto);
        Cookie cookie = new Cookie("token", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
       return ResponseEntity.ok(token);
    }
//
//    @PostMapping("/login")
//    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto, HttpServletRequest request,
//                                        HttpServletResponse response) {
//        LoginResponseDto loginResponseDto = authService.login(loginDto);
//
//        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
//        cookie.setHttpOnly(true);
//        cookie.setSecure("production".equals(deployEnv));
//        response.addCookie(cookie);
//
//        return ResponseEntity.ok(loginResponseDto);
//    }

//    @PostMapping("/refresh")
//    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request) {
//        String refreshToken = Arrays.stream(request.getCookies()).
//                filter(cookie -> "refreshToken".equals(cookie.getName()))
//                .findFirst()
//                .map(Cookie::getValue)
//                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the Cookies"));
//        LoginResponseDto loginResponseDto = authService.refreshToken(refreshToken);

//        return ResponseEntity.ok(loginResponseDto);
//    }
}
