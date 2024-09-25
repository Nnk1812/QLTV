package com.practice.QLTV.Service;

import com.practice.QLTV.DTO.Request.UserRequest;
import com.practice.QLTV.DTO.Response.UserResponse;
import com.practice.QLTV.Entity.User;
import com.practice.QLTV.Mapper.Mapper;
import com.practice.QLTV.Repository.UserRepository;
import jakarta.validation.constraints.Past;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    private UserRepository userRepository;
    private Mapper userMapper;

    public User createUser(UserRequest request) {
        User user = userMapper.toUser(request);
        return userRepository.save(user);
    }

    public List<UserResponse> getalluser(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = userRepository.findAll(pageable);

        return userPage.getContent().stream()
                .map(user -> new UserResponse(
                        user.getUserId(),
                        user.getUserName(),
                        user.getPassWord(),
                        user.getName(),
                        user.getSDT(),
                        user.getDob(),
                        user.getAddress(),
                        user.getCCCD(),
                        user.getDoc()))
                .collect(Collectors.toList());
    }

    public User getuser(int id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    //    public User myinfo(){
//        var context = SecurityContextHolder.getContext();
//        String name = context.getAuthentication().getName();
//        User user = userrepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User Not Found"));
//        return user;
//    }
    public UserResponse updateuser(int id, UserRequest request) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public String deleteuser(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        userRepository.delete(user);
        return "User has been Deleted";
    }

    public static ByteArrayInputStream usersToExcel(List<UserResponse> users) throws IOException {
        String[] columns = {"ID", "Username","Password", "Name", "Date of birth", "Address", "Date of create", "SDT", "CCCD"};

        try (XSSFWorkbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            XSSFSheet sheet = workbook.createSheet("Users");
            Row headerRow = sheet.createRow(0);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            // Tạo tiêu đề cho file Excel
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Thêm dữ liệu người dùng
            int rowIdx = 1;
            for (UserResponse user : users) {
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(user.getUserId());
                row.createCell(1).setCellValue(user.getUserName());
                row.createCell(2).setCellValue(user.getPassWord());
                row.createCell(3).setCellValue(user.getName());
                if (user.getDob() != null) {
                    String formattedDate = user.getDob().format(dateFormatter);
                    row.createCell(4).setCellValue(formattedDate);
                } else {
                    row.createCell(4).setCellValue("N/A");  // Nếu ngày null, xuất "N/A"
                }
                row.createCell(5).setCellValue(user.getAddress());
                if (user.getDoc() != null) {
                    String formattedDate = user.getDoc().format(dateFormatter);
                    row.createCell(6).setCellValue(formattedDate);
                } else {
                    row.createCell(6).setCellValue("N/A");  // Nếu ngày null, xuất "N/A"
                }
                row.createCell(7).setCellValue(user.getSDT());
                row.createCell(8).setCellValue(user.getCCCD());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }


    public static List<UserResponse> importUsersFromExcel(InputStream is) throws Exception {
        List<UserResponse> users = new ArrayList<>();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // Bỏ qua hàng tiêu đề
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                UserResponse user = new UserResponse();
                Iterator<Cell> cellsInRow = currentRow.iterator();

                int cellIndex = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIndex) {
                        case 0:
                            user.setUserId(Integer.parseInt(currentCell.getStringCellValue()));
                            break;
                        case 1:
                            user.setUserName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            user.setPassWord(currentCell.getStringCellValue());
                        case 3:
                            user.setName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            String dateStr = currentCell.getStringCellValue();
                            LocalDate createdAt = LocalDate.parse(dateStr, dateFormatter);
                            user.setDob(createdAt);
                        case 5:
                            user.setAddress(currentCell.getStringCellValue());
                            break;
                        case 6:
                            String dateStr1 = currentCell.getStringCellValue();
                            LocalDate createdAt1 = LocalDate.parse(dateStr1, dateFormatter);
                            user.setDoc(createdAt1);
                            break;
                        case 7:
                            user.setSDT(currentCell.getStringCellValue());
                            break;
                        case 8:
                            user.setCCCD(currentCell.getStringCellValue());
                            break;

                        default:
                            break;
                    }

                    cellIndex++;
                }
                users.add(user);
            }
        }
        return users;
    }
    public void saveAll(List<UserResponse> users) {
        List<User> user = userMapper.toUserRes(users);
         userRepository.saveAll(user);
    }
}
