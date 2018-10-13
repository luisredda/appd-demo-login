package com.appd.demo.services;

import com.appd.demo.models.LoginResponse;
import com.appd.demo.models.LoginVO;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

/**
 * Created by dlopes on 5/20/18.
 */

public class LoginService extends HystrixCommand<LoginResponse> {

    private LoginVO loginVO;

    public LoginService(LoginVO loginVO){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("LoginGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withMetricsRollingStatisticalWindowInMilliseconds(60000)));

        this.loginVO = loginVO;

    }

    @Override
    protected LoginResponse run() throws InterruptedException{

        LoginResponse loginResponse = new LoginResponse();

        Thread.sleep(300 + (long) (Math.random() * 1000));
        loginResponse.setMessage("Login efetuado com sucesso!");
        loginResponse.setSuccess(true);

        return loginResponse;

    }

    @Override
    protected LoginResponse getFallback() {
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setMessage("Falha no Login!");
        loginResponse.setSuccess(false);
        return loginResponse;
    }

}

