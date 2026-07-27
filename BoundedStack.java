//6721651513 ปรินยวัฒน์ ปั้นนาค 800
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    public static final int MAX_QUEUE = 100;

    // Abstraction Function:
    //   AF(queue = QueueTicketบัตรคิวตั้งแต่0-100

    // Representation Invariant:    ต้องมีบัตรคิวอยู่จริง (ไม่เป็น null)
    //ไม่มีบัตรคิวใดเป็น null
    //ไม่มีชื่อบัตรคิวที่เป็นสตริงว่าง
    //ชื่อบัตรคิวห้ามซ้ำกัน
    //  มีได้ไม่เกิน MAX_QUEUE (100) บัตรคิว

    // Safety from rep exposure:    
    //  สร้าง queue แบบ final
    // มีการ copy Obj ทั้งตอนสร้างและตอนส่ง

    private void checkRep() {
        assert queue != null : "queue is not null";
        assert queue.size() <= MAX_QUEUE : "queue size more than MAX_QUEUE";
        Set<String> seen = new HashSet<>();
        for (String s : queue) {
            assert s != null : "ticket is null";
            assert s != "" : "ticket is empty";
            assert seen.add(s) : "duplicate ticket";
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
    * สร้างคิวจากลำดับบัตรคิวที่กำหนด
    *
    * ระวัง: ห้ามเก็บ reference ของ customer ตรง ๆ (rep exposure!)
    *
    * @param customer ลำดับบัตรคิวเริ่มต้น ต้องไม่ซ้ำและไม่เกิน MAX_QUEUE
    * @throws IllegalArgumentException ถ้า customer ผิดเงื่อนไข
    */
    public BoundedStack(List<String> customer) {

        if(customer == null) throw new IllegalArgumentException() ;
        if(customer.size() > MAX_QUEUE)  throw new IllegalArgumentException() ;
        Set<String> seen = new HashSet<>();
        for(String s : customer){
            if(s==null) throw new IllegalArgumentException() ;
            if(s=="") throw new IllegalArgumentException();
            if(!seen.add(s)) throw new IllegalArgumentException();
        }
         
        this.queue = new ArrayList<>(customer) ;
        checkRep();
    }
      // ===== Mutators =====

   /**
     *  เพิ่มบัตรคิว
     *
     * @param ticket ชื่อบัตรคิว ต้องไม่เป็น null และไม่เป็นสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีบัตรคิวนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า ticket เป็น null หรือสตริงว่าง
     */
     public boolean add(String ticket) {
        if(ticket == null || ticket == "") throw new IllegalArgumentException();
        if(queue.contains(ticket) || queue.size()==MAX_QUEUE) return false ;
        queue.add(ticket);
        checkRep();
        return true;
    }


    /**
    *   ลบบัตรคิวในเลิสต์
    *
    * @param ticket ชื่อบัตรคิวที่ต้องการลบ
    * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบบัตรคิวนี้
    */
    public boolean remove(String ticket) {
    if (!queue.contains(ticket))
        return false;

    queue.remove(ticket);
    checkRep();
    return true;
    }

    // ===== Observers =====

    /**
     *  คืนจำนวนบัตรคิวในลิสต์
     */
    public int size() {
         return queue.size();  
    }
    /**
     *  ตรวจว่ามีบัตรคิวนี้อยู่หรือไม่
     */
    public boolean contains(String ticket) {
         return queue.contains(ticket);  
    }
    /**
     *  คืนบัตรคิวทั้งหมดตามลำดับ
     *
     * ระวัง: ห้ามคืน reference ของ queueตรง ๆ (rep exposure!)
     */
    public List<String> queue() {
        return new ArrayList<>(queue);   
    }

    // ===== Producer =====

    /**
     *  คืนคิวใหม่ที่มีบัตรคิวเดียวกันแต่สลับลำดับ
     *
     * ระวัง: ห้ามแก้คิวเดิม (this) เด็ดขาด
     *
     * @return คิวใหม่ที่สลับลำดับแล้ว
     */
    public BoundedStack ST() {
        List<String> copy = new ArrayList<>(queue);
        Collections.shuffle(copy);
        return new BoundedStack(copy);
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}

  
