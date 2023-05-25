package projectJEE.Backend.entities;


import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class to generate and validate connexion tokens
 */
public class JWebToken {

    private static final String SECRET_KEY = "${jwt.secret}";
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final String ISSUER = "projectJEE";
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private JSONObject payload = new JSONObject();
    private String signature;
    private String encodedHeader;

    /**
     * Encode the header to base64
     */
    private JWebToken() {
        encodedHeader = encode(new JSONObject(JWT_HEADER));
    }

    /**
     * Encode a JSONObject to base64
     * @param payload the JSONObject to encode
     * @return the encoded string
     */
    public JWebToken(JSONObject payload) {
        this(payload.getString("sub"), payload.getString("privilege"), payload.getJSONArray("aud"), payload.getLong("exp"));
    }

    /**
     * Encode each part of the token to base64
     * @param sub the subject of the token
     * @param privilege the privilege of the token
     * @param aud the audience of the token
     * @param expires the expiry date of the token
     */
    public JWebToken(String sub, String privilege, JSONArray aud, long expires) {
        this();
        payload.put("sub", sub);
        payload.put("privilege", privilege);
        payload.put("aud", aud);
        payload.put("exp", expires);
        payload.put("iat", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
        payload.put("iss", ISSUER);
        payload.put("jti", UUID.randomUUID().toString());
        signature = hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY);
    }

    /**
     * Create a token from a string and check if it is valid
     * @param token the token to check
     * @throws NoSuchAlgorithmException if the algorithm is not supported
     */
    public JWebToken(String token) throws NoSuchAlgorithmException {
        this();
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Token format");
        }
        if (encodedHeader.equals(parts[0])) {
            encodedHeader = parts[0];
        } else {
            throw new NoSuchAlgorithmException("JWT Header is Incorrect: " + parts[0]);
        }

        payload = new JSONObject(decode(parts[1]));
        if (payload.isEmpty()) {
            throw new JSONException("Payload is Empty: ");
        }
        if (!payload.has("exp")) {
            throw new JSONException("Payload doesn't contain expiry " + payload);
        }
        signature = parts[2];
    }


    @Override
    public String toString() {
        return encodedHeader + "." + encode(payload) + "." + signature;
    }

    /**
     * Check if the token is valid
     * @return true if the token is valid
     */
    public boolean isValid() {
        return payload.getLong("exp") > (LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)) //token not expired
                && signature.equals(hmacSha256(encodedHeader + "." + encode(payload), SECRET_KEY)); //signature matched
    }

    public String getPrivilege() {
        return payload.getString("privilege");
    }

    private static String encode(JSONObject obj) {
        return encode(obj.toString().getBytes(StandardCharsets.UTF_8));
    }

    private static String encode(byte[] bytes) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String decode(String encodedString) {
        return new String(Base64.getUrlDecoder().decode(encodedString));
    }

    /**
     * Sign with HMAC SHA256 (HS256)
     *
     * @param data the data to sign
     * @return the signature
     * @throws Exception if the algorithm is not supported or the key is invalid
     */
    private String hmacSha256(String data, String secret) {
        try {

            //MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);//digest.digest(secret.getBytes(StandardCharsets.UTF_8));

            Mac sha256Hmac = Mac.getInstance("HmacSHA256");
            SecretKeySpec secretKey = new SecretKeySpec(hash, "HmacSHA256");
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            Logger.getLogger(JWebToken.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

}