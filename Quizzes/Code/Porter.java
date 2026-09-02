import java.util.ArrayList;
public class Porter {
    ArrayList<String> service = new ArrayList<>();
    ArrayList<String> port = new ArrayList<>();
    String serviceName;
    String ip;
    String portNum;

    public Porter(String serviceName, String ip) {
        this.serviceName = serviceName;
        this.ip = ip;
        service.add("http"); service.add("https"); service.add("playstation"); service.add("ssh"); service.add("ftp"); service.add("mysql");
        port.add("80"); port.add("443"); port.add("3479"); port.add("22"); port.add("20"); port.add("3306");
    }

    public String build() {
        for (int i = 0; i < service.size(); i++) {
            if (serviceName.equals(service.get(i))) {
                portNum = port.get(i);
                break;
            } else {
                portNum = "-1";
            }
        }
        
        if (portNum.equals("-1")) {
            return "#service not on record table. Use register() to add a new service:port pair";
        } else {
            return "#" + ip + ":" + portNum;
        }
    }

    public void register(String service, String port) {
        this.service.add(service);
        this.port.add(port);
    }

    public static void main(String[] args) {
        Porter test1 = new Porter("http", "192.45.7.201"); 
        System.out.println(test1.build());
        Porter test2 = new Porter("xbox", "192.45.7.201"); 
        System.out.println(test2.build());
        test2.register("xbox", "3074");
        System.out.println(test2.build());
    }
}