package com.spring.boot.config;

import com.spring.boot.dto.UserDto;
import com.spring.boot.service.UserService;
import com.spring.boot.sitting.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.Objects;

@Component
public class TokenHandler {

    @Autowired
    private UserService userService;

    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;
    private String secretKey;
    private Duration time;

    private TokenHandler(JwtToken jwtToken) {
        this.secretKey = jwtToken.getSecret();
        this.time=jwtToken.getTime();
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(UserDto userDto) {
        Date issueDate = new Date();
        Date expiryDate =Date.from(issueDate.toInstant().plus(time));
        jwtBuilder.setSubject(userDto.getUserName());
        jwtBuilder.setIssuedAt(issueDate);
        jwtBuilder.setExpiration(expiryDate);
        return jwtBuilder.compact();
    }


    public UserDto validateToken(String token) {
        if(jwtParser.isSigned(token)) {
          Claims claims= jwtParser.parseClaimsJws(token).getBody();
          Date issueDate = claims.getIssuedAt();
          Date expiryDate = claims.getExpiration();
          UserDto userDto = userService.getUserByName(claims.getSubject());
          boolean isExpired = expiryDate.after(new Date()) && issueDate.before(expiryDate) && Objects.nonNull(userDto);
          if (isExpired) {
              return userDto;
          }
        }

        return null;
    }
}
