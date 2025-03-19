package escom.admin.servicioAlCliente.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import java.awt.*;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String getToken(UserDetails usuario){
        return getToken(new HashMap<>(), usuario);
    }

    /**
     * Obtiene el token y le asigna una duración de 3 horas
     * @param extraClaims
     * @param user El usuaio del cual se esta obteniendo el token
     * @return retorna el token encriptado en base 32
     */
    private String getToken(Map<String, Object> extraClaims, UserDetails user){
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Obtiene la key del token que se saca utilizando la clave secreta en el servidor
     * @return retorna la key decodificada en base 64
     */
    private Key getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getCorreoFromToken(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getCorreoFromToken (token);
        return  (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims getAllClaims (String token){
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }
    public <T> T getClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Date getExpiration(String token){
        return getClaim(token, Claims::getExpiration);
    }
    private boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());

    }
}
