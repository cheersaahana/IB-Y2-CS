//Quiz 13/08/26

public class RanNum {
    public String getNumber() {
        int num = (int) (Math.random() * 257);
        return num + "";
    }

    public static void main(String[] args) {
        RanNum generator = new RanNum();
        System.out.println(generator.getNumber());
    }
}