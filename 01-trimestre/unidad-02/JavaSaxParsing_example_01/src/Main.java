import com.acdat.unit2.sax.MyRunner;
import com.acdat.unit2.sax.User;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MyRunner myRunner = new MyRunner();
        List<User> users = myRunner.parseUsers();

        for (User user:users) {
            System.out.println(user.toString());
        }
    }
}