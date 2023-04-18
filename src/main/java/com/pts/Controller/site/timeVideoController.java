package com.pts.Controller.site;

import com.pts.DAO.UserContentDAO;
import com.pts.entity.UserContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

    @RestController
    public class timeVideoController {
        @Autowired
        UserContentDAO userContentDAO;


        @PostMapping("/save-time")
        @ResponseBody
        public String saveTime(@RequestParam String  contentId, @RequestParam String time, HttpServletRequest request) {
            String user = request.getRemoteUser();
            int id = Integer.parseInt(contentId);

            UserContent userContent = userContentDAO.addtime(user, id);
            if (userContent != null) {
                if ("Complete".equalsIgnoreCase(time) || userContent.getTime().equalsIgnoreCase("Complete")) {
                    userContent.setTime("Complete");
                    userContentDAO.save(userContent);
                    return "Content already completed";
                } else {
                    userContent.setTime(time);
                    userContentDAO.save(userContent);
                    return "Time saved successfully";
                }
            } else {
                return "User content not found";
            }
        }


    }
