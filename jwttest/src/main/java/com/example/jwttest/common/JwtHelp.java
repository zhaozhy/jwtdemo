package com.example.jwttest.common;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.util.unit.DataUnit;
import java.security.AlgorithmConstraints;
import java.util.Date;
import java.util.Map;

public class JwtHelp {

    private  static final  String  SCRET="9a96349e2345385785e804e0f4254dee";

    private  static  final  String ISSUER="gerry_user";

    public  static  String createToken(Map<String ,String > maps){

        Algorithm  algorithm=Algorithm.HMAC256(SCRET);
        JWTCreator.Builder  builder= JWT.create().withIssuer(ISSUER).withExpiresAt(DateUtils.addDays(new Date(),1));
        maps.forEach( (k,v)->builder.withClaim(k,v));
        return builder.sign(algorithm).toString();
    }

    public  static  Map<String ,String > verifyToken(String token)
    {
       Algorithm  algorithm=null;
       algorithm=Algorithm.HMAC256(SCRET);

        JWTVerifier  verifier=JWT.require(algorithm).withIssuer(ISSUER).build();
        DecodedJWT  jwt=verifier.verify(token);
        Map<String , Claim> map=jwt.getClaims();
        Map<String,String >  resultMap= Maps.newHashMap();
        map.forEach((k,v)->resultMap.put(k,v.asString()));

        return resultMap;
    }


}
