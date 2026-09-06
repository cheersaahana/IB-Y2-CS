public class NAT {
    private int deviceCount = 0;
    private String[] localIP;
    private int[] localPorts;
    private int[] publicPorts;
    private String publicIP;

    public NAT (String publicIP, int deviceCount) {
        this.publicIP = publicIP;
        localIP = new String[deviceCount];
        localPorts = new int[deviceCount];
        publicPorts = new int[deviceCount];
    }

    public String get_new_trans(String ip, int port) {
        localIP[deviceCount] = ip;
        localPorts[deviceCount] = port;
        publicPorts[deviceCount] = 10001 + deviceCount;
        deviceCount++;

        return publicIP + ":" + publicPorts[deviceCount - 1];
    }

    public static void main(String[] args) {
        NAT test = new NAT("203.0.113.55", 3);
        String[] testIPs = {"192.168.1.10", "192.168.1.11", "192.168.1.12"};
        int[] testPorts = {4500, 5100, 5100};

        for (int i = 0; i < testIPs.length; i++) {
            System.out.println(test.get_new_trans(testIPs[i], testPorts[i]));
        }
    }
}