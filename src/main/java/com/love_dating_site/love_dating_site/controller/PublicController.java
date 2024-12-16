package com.love_dating_site.love_dating_site.controller;

import com.love_dating_site.love_dating_site.dto.QrCodeResponse;
import com.love_dating_site.love_dating_site.entity.UserEntity;
import com.love_dating_site.love_dating_site.exception.DataNotFoundException;
import com.love_dating_site.love_dating_site.service.DashBoardService;
import com.love_dating_site.love_dating_site.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@CrossOrigin("*")
public class PublicController {

    private final UserService userService;
    private final DashBoardService dashBoardService;

    public PublicController(UserService userService,
                            DashBoardService dashBoardService) {
        this.userService = userService;
        this.dashBoardService = dashBoardService;
    }

    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("Hello from love dating site", HttpStatus.OK);
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<UserEntity> login(@RequestParam String username,
                                            @RequestParam String password) {
        UserEntity user = userService.authenticateUser(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/get-qr-code", method = RequestMethod.GET)
    public ResponseEntity<QrCodeResponse> getRechargeQrCode() {
        try {
            QrCodeResponse response = dashBoardService.getRechargeQrCode();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
