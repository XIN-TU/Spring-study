package study.projo;

public class UserT {
    private String name;

    public UserT()
    {
        System.out.println("UserT 被创建了");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
