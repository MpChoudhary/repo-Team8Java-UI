package springproject.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import springproject.service.JsonService;

import java.io.IOException;
import java.util.Iterator;


@Controller
public class LoginController {

    @Autowired
    private JsonService jsonService;

    @RequestMapping(value = "/LoginHandler", method = RequestMethod.GET)
    public String loginGetHandler() {
        return "login";
    }

    @RequestMapping(value = "/LoginHandler", method = RequestMethod.POST)
    public void loginPostHandler(HttpServletRequest req, HttpServletResponse res, ModelMap model)
            throws IOException, ParseException {

        String body = jsonService.requestToString(req);
        JSONObject jsonObject = jsonService.parseRequest(body);

        if(jsonService.isJson(body)) {
            jsonService.flushJSON(jsonObject, res);
            jsonService.flushMessage("Success", res);
//            return "";
        }
//
//        System.out.println("Not JSON request!\n" + body);
//
//        for (Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
//            String key = (String) iterator.next();
//            model.put(key, jsonObject.get(key));
//        }
//
//        System.out.println(model);
//
//        return "dashboard";
    }
}
