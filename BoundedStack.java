import java.util.ArrayList;
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
    public final int MAX_Queue =100;
    // Abstraction Function:
    //   AF(queue = QueueTicketบัตรคิวตั้งแต่0-100

    // Representation Invariant:    ต้องมีบัตรคิวอยู่จริง (ไม่เป็น null)
    //ไม่มีบัตรคิวใดเป็น null
    //ไม่มีชื่อบัตรคิวที่เป็นสตริงว่าง
    //ชื่อบัตรคิวห้ามซ้ำกัน
    //  มีได้ไม่เกิน MAX_Queue (100) บัตรคิว

    // Safety from rep exposure:    
    //  สร้าง queue แบบ final
    // มีการ copy Obj ทั้งตอนสร้างและตอนส่ง
    //   ...

    /**
     * checkRep()
     * แปลง RI ทุกข้อเป็น assert หนึ่งบรรทัด พร้อมข้อความอธิบาย
     */
    private void checkRep() {
        assert queue != null : "queue is not null";
        assert queue.size() <= MAX_Queue ;
        Set<String> seen = new HashSet<>();
        for (String s : queue) {
            assert s != null ;
            assert s != "" ;
            assert seen.add(s):"ชื่อเจ้าของบัตรซ้ำ";
} 
        }
    

    //Creator 

    /**
     * สร้างคิวว่าง
     */
    public BoundedStack() { 
        this.queue = new ArrayList<>();
        checkRep();
    }

    /**
     * สร้างบัตรคิวจากรายชื่อลูกค้าหรือคนที่ต่อแถวมา
     * @param Customer รายชื่อเจ้าของบัตรคิว ต้องไม่ซ้ำและไม่เกิน MAX_Queue
     * @throws IllegalArgumentException ถ้า Customer ผิดเงื่อนไข
     */
    public BoundedStack(List<String> Customer) {

        if(Customer == null) throw new IllegalArgumentException() ;
        if(Customer.size() > MAX_Queue)  throw new IllegalArgumentException() ;
        Set<String> seen = new HashSet<>();
        for(String s : Customer){
            if(s==null) {
                throw new IllegalArgumentException() ;
            }
            if(s=="") {
                throw new IllegalArgumentException();
            }
                if(!seen.add(s)) {
            throw new IllegalArgumentException();
            }
        }
        this.queue = new ArrayList<>(Customer) ;
        checkRep();
    }

    //Mutators 
    /**
     * @param ticket บัตรคิวที่ต้องไม่ใช่ null และค่าว่าง
     * @return true ถ้าเพิ่มสำเร็จ, false ถ้ามีคิวนี้อยู่แล้วหรือเต็มแล้ว
     * @throws IllegalArgumentException ถ้า ticket เป็น null หรือค่าว่าง
     */
     public boolean add(String ticket) {
        if(ticket == null || ticket == "") throw new IllegalArgumentException();
        if(queue.contains(ticket) || queue.size()==MAX_Queue) return false ;
        queue.add(ticket);
        checkRep();
        return true;
    }
    /**
     * ลบคิวออกจากคิวทั้งหมด
     * @param ticket คิวที่ต้องการลบออกจากลิสต์
     * @return true ถ้าลบสำเร็จ, false ถ้าไม่พบคิวของคนนี้
     */
    public boolean remove(String ticket) {
        if (!queue.contains(ticket)) {
            return false;
        }
        queue.remove(ticket);
        checkRep();
        return true; 
    }
    /**
     * 
     * @return จำนวนคิวในลิสต์
     */
   public int size() {
        return queue.size();  
    }
    /**
     * 
     * @param ticket
     * @return ค้นหาคิวนั้นๆที่อยู่ในลิสต์
     */
    public boolean contains(String ticket) {
         return queue.contains(ticket);  
    }
    /**
     * คืนรายชื่อเพลงทั้งหมดตามลำดับ
     *
     * ระวัง: ห้ามคืน reference ของ songs ตรง ๆ (rep exposure!)
     */
    public List<String> queue(){
        return new ArrayList<>(queue);   // แก้บรรทัดนี้
    }
    // ===== Producer =====

    /**
     *คืนเพลย์ลิสต์ใหม่ที่มีเพลงเดียวกันแต่สลับลำดับ
     * @return คิวใหม่ที่ทำการเปลี่ยนลำดับที่แล้ว
     */
    public BoundedStack shuffled() {
        List<String> copy = new ArrayList<>(queue);
        Collections.shuffle(copy);
        return new BoundedStack(copy);  
    }
    public String toString() {
        return queue.toString();
    }

}