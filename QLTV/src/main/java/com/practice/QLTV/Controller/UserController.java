package com.practice.QLTV.Controller;

import com.practice.QLTV.DTO.Request.UserRequest;
import com.practice.QLTV.DTO.Response.APIResponse;
import com.practice.QLTV.DTO.Response.UserResponse;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Repository.projection.UserView;
import com.practice.QLTV.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    APIResponse<User>  createUser(@RequestBody @Valid UserRequest request)
    {
        APIResponse<User> apiResponse = new APIResponse<>();
            apiResponse.setData(userService.createUser(request));
        return apiResponse;
    }
    @GetMapping
    List<UserResponse> getallUser(@RequestParam(defaultValue = "0") int page,
                            @RequestParam(defaultValue = "5") int size){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username : {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userService.getalluser(page, size);
    }
    @GetMapping("/{id}")
    User getuser (@PathVariable int id){
        return userService.getuser(id);
    }
    @GetMapping("/myinfo")
    User getmyinfo(){
        return userService.myinfo();
    }
    @GetMapping("/count")
    List<UserView> countuserborrow(@RequestParam("startdate")LocalDate startdate,
                                   @RequestParam("enddate")LocalDate enddate){
        return userService.countuserborrow(startdate,enddate);
    }

    @PutMapping("/{id}")
    UserResponse updateuser(@PathVariable int id, @RequestBody @Valid UserRequest request){
        return userService.updateuser(id,request);
    }
    @DeleteMapping("/{id}")
    String deleteuser(@PathVariable int id){
        return userService.deleteuser(id);
    }
    @GetMapping("/exportExcel")
    public ResponseEntity<byte[]> exportToExcel(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) throws IOException {

        // Gọi phương thức getAllUser để lấy danh sách người dùng theo phân trang
        List<UserResponse> userexport = userService.getalluser(page, size);

        // Chuyển đổi danh sách UserResponse thành tệp Excel
        ByteArrayInputStream in = userService.usersToExcel(userexport);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=users.xlsx");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(in.readAllBytes());
    }
    @PostMapping("/importExcel")
    public ResponseEntity<String> importUsersFromExcel(@RequestPart("file") MultipartFile file) {
        try {
            // Kiểm tra nếu file không đúng định dạng
            if (!file.getOriginalFilename().endsWith(".xlsx")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload an Excel file!");
            }

            // Đọc dữ liệu từ file Excel
            List<UserResponse> users = UserService.importUsersFromExcel(file.getInputStream());

            // Lưu dữ liệu vào database hoặc xử lý thêm
            userService.saveAll(users);

            return ResponseEntity.ok("File uploaded and data imported successfully!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while importing data: " + e.getMessage());
        }
    }

}
