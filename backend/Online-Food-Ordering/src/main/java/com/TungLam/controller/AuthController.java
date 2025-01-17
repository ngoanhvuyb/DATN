package com.TungLam.controller;

import com.TungLam.config.JwtProvider;
import com.TungLam.model.Cart;
import com.TungLam.model.USER_ROLE;
import com.TungLam.model.User;
import com.TungLam.repository.CartRepository;
import com.TungLam.repository.UserRepository;
import com.TungLam.request.LoginRequest;
import com.TungLam.response.AuthResponse;
import com.TungLam.service.CustomerUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private CustomerUserDetailService customerUserDetailService;
    @Autowired
    private CartRepository cartRepository;

//    @PostMapping("/signup")
//    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody User user) throws Exception {
//
//        User isEmailExist = userRepository.findByEmail(user.getEmail());
//
//        if(isEmailExist != null){
//            throw new Exception("Email is already used with another account");
//        }
//
//        User createdUser = new User();
//        createdUser.setEmail(user.getEmail());
//        createdUser.setFullName(user.getFullName());
//        createdUser.setRole(user.getRole());
//        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
//
//        User savedUser = userRepository.save(createdUser);
//
//        Cart cart = new Cart();
//        cart.setCustomer(savedUser);
//        cartRepository.save(cart);
//
//        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        String jwt = jwtProvider.generateToken(authentication);
//
//        AuthResponse authResponse = new AuthResponse();
//        authResponse.setJwt(jwt);
//        authResponse.setMessage("Register success");
//        authResponse.setRole(savedUser.getRole());
//
//        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
//
//    }
@PostMapping("/signup")
public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws Exception {

    // Kiểm tra xem email đã tồn tại chưa
    User isEmailExist = userRepository.findByEmail(user.getEmail());
    if (isEmailExist != null) {
        throw new Exception("Email is already used with another account");
    }

    // Tạo người dùng mới và lưu thông tin
    User createdUser = new User();
    createdUser.setEmail(user.getEmail());
    createdUser.setFullName(user.getFullName());
    createdUser.setPassword(passwordEncoder.encode(user.getPassword()));

    // Gán vai trò người dùng từ thông tin đã chọn trong request
    createdUser.setRole(user.getRole());
//    System.out.println("User Role: " + createdUser.getRole());
//    System.out.println("Role from request: " + user.getRole());
//    System.out.println("User from request: " + user);



    // Lưu người dùng vào cơ sở dữ liệu
    User savedUser = userRepository.save(createdUser);

    // Tạo giỏ hàng cho người dùng
    Cart cart = new Cart();
    cart.setCustomer(savedUser);
    cartRepository.save(cart);

    // Xác thực người dùng và tạo JWT
    Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtProvider.generateToken(authentication);

    // Trả về phản hồi chứa JWT và vai trò đã chọn
    AuthResponse authResponse = new AuthResponse();
    authResponse.setJwt(jwt);
    authResponse.setMessage("Register success");
    authResponse.setRole(savedUser.getRole());

    return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
}

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest req){

        String username = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(username, password);

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String role = authorities.isEmpty()?null:authorities.iterator().next().getAuthority();

        String jwt = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login success");
        authResponse.setRole(USER_ROLE.valueOf(role));
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetails =  customerUserDetailService.loadUserByUsername(username);

        if(userDetails==null){
            throw  new BadCredentialsException("Invalid username...");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password...");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
