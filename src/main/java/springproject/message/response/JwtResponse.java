package springproject.message.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    public String expiration;
    public String name;

    public JwtResponse(String token, String expiration, String name) {
        this.token = token;
        this.expiration = expiration;
        this.name =  name;
    }
    public JwtResponse(String accessToken) {
        this.token = accessToken;
    }

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