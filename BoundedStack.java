import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * QueueTicket — ADT แทนลำดับที่หมายเลขของบัตรคิวรถ  Queue ticket (หมายบัตรคิวรถ)
 *
 * ค่านามธรรม (A): ลำดับที่ของหมายเลขบัตรคิว เช่น [บัตรคิว1, บัตรคิว2, บัตรคิว3]
 *
 * ตัวอย่างการใช้งาน:
 *     QueueTicket q = new QueueTicket();
 *     q.add("1");
 *     q.add("2");
 *     System.out.println(q.size());   // 2
 */
public class BoundedStack {
  // ===== representation =====
    private final List<String> queue ;
    public final int MAX_QUEUE =100;
    // TODO 1: เขียน Abstraction Function ตรงนี้
    // Abstraction Function:
    //   AF(queue = QueueTicketบัตรคิวตั้งแต่0-100

    // TODO 2: เขียน Representation Invariant ตรงนี้ (4 ข้อ)
    // Representation Invariant:    ต้องมีบัตรคิวอยู่จริง (ไม่เป็น null)
    //ไม่มีบัตรคิวใดเป็น null
    //ไม่มีชื่อบัตรคิวที่เป็นสตริงว่าง
    //ชื่อบัตรคิวห้ามซ้ำกัน
    //  มีได้ไม่เกิน MAX_QUEUE (100) บัตรคิว

    // TODO 3: เขียน Safety from rep exposure ตรงนี้ 
    // Safety from rep exposure:    
    //  สร้าง queue แบบ final
    // มีการ copy Obj ทั้งตอนสร้างและตอนส่ง
    //   ...

    /**
     * TODO 4: เขียน checkRep()
     * แปลง RI ทุกข้อเป็น assert หนึ่งบรรทัด พร้อมข้อความอธิบาย
     */
    private void checkRep() {
        assert queue != null : "queue is not null";
        assert queue.size() <= MAX_QUEUE ;
        Set<String> seen = new HashSet<>();
        for (String s : queue) {
            assert s != null ;
            assert s != "" ;
            assert seen.add(s) ;
} 
        }
    

    // ===== Creator =====

    /**
     * สร้างคิวว่าง
     */
    public BoundedStack() { 
        this.queue = new ArrayList<>();
        checkRep();
    }

     /**
     * TODO 5: Creator ตัวที่สอง
     * สร้างเพลย์ลิสต์จากรายชื่อเพลงที่ให้มา
     *
     * ระวัง: ห้ามเก็บ reference ของ initial ตรง ๆ (rep exposure!)
     *
     * @param initial รายชื่อเพลงเริ่มต้น ต้องไม่ซ้ำและไม่เกิน MAX_SONGS
     * @throws IllegalArgumentException ถ้า initial ผิดเงื่อนไข
     */
    public BoundedStack(List<String> Customer) {

        if(Customer == null) throw new IllegalArgumentException() ;
        if(Customer.size() > MAX_QUEUE)  throw new IllegalArgumentException() ;
        Set<String> seen = new HashSet<>();
        for(String s : Customer){
            if(s==null) throw new IllegalArgumentException() ;
            if(s=="") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
        //this.songs = intitial;
        this.queue = new ArrayList<>(Customer) ;
        checkRep();
        // เขียนโค้ดตรงนี้
    }


    /**
     * 
     * @param r
     */
     public boolean add(String ticket) {
        if(ticket == null || ticket == "") throw new IllegalArgumentException();
        if(queue.contains(ticket) || queue.size()==MAX_QUEUE) return false ;
        queue.add(ticket);
        checkRep();
        return true;
    }



   public int size() {
        return queue.size();  
    }


    public boolean contains(String string) {
         return queue.contains(queue);  
    }
    
    public String toString() {
        return queue.toString();
    }


  
}