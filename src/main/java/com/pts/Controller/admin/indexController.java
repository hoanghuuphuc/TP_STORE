package com.pts.Controller.admin;

import com.pts.entity.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class indexController {

    @RequestMapping("admin")
    public String qltk(Model m){


        return "Admin/index";
    }
}
