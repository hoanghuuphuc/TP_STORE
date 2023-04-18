package com.pts.Controller.site;

import com.pts.DAO.*;
import com.pts.Service.AccountService;
import com.pts.Service.CategoryService;
import com.pts.Service.CourseService;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Base64;
import com.pts.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import javax.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


@Controller

public class homeController {

    @Autowired
    AccountService accountService;
    @Autowired
    CourseService courseService;

    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    CategoryService categoryService;
    @Autowired
    ContentDAO contentDAO;
    @Autowired
    ChapterDAO chapterDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    CourseDAO courseDAO;
    @Autowired
    UserContentDAO userContentDAO;




    @RequestMapping("/...")
    public String main(){

        return "/stie/index";
    }




    @RequestMapping("")
    public String index(Model m,HttpServletRequest request){

        String username =request.getRemoteUser();
        Account acc =accountDAO.laytk(username);
        if(acc !=null){
            String image =acc.getTps_Photo();
            m.addAttribute("image",image);
        }


        //khoa hoc free
        List<Course> courses = courseService.findAll().stream().filter(Course::isTps_Status).filter(course -> course.getTps_Price()==0).collect(Collectors.toList());
        m.addAttribute("courses",courses);

        //khoa hoc co phi
        List<Course> courses1 = courseService.findAll().stream().filter(Course::isTps_Status).filter(course -> course.getTps_Price() >0).collect(Collectors.toList());
        m.addAttribute("coursess",courses1);

        //danh muc khoa hoc
        List<Category>categories =categoryDAO.findAll();
        m.addAttribute("categories",categories);



        return "/site/home";
    }



//    trang chi tiết


    @RequestMapping("/khoa-hoc/{tps_id}")
    public String trangchitiet(Model m, @PathVariable("tps_id") int id, HttpServletRequest request) throws FileNotFoundException {
        //tim kiếm nội dung theo id

        System.out.println(request.getRemoteUser());
            Course course = courseService.findById(id);
            m.addAttribute("detail", course);
            String username=request.getRemoteUser();

//        System.out.println(username);
            Order order = orderDAO.ktKhoaHoc(username, id);
            if (order != null) {
                m.addAttribute("owned", true);
                List<Integer> maxContentIds = new ArrayList<>();
                List<UserContent> contentIds = userContentDAO.layvideodanghoc(username, order.getCourseor().getTps_id());
                int maxContentId = 0;
                for (UserContent userContent : contentIds) {
                    if (userContent.isCompleted() && userContent.getContent().getTps_id() > maxContentId) {
                        maxContentId = userContent.getContent().getTps_id();
                    }
                }
                maxContentIds.add(maxContentId);
                m.addAttribute("maxContentId",maxContentId);
                System.out.println(maxContentId);
            } else {
                m.addAttribute("owned", false);
            }


            //nội dụng chương
            List<Chapter> cc = chapterDAO.findByCourse(id);
            m.addAttribute("listC", cc);

            //doc file
            int htt = id;
        String url = "https://res.cloudinary.com/djfbh9dqg/raw/upload/v1678295361/2850_qaey4h.txt";
        try {
            URL cloudinaryUrl = new URL(url);
            URLConnection connection = cloudinaryUrl.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("\n");
            }
            reader.close();
            String fileContent = sb.toString();
            m.addAttribute("fileContent", fileContent);
        } catch (IOException e) {
            System.out.println("Error reading file from Cloudinary: " + e.getMessage());
        }
            //tong chuong
            int count=chapterDAO.findByCourse(id).size();
            m.addAttribute("soluongChuong",count);

            //tong bai hoc
            int totalContent = 0;
            for (Chapter chapter : cc) {
                totalContent += chapter.getContents().size();
                m.addAttribute("totalContent",totalContent);
            }
            return "/site/details";

    }
    @GetMapping("upload")
    public String upload(Model m){
        return "site/file";
    }


}
