package com.gracegh.registrationandloginfeature.Controller;

import com.gracegh.registrationandloginfeature.Entity.User;
import com.gracegh.registrationandloginfeature.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProgramController {

    @Autowired
    private UserRepository userRepository;

    public ProgramController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("")
    public String viewHomePage(){
        return "index";//returns the index.html page...
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());//sends a new user object to the sign_up_form...
        return "sign_up_form";
    }


    //implementing the code for processing user data in registration...for this, you will use post mapping...
    @PostMapping("/process_register")
    public String processRegistration(User user){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);

        return "register_success";
    }

    @GetMapping("/list_users")
    public String viewUsersList(Model model){
        List<User> listUsers = userRepository.findAll();
         model.addAttribute("listUsers", listUsers);
        return "users";
    }
}

//custom user details class represents the authenticated user...