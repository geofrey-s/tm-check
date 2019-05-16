package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query(value = "SELECT s.* FROM Student s INNER JOIN Card c ON C.student_id = s.id where c.barcode = :barcode", nativeQuery = true)
    public Student findByBarcode(String barcode);

    public Student findByStudentRegId(String regId);
}
