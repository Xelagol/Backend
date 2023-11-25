import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "linkedpurchaselist")
public class LinkedPurchaseList {

    @EmbeddedId
    @Column(columnDefinition = "varchar(100)")
    private KeyLPL id;

//    @Column(name = "student_id", /*insertable = false, updatable = false,*/ nullable = true)
//    private int studentId;
//    @Column(name = "course_id",/* insertable = false, updatable = false,*/ nullable = true)
//    private int courseId;
    public KeyLPL getId() {
        return id;
    }
    public void setId(KeyLPL id) {
        this.id = id;
    }
//    public int getStudentId() {
//        return studentId;
//    }
//    public void setStudentId(int studentId) {
//        this.studentId = studentId;
//    }
//    public int getCourseId() {
//        return courseId;
//    }
//    public void setCourseId(int courseId) {
//        this.courseId = courseId;
//    }
}
