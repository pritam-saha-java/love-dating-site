package com.love_dating_site.love_dating_site.controller;

import com.love_dating_site.love_dating_site.entity.QrCodesEntity;
import com.love_dating_site.love_dating_site.entity.UserEntity;
import com.love_dating_site.love_dating_site.dto.UserOperationsRequest;
import com.love_dating_site.love_dating_site.service.AdminService;
import com.love_dating_site.love_dating_site.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

    private final UserService userService;
    private final AdminService adminService;

    public AdminController(UserService userService,
                           AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> login(@RequestParam String username,
                                                     @RequestParam String password) {
        Map<String, String> user = userService.adminLogin(username, password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/create-user", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserEntity> createUser(@ModelAttribute UserOperationsRequest request) {
        UserEntity user = userService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/update-user/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<UserEntity> updateUser(@PathVariable("userId") Long userId,
                                                 @RequestBody UserOperationsRequest request) {
        UserEntity user = userService.updateUser(userId, request);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete-user/{username}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/get-user/{username}", method = RequestMethod.GET)
    public ResponseEntity<UserEntity> getUserById(@PathVariable String username) {
        UserEntity user = userService.getUserById(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(path = "/get-all-users", method = RequestMethod.GET)
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(path = "/get-all-qr-codes", method = RequestMethod.GET)
    public ResponseEntity<List<QrCodesEntity>> getAllQrCodes() {
        List<QrCodesEntity> qrCodes = adminService.getAllQrCodes();
        return new ResponseEntity<>(qrCodes, HttpStatus.OK);
    }

    @RequestMapping(path = "/delete-qr-code/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteQrCode(@PathVariable Long id) {
        adminService.deleteQrCode(id);
        return new ResponseEntity<>("QR Code deleted successfully", HttpStatus.NO_CONTENT);
    }

    @RequestMapping(path = "/save-qr-code", method = RequestMethod.POST)
    public ResponseEntity<QrCodesEntity> saveQrCode(@RequestBody QrCodesEntity qrCode) {
        QrCodesEntity savedQrCode = adminService.saveQrCode(qrCode);
        return new ResponseEntity<>(savedQrCode, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/set-active-qr-code/{id}", method = RequestMethod.PUT)
    public ResponseEntity<QrCodesEntity> setActiveQrCode(@PathVariable Long id) {
        QrCodesEntity activeQrCode = adminService.setActiveQrCode(id);
        return new ResponseEntity<>(activeQrCode, HttpStatus.OK);
    }
}
