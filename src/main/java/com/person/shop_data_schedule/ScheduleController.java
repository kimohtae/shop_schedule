package com.person.shop_data_schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScheduleController {
    @GetMapping("/")
    public String getMain(){
        return "/index";
    }
}
