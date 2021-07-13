package study.pojo;

// 实体类
public class User {
    private int ID;
    private String NAME;
    private String PWD;

    public User() {
    }

    public User(int ID, String NAME, String PWD) {
        this.ID = ID;
        this.NAME = NAME;
        this.PWD = PWD;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getPWD() {
        return PWD;
    }

    public void setPWD(String PWD) {
        this.PWD = PWD;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", PWD='" + PWD + '\'' +
                '}';
    }
}
