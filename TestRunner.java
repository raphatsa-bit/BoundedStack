import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


 /** 
 * TestRunner สำหรับทดลองและตรวจสอบและทดลองเพื่อหาข้อผิดพลาดของโปรแกรมตั๋วรถ
 */
public class TestRunner {

    private static int pass_test = 0;
    private static int failed_test = 0;

    /** 
     * Helper หลักที่จะแสดงค่าว่า ผ่านหรือไม่และให้นับผลรวมไปในตัวเอง
     */
    private static void check(String name,Boolean condition){
        if (condition) {
            pass_test++;
            System.out.println("[PASS_TEST] " + name);
        } else {
            failed_test++;
            System.out.println("[FAIL_TEST] " + name);
        }
    }   

 public static void main(String[] args){
    boolean AWS = false;
    assert AWS = true;
    if (AWS) {
            System.out.println("assertions disabled"
                    + " - re-run with: java -ea PlaylistTest\n");
        }
    System.out.println("___QueueTicketNumber_Playlist_Test___\n");

    testCreators();
    testAdd();
    testRemove();
    testObservers();
    testProducer();
    testExposure();

     System.out.println("\n___Summary___");
        System.out.println("All_Passed: " + pass_test);
        System.out.println("All_Failed: " + failed_test);
        System.out.println("Total : " + (pass_test + failed_test));
        System.out.println(failed_test == 0 ? "ALL_TESTS_PASSED" : "SOME TESTS FAILED");

        if (failed_test > 0) {
            System.exit(1);
        }
    }
    //
    private static void testExposure() {
        System.out.println("___CREATORS___");
        BoundedStack empty = new BoundedStack(failed_test);
        check("new[]_empty", empty.size()==0);
        check("new[]_contains are nothing", !empty.contains("anything else"));
    
    }
    private static void testProducer() {
        
    }
    private static void testObservers() {
        
    }
    private static void testRemove() {
        
    }
    private static void testAdd() {
    
    }
    private static void testCreators() {
    
    }
}