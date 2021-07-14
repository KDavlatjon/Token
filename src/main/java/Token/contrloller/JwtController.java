package Token.contrloller;

import Token.entity.LoginVM;
import Token.entity.Users;
import Token.repository.UserRepository;
import Token.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JwtController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Autowired
    public JwtController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginVM loginVM){
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginVM.getName(), loginVM.getPassword()));
        Users users = userRepository.findByName(loginVM.getName());
        if (users == null){
            throw new UsernameNotFoundException("Bunday foydalanuvchi mavjud emas");
        }
        String token = jwtTokenProvider.createToken(users.getName(), users.getRoles());

//========= The required parameters are added here
        Map<Object, Object> map = new HashMap<>();
        map.put("userName", users.getName());
        map.put("token", token);
        return ResponseEntity.ok(map);
    }
}
