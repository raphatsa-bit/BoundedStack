import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    private static int pass_test = 0;
    private static int failed_test = 0;

    /** 
     * 
     *
     */
    private static void check(String name, boolean condition) {
        if (condition) {
            pass_test++;
            System.out.println("[PASS] " + name);
        } else {
            failed_test++;
            System.out.println("[FAIL] " + name);
        }
    }
    
}