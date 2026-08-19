Quiz 1 - 13/08/2026

Prompt: Create a class that generates a random number between 0 and 256, returns a string.

Code Solution: 
```
public class RanNum {
    public String getNumber() {
        int num = (int) (Math.random() * 257);
        return num + "";
    }
```

Screenshot Proof:
![alt text](<Screenshot 2026-08-19 at 9.24.39 PM.png>)


Quiz 2 - 18/08/2026

Prompt: Create a class that generates a valid IPv4 address. You may use the class RanNum()

Code Solution:
``` 
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

Screenshot Proof:
![alt text](<Screenshot 2026-08-19 at 9.27.40 PM.png>)
