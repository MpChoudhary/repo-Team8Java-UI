package springproject.message.response;

import java.util.Date;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    public String issuedDate;

    public JwtResponse(String token, String issuedDate) {
        this.token = token;
        this.issuedDate = issuedDate;
    }
//
//    public JwtResponse(String accessToken) {
//        this.token = accessToken;
//    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }
}