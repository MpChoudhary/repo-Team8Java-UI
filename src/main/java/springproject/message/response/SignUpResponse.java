package springproject.message.response;

public class SignUpResponse {
    private String response;

    public SignUpResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "SignUpResponse{" +
                "response='" + response + '\'' +
                '}';
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }
}
