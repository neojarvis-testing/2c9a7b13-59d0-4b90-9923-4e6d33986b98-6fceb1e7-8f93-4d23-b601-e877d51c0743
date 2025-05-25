package com.examly.springappuser.jwt;

import java.util.Date;
import java.util.HashMap;
import java.security.Key;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import com.examly.springappuser.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

    private final String SECRET_KEY = "ThisIsTheSecretKeyToGenerateTheJSONWebToken";
    private final long EXP_TIME = 30 * 60 * 1000L;

    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+EXP_TIME);
        HashMap<String, Object> userdata = new HashMap<>();
        userdata.put("userId", user.getUserId());
        userdata.put("username", user.getUsername());
        userdata.put("role", user.getUserRole());
        return Jwts.builder()
                            .setSubject(user.getUsername())
                            .claim("userId", user.getUserId())
                            .claim("role", user.getUserRole())
                            .setIssuedAt(now)
                            .setExpiration(expiryDate)
                            .signWith(getKey(), SignatureAlgorithm.HS256)
                            .compact();
    }
}
