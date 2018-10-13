package com.appd.demo;

import com.appd.demo.models.LoginResponse;
import com.appd.demo.models.LoginVO;
import com.appd.demo.services.LoginService;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by dlopes on 5/20/18.
 */

@RestController
public class LoginController {


    @RequestMapping(value = "/api/login", produces = "application/json")
    public ResponseEntity<LoginResponse> login(@RequestBody String body) throws Exception{
        //System.out.println(body);

        Gson gson = new Gson();

        LoginVO loginVO = gson.fromJson(body, LoginVO.class);
        LoginResponse loginResponse = new LoginService(loginVO).execute();

        if (loginResponse.isSuccess()) {
            return ResponseEntity.ok(loginResponse);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(loginResponse);
        }

    }
}
