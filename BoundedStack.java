//6721651513 ปรินยวัฒน์ ปั้นนาค 800
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * BoundedStack —ADT แทนลำดับที่หมายเลขของบัตรคิวรถ  Queue ticket (หมายบัตรคิวรถ)
 *
 * ค่านามธรรม (A): ลำดับที่ของหมายเลขบัตรคิว เช่น [บัตรคิว1, บัตรคิว2, บัตรคิว3]
 *
 * ตัวอย่างการใช้งาน:
 *     BoundedStack q = new BoundedStack();
 *     q.add("1");
 *     q.add("2");
 *     System.out.println(q.size());   
 */
    public class BoundedStack {

    // ===== representation =====
    private final List<String> queue ;
    public static final int MAX_QUEUE = 100;

    // Abstraction Function:
    //   AF(queue = ลำดับบัตรคิวตั้งแต่0-100 (จำนวนสูงสุดไม่เกิน MAX_QUEUE)

    // Representation Invariant:    
    // ต้องมีบัตรคิวอยู่จริง (ไม่เป็น null)
    // ไม่มีบัตรคิวใดเป็น null
    // ไม่มีชื่อบัตรคิวที่เป็นสตริงว่าง
    // ชื่อบัตรคิวห้ามซ้ำกัน
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
            assert !s.isEmpty() : "ticket is empty";
            assert seen.add(s) : "duplicate ticket";
    } 
        }
    

    // ===== Creator =====

    /**
     * สร้างรายการบัตรคิวใหม่ที่ยังไม่มีบัตรคิวใด ๆ
     */
    public BoundedStack() { 
        this.queue = new ArrayList<>();
        checkRep();
    }

     /**
    * สร้างคิวจากลำดับบัตรคิวที่กำหนด
    *
      * @param customer ลำดับบัตรคิวเริ่มต้น ห้ามเป็น null
     * @throws IllegalArgumentException ถ้า customer เป็น null
     * @throws IllegalArgumentException ถ้า customer.size() > MAX_QUEUE
     * @throws IllegalArgumentException ถ้ามี element ใดใน customer เป็น null
     * @throws IllegalArgumentException ถ้ามี element ใดใน customer เป็นสตริงว่าง ("")
     * @throws IllegalArgumentException ถ้ามีบัตรคิวใน customer ซ้ำกัน (ค่าเดียวกันปรากฏมากกว่า 1 ครั้ง)
     */
    public BoundedStack(List<String> customer) {

        if(customer == null) throw new IllegalArgumentException("Customer list is null") ;
        if(customer.size() > MAX_QUEUE)  throw new IllegalArgumentException("List size exceeds MAX_QUEUE") ;
        Set<String> seen = new HashSet<>();
    for(String s : customer){
        if(s == null) throw new IllegalArgumentException("Ticket is null");
        if(s.isEmpty()) throw new IllegalArgumentException("Ticket is empty");
        if(!seen.add(s)) throw new IllegalArgumentException("Duplicate ticket");
        }
        this.queue = new ArrayList<>(customer) ;
        checkRep();
    }
      // ===== Mutators =====

   /**
     *  เพิ่มบัตรคิวอันใหม่ไปลำดับท้ายของรายการ
     *
     * * Post-condition: 
     * - ถ้า return true: บัตรคิวจะถูกเพิ่มที่ท้ายรายการ และ size เพิ่มขึ้น 1
     * - ถ้า return false: รายการจะไม่เปลี่ยนแปลงเลย (unchanged)
     * @param ticket ชื่อบัตรคิวที่ต้องการเพิ่ม ห้ามเป็น null หรือสตริงว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีบัตรคิวนี้อยู่แล้ว หรือรายการเต็มแล้ว (size == MAX_QUEUE)
     * @throws IllegalArgumentException ถ้า ticket เป็น null หรือเป็นสตริงว่าง
     */
    public boolean add(String ticket) {
        checkRep();
        if(ticket == null || ticket.isEmpty()) throw new IllegalArgumentException("Invalid ticket");
        if(queue.contains(ticket) || queue.size() == MAX_QUEUE) return false;
        queue.add(ticket);
        checkRep();
        return true;
    }


  /**
     * ลบบัตรคิวที่ระบุในรายการ
     *
     * Post-condition: 
     * - ถ้า return true: บัตรคิวจะถูกลบออก size ลดลง 1 โดยลำดับของบัตรคิวใบอื่นยังคงเดิม
     * - ถ้า return false: รายการจะไม่เปลี่ยนแปลงเลย (unchanged)
    * @param ticket ชื่อบัตรคิวที่ต้องการลบ (รับค่า null ได้ จะ return false เสมอ)
    * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบบัตรคิวนี้
    */
    public boolean remove(String ticket) {
    checkRep();
    if (!queue.contains(ticket))
        return false;

    queue.remove(ticket);
    checkRep();
    return true;
    }

    // ===== Observers =====

    /**
     *  คืนจำนวนบัตรคิวทั้งหมดที่มีอยู่ในรายการขณะนี้
      * @return จำนวนบัตรคิวปัจจุบัน โดยมีค่าอยู่ในช่วง 0 ถึง MAX_QUEUE เสมอ
     */
    public int size() {
         return queue.size();  
    }

    /**
     *  ตรวจว่ามีบัตรคิวนี้อยู่ในรายการหรือไม่
    * @param ticket ชื่อบัตรคิวที่ต้องการตรวจสอบ (รับค่า null ได้ จะ return false เสมอ
     * เนื่องจากไม่มีบัตรคิวใบใดในรายการเป็น null ตาม RI)
     * @return true ถ้าพบบัตรคิวที่มีค่าเท่ากับ ticket อยู่ในรายการ, false ถ้าไม่พบ
     */
    public boolean contains(String ticket) {
         return queue.contains(ticket);  
    }

    /**
     * คืนค่ารายชื่อบัตรคิวทั้งหมดตามลำดับ
     *
     * @return ลิสต์ของบัตรคิวทั้งหมด (ข้อมูลถูกคัดลอกใหม่เพื่อป้องกันการแก้ไข)
     */
    public List<String> queue() {
        return new ArrayList<>(queue);   
    }

    // ===== Producer =====

    /**
     *  สร้างและคืนค่ารายการบัตรคิวใหม่ 
     *
     * @return คิวใหม่ที่สลับลำดับแบบสุ่มแล้ว
     */
    public BoundedStack shuffled() {
        List<String> copy = new ArrayList<>(queue);
        Collections.shuffle(copy);
        return new BoundedStack(copy);
    }

    /**
     * คืนค่าสตริงที่เป็นตัวแทนของรายการบัตรคิวทั้งหมด
     * 
     * @return สตริงในรูปแบบอาร์เรย์ เช่น "[ticket1, ticket2, ticket3]"
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}

  
