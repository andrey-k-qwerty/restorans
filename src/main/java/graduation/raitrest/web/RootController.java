package graduation.raitrest.web;

import graduation.raitrest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {
    @Autowired
    private UserService service;

//    @GetMapping("/index")
//    public String root() {
//        return "index";
//    }
    @GetMapping("/")
    public String root2() {
        return "index";
    }
    @GetMapping()
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    @ResponseBody
    public String getUsers(Model model) {
     //   model.addAttribute("users", service.getAll());
    //    return "users";
        return service.getAll().toString();
    }

    @PostMapping("/users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:meals";
    }
}
