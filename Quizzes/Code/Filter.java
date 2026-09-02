public class Filter {
    private String[] hostName = {"localhost", "google.com", "example.com"};
    private String[] ip = {"127.0.0.1", "142.250.72.14", "7.7.7.7"};
    private String test;

    public Filter (String test) {
        this.test = test;
    }

    public String check() {
        for (int i = 0; i < hostName.length; i++) {
            if (test.equals(hostName[i])) {
                return "#accepted with ip:" + ip[i] + " hostname:" + test;
            } 
            if (test.equals(ip[i])) {
                return "#accepted ip:" + test + " hostname:" + hostName[i];
            }
        }
        return "#rejected";
    }

    public static void main(String[] args) {
        Filter test1 = new Filter("127.0.0.1");
        System.out.println(test1.check());
        Filter test2 = new Filter("time.com");
        System.out.println(test2.check());
        Filter test3 = new Filter("example.com");
        System.out.println(test3.check());
    }
}