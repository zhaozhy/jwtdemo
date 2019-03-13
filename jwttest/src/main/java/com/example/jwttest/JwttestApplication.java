package com.example.jwttest;

import com.example.jwttest.common.JwtHelp;
import com.google.common.collect.ImmutableMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Map;
import java.util.function.BiConsumer;

@SpringBootApplication
public class JwttestApplication {

    public static void main(String[] args) {

        String token = GetToken();
        System.out.println(token);

       Map<String ,String >maps=  GetMap(token);

        System.out.println(maps.get("name"));
        System.out.println(maps.get("email"));
        SpringApplication.run(JwttestApplication.class, args);
    }

    public  static  String  GetToken()
    {
        String token= JwtHelp.createToken(ImmutableMap.of("name","zhaoliang","email","77703156@qq.com"));
        return   token;
    }

    public static Map<String ,String>   GetMap(String token)
    {
        Map<String,String >  map=null;
        map=JwtHelp.verifyToken(token);
        return map;
    }

}

