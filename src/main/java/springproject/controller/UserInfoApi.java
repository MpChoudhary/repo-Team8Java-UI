package springproject.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springproject.message.response.ValidTokenResponse;
import springproject.security.jwt.JwtProvider;
import springproject.service.JsonService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserInfoApi {
    @Autowired
    JwtProvider jwt;
    @Autowired
    JsonService jsonService;
    @RequestMapping(value = "/username", method = RequestMethod.POST)
    public ResponseEntity<?> testUser(HttpServletRequest request,HttpServletResponse res, Authentication auth) throws IOException, ParseException {
        /*  Principal or Authentication can be used to get the name of
            the currently logged in user.
         */
        String isValid;
        String name;
        String token = null;

        String body = jsonService.requestToString(request);
        JSONObject jsonObject = jsonService.parseRequest(body);

        if(jsonService.isJson(body)) {
            String authentication = (String) jsonObject.get("jwt");
            token = authentication.replace("Bearer ", "");
            Boolean valid = jwt.validateJwtToken(token);
            isValid = valid.toString();
        } else {
            isValid = "false";
        }

        if(isValid == "true"){
            name = jwt.getUserNameFromJwtToken(token);
        } else name = "";
        return ResponseEntity.ok(new ValidTokenResponse(isValid, name));
    }
}
