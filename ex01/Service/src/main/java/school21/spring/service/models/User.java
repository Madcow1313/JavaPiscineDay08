package school21.spring.service.models;

public class User {
    Long identifier;
    String email;

    public User(Long identifier, String email){
        this.identifier = identifier;
        this.email = email;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", email='" + email + '\'' +
                '}';
    }
}
