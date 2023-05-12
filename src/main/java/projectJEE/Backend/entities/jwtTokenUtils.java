package projectJEE.Backend.entities;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class jwtTokenUtils {
    private static final long EXPIRE_TIME = 60 * 60;

    private String SECRET_KEY;

    public String generateToken(user user) {
        return Jwts.builder().setSubject(String.format("%d,%s,%s,%d", user.getId(), user.getUsername(), user.getPassword(), user.getPrivilege()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }
}
