package library.templatepattern.connect;

public abstract class ConnectionTemplate {
    public final <T> T connectDB(String dbURL, String user, String password) {
        setDBDriver();
        setCredentials(user, password);
        T conn = openConnection(dbURL);
        return conn;
    }

    public abstract void setDBDriver();
    public abstract void setCredentials(String user, String password);
    public abstract <T> T openConnection(String dbURL);
}
