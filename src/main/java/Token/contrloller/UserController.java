package Token.contrloller;

import Token.entity.Users;
import Token.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

     private final UserService usersService;

     @Autowired
     public UserController(UserService usersService) {
          this.usersService = usersService;
     }


     @GetMapping("/list")
     public ResponseEntity<?> getList(){
          return ResponseEntity.ok(usersService.listUser());
     }
//
     @GetMapping("/get/{id}")
     public ResponseEntity<?> getOne(@PathVariable(name = "id") Integer id){
          return ResponseEntity.ok(usersService.getUser(id));
     }

     @PostMapping("/register")
     public ResponseEntity<?> create(@RequestBody Users users) {
          if (!checkPasswordLength(users.getPassword())) {
               return new ResponseEntity("Parol uzunligi 4 dan kam", HttpStatus.BAD_REQUEST);
          }
          if (usersService.checkUserPhone(users.getPhone())) {
               return new ResponseEntity("Bu raqam bilan oldin ro'yxatdan o'tilgan", HttpStatus.BAD_REQUEST);
          }

          return ResponseEntity.ok(usersService.create(users));
     }

     private boolean checkPasswordLength(String password){
          return password.length() >= 4;
     }



}
