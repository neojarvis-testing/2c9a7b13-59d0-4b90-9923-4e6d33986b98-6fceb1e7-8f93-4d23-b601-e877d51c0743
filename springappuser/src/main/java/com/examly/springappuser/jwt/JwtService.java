package com.examly.springappuser.jwt;

import java.security.Key;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.examly.springappuser.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private static final String SECRET_KEY = "ThisIsTheSecretKeyToGenerateTheJSONWebToken";
    private static final long TOKEN_EXP_TIME = 30*60*1000L;
    private Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByte);
    }
    public String generateToken(User user) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + TOKEN_EXP_TIME);
        return Jwts.builder()
                    .setSubject(user.getUsername())
                    .claim("userId", user.getUserId())
                    .claim("role", user.getUserRole())
                    .setIssuedAt(now)
                    .setExpiration(expiry)
                    .signWith(SignatureAlgorithm.HS256, getKey())
                    .compact();
    }
}
