package study.demo01;

public class Proxy {
    private Host host; // 使用组合而不是继承

    public Proxy() {

    }

    public Proxy(Host host) {
        this.host = host;
    }

    public void rent() {
        host.rent();
        seeHouse();
        fare();
        contract();
    }

    //看房
    public void seeHouse()
    {
        System.out.println("中介带你看房");
    }

    //收中介费
    public void fare()
    {
        System.out.println("中介收中介费");
    }

    //签租赁合同
    public void contract()
    {
        System.out.println("签租赁合同");
    }
}
