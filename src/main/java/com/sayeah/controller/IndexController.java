package com.sayeah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by BIG-JIAN on 2017/5/31.
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/")
    public String index(Model model) {

        return "index";
    }

    @RequestMapping(value = "index")
    public String index2() {
        return "index";
    }


    @RequestMapping(path = {"/studyList/{userId}"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String studyList(Model model, @PathVariable("userId") int userId) {


        return "studylist";
    }


}
