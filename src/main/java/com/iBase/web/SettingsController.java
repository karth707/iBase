package com.iBase.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.iBase.service.db.UserInfoDAO;

@Controller
public class SettingsController
{
    private String userId = "jake@asu.edu";

    @RequestMapping(value="/settings", method = RequestMethod.GET)
    public String handleSettingsRequest(Model model)
    {
        Authentication auth = SecurityContextHolder.getContext()
                .getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken))
        {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addAttribute("userName", userDetail.getUsername());
            return "settings";
        }
        return "settings";
    }
    @Autowired
    private UserInfoDAO userInfoDAO;

    public UserInfoDAO getUserInfoDAO() {
        return userInfoDAO;
    }

    public void setUserInfoDAO(UserInfoDAO userInfoDAO)
    {
        this.userInfoDAO = userInfoDAO;
    }
}

