package com.iBase.web;

import com.iBase.domain.ImageFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;


@Controller
public class SettingsController
{
    @RequestMapping(value="/settings", method = RequestMethod.GET)
    public String handleSettingsRequest(Model model)
    {
        return "settings";
    }

}

