import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

 /** 
 * TestRunner สำหรับทดลองและตรวจสอบและทดลองเพื่อหาข้อผิดพลาดของโปรแกรมลิสต์บัตรคิวรถ
 */
public class TestRunner {

    private static int pass_test = 0;
    private static int failed_test = 0;

    /** 
     * Helper หลักที่จะแสดงค่าว่า ผ่านหรือไม่และให้นับผลรวมไปในตัวเอง
     */
    private static void check(String n,Boolean condition){
        if (condition) {
            pass_test++;
            System.out.println("[PASS_TEST] " + n);
        } else {
            failed_test++;
            System.out.println("[FAIL_TEST] " + n);
        }
    }   

 public static void main(String[] args){
        boolean AWS = false;
        assert AWS = true;
    
        test_Creators();
        test_Producer();
        test_Observers();
        test_Mutator_Add();
        test_Mutator_Delete();
        test_Exposure();
        
        if (AWS) {
                System.out.println("assertions disabled"
                        + " - re-run with: java -ea PlaylistTest\n");
        }
        System.out.println("___QueueTicketNumber_Playlist_Test___\n");


        System.out.println("\n___Summary___");
        System.out.println("All_Passed: " + pass_test);
        System.out.println("All_Failed: " + failed_test);
        System.out.println("Total : " + (pass_test + failed_test));
        System.out.println(failed_test == 0 ? "ALL_TESTS_PASSED" : "SOME_TESTS_STILL_FAILED");

        if (failed_test > 0) {
                System.exit(1);
        }
    }
    // ลิสต์ว่าง,มีคิว,input ผิดเงื่อนไข
    private static void test_Creators() {
        System.out.println("___CREATORS___");
        BoundedStack empty = new BoundedStack();
        //เช็คขนาดเริ่มต้น
        check("list_new empty", empty.size()==0);
        //เช็คการค้นหาจากลิสต์ว่าง
        check("list_new contains are nothing", !empty.contains("anything_else"));

        BoundedStack b = new BoundedStack(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H",
         "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));
        //เช็คการค้นหาข้อมูล
        check("list_new contains E", b.contains("E"));
        check("list_new contains X", b.contains("X"));
        check("list_new contains N", b.contains("N"));
        //เช็คขนาด BoundedStack
        check("list_new size 26", b.size() == 26);
        //เช็คการจัดลำดับข้อมูล
        check("list_new order", b.queue().equals(Arrays.asList
        ("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q",
         "R", "S", "T", "U", "V", "W", "X", "Y", "Z")));

        //list ว่างคือ = ขอบล่างของ list ถูกต้อง
        BoundedStack FromEmty = new BoundedStack(new ArrayList<String>());
        check("list_new emty", FromEmty.size() == 0);

        // inputผิด throws exception 
        boolean TD = false;
        try {
            new BoundedStack(Arrays.asList("X","X"));
        } catch (IllegalArgumentException e) {
            TD = true;
        }
        check("list_new Duplicates throws IllegalArgumentException", TD);
        //ไม่ยอมให้เก็บข้อมูลที่เป็น null
        boolean TNull = false;
        try {
            new BoundedStack(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H",
         "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", null));
        } catch (IllegalArgumentException e) {
            TNull = true;
        }
        check("list_new Null throws IllegalArgumentException", TNull);
        //ไม่ยอมรับ List ที่ไม่มีอยู่จริง
        boolean TNullList = false;
        try {
            new BoundedStack(null);
        } catch (IllegalArgumentException e) {
            TNullList = true;
        }
        check("list_new LISTNull throws IllegalArgumentException", TNullList);
    
    }
    
    private static void test_Producer() {
        
    }
    private static void test_Observers() {
        
    }
    private static void test_Mutator_Add() {
    
    }
    
    private static void test_Mutator_Delete() {
        
    }
    private static void test_Exposure() {
        
    }
}