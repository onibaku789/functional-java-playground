package template.java8;

public interface SSO {
    boolean logIn(String userName, String password);

    boolean sendData(byte[] data);

    void logOut();
}
