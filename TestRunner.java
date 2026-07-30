import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
//6721651734 นายรภัสสา เผ่ามา ทำเทส
 /** 
 * TestRunner สำหรับทดลองและตรวจสอบและทดลองเพื่อหาข้อผิดพลาดของโปรแกรมลิสต์บัตรคิวรถ
 */
public class TestRunner {

    private static int pass_test = 0;
    private static int failed_test = 0;

    /** 
     * Helper หลักที่จะแสดงค่าว่า ผ่านหรือไม่และให้นับผลรวมไปในตัวเอง
     */
    private static void check(String n,Boolean Condition){
        if (Condition) {
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
        if (!AWS) {System.out.println("assertions disabled"
            + " - re-run with: java -ea TestRunner\n");
        }
        System.out.println("___QueueTicket_Number_list_Test___\n");
        
        TR_Creators();
        TR_Producer();
        TR_Observers();
        TR_Mutator_Add();
        TR_Mutator_Delete();
        TR_Exposure();

        System.out.println("\n___SUMMARY___");
        System.out.println("All_Passed: " + pass_test);
        System.out.println("All_Failed: " + failed_test);
        System.out.println("Total : " + (pass_test + failed_test));
        System.out.println(failed_test == 0 ? "ALL_TESTS_PASSED" : "SOME_TESTS_STILL_FAILED");

        if (failed_test > 0) {
                System.exit(1);
        }
    }
    // ลิสต์ว่าง,มีคิว,input ผิดเงื่อนไข
    private static void TR_Creators() {
        System.out.println("___CREATORS___");
        BoundedStack empty = new BoundedStack();
        //เช็คขนาดเริ่มต้น
        check("list_new empty", empty.size()==0);
        //เช็คการค้นหาจากลิสต์ว่าง
        check("list_new contains are nothing", !empty.contains("anything_else"));

        BoundedStack b = new BoundedStack(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));
        //เช็คการค้นหาข้อมูล
        check("list_new contains 12", b.contains("12"));
        check("list_new contains 25", b.contains("25"));
        //เช็คขนาด BoundedStack
        check("list_new size 30", b.size() == 30);
        //เช็คการจัดลำดับข้อมูล
        check("list_new order", b.queue().equals(Arrays.asList
        ("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")));

        //list ว่างคือ = ขอบล่างของ list ถูกต้อง
        BoundedStack FromEmty = new BoundedStack(new ArrayList<String>());
        check("list_new emty", FromEmty.size() == 0);

        // inputผิด throws exception 
        boolean TD = false;
        try {
            new BoundedStack(Arrays.asList("5","5"));
        } catch (IllegalArgumentException e) {
            TD = true;
        }
        check("list_new Duplicates throws IllegalArgumentException", TD);
        //ไม่ยอมให้เก็บข้อมูลที่เป็น null
        boolean TNull = false;
        try {
            new BoundedStack(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", null));
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
    //คืนค่าตัวของใหม่และต้องไม่แก้ค่าของตัวเดิม
    private static void TR_Producer() {
       System.out.println("\n___Producer___");
        BoundedStack OG = new BoundedStack(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"));
        BoundedStack ST = OG.shuffled();
        //ตรวจสอบว่าลิสต์ที่สร้างขึ้นมาใหม่มี จำนวนสมาชิก = ลิสต์เดิมมั้ย
        check("ST(shuffled ticket) has the same size", ST.size() == OG.size());

        List<String> x_List = new ArrayList<String>(OG.queue());
        List<String> y_List = new ArrayList<String>(ST.queue());
        Collections.sort(x_List);
        Collections.sort(y_List);
        //เช็คว่าสมาชิกข้างในครบถ้วนเหมือนเดิมไหมหลังจากเรียงลำดับคิว
        check("ST(shuffled ticket) contains exactly the same queue", x_List.equals(y_List));
        //เช็คว่าไม่ได้ไปแก้ไขข้อมูลต้นฉบับ
        check("ST(shuffled ticket) not mutate the original",
                OG.queue().equals(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30")));

        //เช็คว่าลำดับใน ST ถูกสลับจริงและไม่ได้คืนค่าลำดับเดิมกลับมา
        check("ST(shuffled ticket) should actually shuffle the order",
        !ST.queue().equals(OG.queue()));

        //ตัวใหม่ต้องไม่กระทบตัวเดิมที่มีอยู๋แล้ว
        ST.add("X");
        check("ST mutating the result does not affect the OG)",
                OG.size() == 30);

        //ลิสต์ที่เป็นคิวว่างจะต้องไม่พัง
        BoundedStack empty_ST = new BoundedStack().shuffled();
        check("ST(shuffled ticket) an empty playlist is safe", empty_ST.size() == 0);
        //เช็คว่า ST ไม่ใช่ก้อนเดียวกับ OG
        check("ST(shuffled ticket) not the same reference",
        ST != OG && ST.queue() != OG.queue());
    } 
    //ต้องไม่มี side effect
    private static void TR_Observers() {
        System.out.println("\n___Observers___");
        //เช็คกสรอ่านและคืนค่าของโค้ดนี้
        BoundedStack s = new BoundedStack(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10"));
        check("List_size reports 10", s.size() == 10);
        check("List_contains finds an existing queue", s.contains("5"));
        check("List_contains rejects a missing queue", !s.contains("20"));
        check("List_queue returns the full list in order",
        s.queue().equals(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
        //เช็คว่าเรียกใช้size มันจะแก้ข้อมูลในลิสต์มั้ย 
        check("List_Calling size does not change the list",
        s.size() == 10 && s.queue().equals(Arrays.asList( "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
        //เช็คว่าเรียกใช้contains แค่ค้นหาข้อมูลจริงๆและไม่กระทบต่อข้อมูลภายในลิสต์ 
        check("List_Calling contains does not change the list", s.contains("8") &&
        s.queue().equals(Arrays.asList( "1", "2", "3", "4", "5", "6", "7", "8", "9", "10")));
    }
    //ลองเพิ่มข้อมูลแต่ลำดับต้องไม่พังกับตอนที่ได้คิวซ้ำต้องลิสต์ไม่พัง
    private static void TR_Mutator_Add() {
        System.out.println("\n___Mutator_Add___");
        //เวลาเพิ่มคิว คิวต้องถูกเพิ่มและขนาดต้องเพิ่มด้วยและก็ถูกค้นหาได้
        BoundedStack s = new BoundedStack();
        check("List_Add_(1) must returns true", s.add("1"));
        check("List_Add_(1) size = 1", s.size() == 1);
        check("List_Add_(1) must found in contains", s.contains("1"));
        //ตอนเพิ่มคิวเข้ามาจะเรียงลำดับก่อนค่อยเพื่ม
        s.add("2");
        s.add("3");
        s.add("4");
        s.add("5");
        check("List_Add keeps the order when adds queue",
        s.queue().equals(Arrays.asList("1","2","3","4","5")));
        //ตอนใส่คิวที่ซ้ำเข้ามาต้องไม่เป็นไร
        check("List_Add Duplicate returns false", !s.add("1"));
        check("List_Add fail add but stays the same", s.size() == 5);
        //เวลาinput อะไรแปลกๆที่รับไม่ได้ก็ให้โยน exception
        //ตอนใส่ค่าว่าง
        try {
            s.add("");
            check("List_Add emty throw exceptions", false); 
        } catch (IllegalArgumentException e) {
            check("List_Add emty throw exceptions", true);
        }
        //ตอนไม่ใส่อะไรเลย
        try {
            s.add(null);
            check("List_Add null throw exceptions", false);
        } catch (IllegalArgumentException e) {
            check("List_Add null throw exceptions", true);
        }
    }
    //ลบคิวที่มีอยู่จริงและคิวที่ไม่มีจริว
    private static void TR_Mutator_Delete() {
        System.out.println("\n___Remove___");
        
        BoundedStack ticket = new BoundedStack(Arrays.asList("21","22","23","24"));
        //ลบคิวจากที่มีอยู่ในลิสต์
        boolean Delete = ticket.remove("22");
        List<String> Lticket = Arrays.asList("21","23","24");
        check("List_remove returns ture", Delete);
        check("List_remove size-1", ticket.size() == 3);
        check("List_remove 22 is gone", !ticket.contains("22"));
        check("List_remove same oder", ticket.queue().equals(Lticket));
        //ลบคิวเดิมซ้ำๆต้องไม่มีไรเปลี่ยน
        ticket.remove("22");
        boolean Again = ticket.remove("22");
        check("List_remove returns false", !Again);
        check("List_remove NothingChange", ticket.size() == 3);
        //ลบคิวที่ไม่ได้มีอยู่ในลสตืจริงๆ
        boolean Somethingwrong = ticket.remove("Nothing");
        check("List_remove returns flase",!Somethingwrong);
        check("List_remove NothingChange", ticket.size() == 3);
        //ลองลบค่าว่าง
        boolean Null = ticket.remove(null);
        check("List_remove Null returns false", !Null);
        check("List_remove NothingChange", ticket.size() == 3);
    }
    //ลองหาว่าจะไม่เป็น representation exposure 
    private static void TR_Exposure() {
        System.out.println("\n___Exposure___");
        //แก้ไข List ที่ส่งกลับมาจาก queue() ต้องไม่กระทบข้างใน BoundedStack
        BoundedStack Queue = new BoundedStack();
        Queue.add("1");
        Queue.queue().clear();
        Queue.queue().add("injected");
        check("List_Ex creates a new copy every time", Queue.queue() != Queue.queue());
        check("List_Ex Changing the output (queue()) won't change the actual BoundedStack", 
        Queue.size() == 1 && !Queue.contains("injected"));

        //แก้ List ต้นทางที่ส่งเข้า Constructor ต้องไม่กระทบข้างใน BoundedStack
        List<String> ticket = new ArrayList<>(Arrays.asList("1", "2"));
        BoundedStack copyQueue = new BoundedStack(ticket);
        ticket.clear();
        ticket.add("injected");
        check("List_Ex Changes to the input data won't affect the created BoundedStack", 
        copyQueue.size() == 2 && !copyQueue.contains("injected"));
        }
}
