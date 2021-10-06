package template.prejava8;

public abstract class Network {
    String userName;
    String password;

    Network() {
    }

    /**
     * Publish the data to whatever network.
     */
    public final boolean post(String message) {
        // Authenticate before posting. Every network uses a different
        // authentication method.
        if (logIn(this.userName, this.password)) {
            // Send the post data.
            boolean result = sendData(message.getBytes());
            logOut();
            return result;
        }
        return false;
    }

    protected abstract boolean logIn(String userName, String password);

    protected abstract boolean sendData(byte[] data);

    protected abstract void logOut();
}
