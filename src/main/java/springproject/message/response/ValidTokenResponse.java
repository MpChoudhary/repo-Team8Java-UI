package springproject.message.response;

public class ValidTokenResponse {
    private String isValid;
    private String name;

    public ValidTokenResponse(String isValid, String name) {
        this.isValid = isValid;
        this.name = name;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
