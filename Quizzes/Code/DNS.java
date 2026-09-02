import java.util.ArrayList;
public class DNS {
    private ArrayList<String> hostName = new ArrayList<>();
    private ArrayList<String> IP = new ArrayList<>();
    private String host;
    private String ip;

    public DNS(String host) {
        this.host = host;
        this.hostName.add("localhost"); this.hostName.add("google.com"); this.hostName.add("example.com");
        IP.add("127.0.0.1"); IP.add("142.250.72.14"); IP.add("7.7.7.7");
    }

    public String lookup() {
        for (int i = 0; i < hostName.size(); i++) {
            if (host.equals(hostName.get(i))) {
                ip = IP.get(i);
                break;
            } else {
                ip = "-1";
            }
        }
        
        if (ip.equals("-1")) {
            return "#hostname not on record table. Use register() to add a new hostname:IP pair";
        } else {
            return "#" + ip;
        }
    }

    public void register(String host, String ip) {
        hostName.add(host);
        IP.add(ip);
    }

    public static void main(String[] args) {
        DNS test1 = new DNS("google.com"); 
        System.out.println(test1.lookup());
        DNS test2 = new DNS("jisedu.or.id"); 
        System.out.println(test2.lookup());
        test2.register("jisedu.or.id", "104.17.68.73");
        System.out.println(test2.lookup());
    }
}