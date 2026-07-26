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

    test_Creators();
    test_Add();
    test_Remove();
    test_Observers();
    test_Producer();
    test_Exposure();

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
    private static void test_Exposure() {
        System.out.println("___CREATORS___");
        BoundedStack empty = new BoundedStack(failed_test);
        check("new[]_empty", empty.size()==0);
        check("new[]_contains are nothing", !empty.contains("anything else"));

        BoundedStack b = new BoundedStack(Arrays.asList("A", "B", "C"));
        check("new(list) -> size 3", b.size() == 3);
        check("new(list) -> contains B", b.contains("B"));
        check("new(list) -> preserves order",
                b.queue().equals(Arrays.asList("A", "B", "C")));

        // boundary: list ว่างคือขอบล่างที่ถูกต้อง
        BoundedStack fromEmpty = new BoundedStack(new ArrayList<String>());
        check("new(empty list) -> empty", fromEmpty.size() == 0);

        // input ที่ผิดเงื่อนไขต้องโยน exception ไม่ใช่ปล่อยผ่าน
        boolean threwDup = false;
        try {
            new BoundedStack(Arrays.asList("A", "A"));
        } catch (IllegalArgumentException e) {
            threwDup = true;
        }
        check("new(duplicates) -> throws IllegalArgumentException", threwDup);

        boolean threwNull = false;
        try {
            new BoundedStack(Arrays.asList("A", null));
        } catch (IllegalArgumentException e) {
            threwNull = true;
        }
        check("new(list with null) -> throws IllegalArgumentException", threwNull);

        boolean threwNullList = false;
        try {
            new BoundedStack(null);
        } catch (IllegalArgumentException e) {
            threwNullList = true;
        }
        check("new(null) -> throws IllegalArgumentException", threwNullList);
    
    }
    private static void test_Producer() {
        
    }
    private static void test_Observers() {
        
    }
    private static void test_Remove() {
        
    }
    private static void test_Add() {
    
    }
    private static void test_Creators() {
    
    }
}