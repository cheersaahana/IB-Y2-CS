//Quiz 18/08/26
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

    public static void main(String[] args) {
        IPv4 addr = new IPv4();
        System.out.println(addr.generate());
    }
}