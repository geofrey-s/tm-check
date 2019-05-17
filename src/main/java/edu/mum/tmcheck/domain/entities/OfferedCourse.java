package edu.mum.tmcheck.domain.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class OfferedCourse implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    Course course;

    @ManyToOne(optional = true)
    @JoinColumn
    Faculty faculty;

    @ManyToOne(optional = true)
    @JoinColumn
    Block block;

    @ManyToMany
    List<Student> students;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
