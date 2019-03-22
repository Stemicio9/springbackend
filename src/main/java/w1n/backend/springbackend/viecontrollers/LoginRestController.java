package w1n.backend.springbackend.viecontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import w1n.backend.springbackend.model.User;
import w1n.backend.springbackend.services.UserDetailService;

import java.util.List;

@RestController
public class LoginRestController {


    @Autowired
    private UserDetailService userService;


    @GetMapping("/tuttigliutenti")
    public List<User> tuttigliutenti(){
        return userService.findAllUser();
    }

}
