import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class KeyLPL implements Serializable {
    @Column(columnDefinition = "varchar(50)" )
private Integer studentId;
    @Column(columnDefinition = "varchar(50)" )

    private Integer courseId;
    public KeyLPL(){}
    public KeyLPL(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyLPL keyLPL = (KeyLPL) o;
        return Objects.equals(studentId, keyLPL.studentId) && Objects.equals(courseId, keyLPL.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }
}
