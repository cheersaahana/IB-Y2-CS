//Quiz 21/08/26
public class Checker {
    Boolean test = true;
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
    public static void main(String[] args) {
        Checker test1 = new Checker("hello");
        System.out.println(test1.test);

        Checker test2 = new Checker("192.45.7.201");
        System.out.println(test2.test);
    }
}