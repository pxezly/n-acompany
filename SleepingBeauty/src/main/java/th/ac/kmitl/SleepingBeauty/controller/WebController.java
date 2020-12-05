package th.ac.kmitl.SleepingBeauty.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
    @RequestMapping("/contact")
    public String getcontact(Model model) {
            return "Contact";
    }
    @RequestMapping("/about")
    public String getabout(Model model) {
        return "About";
    }
    @RequestMapping("/music")
    public String getmusic(Model model) {
        return "Music";
    }
}
