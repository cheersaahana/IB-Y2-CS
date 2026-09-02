# Quizzes for IB Y2

## Quiz 1 - 13/08/2026

### Prompt: 
Create a class that generates a random number between 0 and 256, returns a string.

### Code Solution: 
```java
public class RanNum {
    public String getNumber() {
        int num = (int) (Math.random() * 257);
        return num + "";
    }
```

### Screenshot Proof:
![alt text](Screenshots/Q1.png)


## Quiz 2 - 18/08/2026

### Prompt: 
Create a class that generates a valid IPv4 address. You may use the class RanNum()

### Code Solution:
```java
public class IPv4 {
    public String generate() {
        String string = "#";
        for (int i = 0 ; i < 4; i++) {
            RanNum num = new RanNum();
            if (i == 3) {
                string = string + num.getNumber();
            } else {
                string = string + num.getNumber() + ".";
            }  
        }
        return string;
    }
```

### Screenshot Proof:
![alt text](Screenshots/Q2.png)


## Quiz 3 - 20/08/2026

### Prompt: 
Create a class that receives a input String add and it checks for valid IPv4 address.

### Code Solution:
```java
public class Checker {
    private boolean test = true;
    public Checker(String add) {
        String[] pieces = add.split("\\.");

        if (pieces.length != 4) {
            test = false;
        } else {
            for (int i = 0; i < pieces.length; i++) {
                try {
                    int num = Integer.parseInt(pieces[i]);
                    if (num < 0 || num > 255) {
                        test = false;
                    }
                } catch(Exception NumberFormatException) {
                    test = false;
                }
            }
        }
    }
```
### Screenshot Proof:  
![alt text](Screenshots/Q3.png)


## Quiz 4 - 21/08/2026

### Prompt:
Create a class receives a service name, ip address and build a ip:port address.

### Code Solution:
```java
public class Porter {
    private ArrayList<String> service = new ArrayList<>();
    private ArrayList<String> port = new ArrayList<>();
    private String serviceName;
    private String ip;
    private String portNum;

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
}
```

### Screenshot Proof:
![alt text](Screenshots/Q4.png)


## Quiz 5 - 24/08/2026

### Prompt:
Create a class that uses parallel arrays to store a table of hostnames and their IP addresses.

### Code Solution:
```java
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
}
```

### Screenshot Proof:
![alt text](Screenshots/Q5.png)


## Quiz 6 - 27/08/2026

### Prompt:
Create a class that uses parallel arrays to filter traffic in network using ip/hostnames, assume the DNS:

### Code Solution:
```java
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
}
```

### Screenshot Proof:
![alt text](Screenshots/Q6.png)