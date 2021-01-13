import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author youguoqiang
 * @date 2020/8/17
 */
public class IteratorRemove {
    public static void main(String[] args) {
        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        /*for (int i = 0; i < 1; i++) {
            if (userNames.get(i).equals("Hollis")) {
                userNames.remove(i);
            }
        }*/
        Iterator iterator = userNames.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("Hollis")) {
                iterator.remove();
            }
        }
        userNames.remove("H");
        System.out.println(userNames);
    }
}
