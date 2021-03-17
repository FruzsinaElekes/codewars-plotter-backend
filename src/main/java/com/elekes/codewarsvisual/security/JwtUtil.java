package com.elekes.codewarsvisual.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static io.jsonwebtoken.Jwts.parser;

@Service
@Getter
public class JwtUtil {

    @Value("${jwt.validityInMs}")
    private int validityInMs;
    @Value("${jwt.secret}")
    private String secret;

    @PostConstruct
    private void encodeSecret() {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(Authentication authentication) {
        // add apiKey as a claim?
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("roles", authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));
        Date date = new Date();
        Date validTill = new Date(date.getTime() + validityInMs);
        return Jwts.builder()
                .setIssuedAt(date)
                .setClaims(claims)
                .setExpiration(validTill)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String retrieveTokenFromHeader(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = "";
        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }
        return token;
    }

    public boolean validateToken(String token) {
        Jws<Claims> claims = parser().setSigningKey(secret).parseClaimsJws(token);
        Date expiration = claims.getBody().getExpiration();
        Date now = new Date();
        if (now.before(expiration)) return true;
        return false;
    }

    public Authentication parseUserFromToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        String username = body.getSubject();
        List<String> roles = (List<String>) body.get("roles");
        List<SimpleGrantedAuthority> authorities = new LinkedList<>();
        for (String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new UsernamePasswordAuthenticationToken(username, "", authorities);
    }
}
