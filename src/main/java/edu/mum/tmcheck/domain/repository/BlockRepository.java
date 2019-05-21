package edu.mum.tmcheck.domain.repository;

import edu.mum.tmcheck.domain.entities.Block;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlockRepository extends CrudRepository<Block, Long> {
    public Block getTopByIdIsTrue();

    @Query(value = "SELECT * FROM block b WHERE true LIMIT 1", nativeQuery = true)
    public Block getFirst();

    @Query(value = "SELECT b.ID,b.START_DATE,b.END_DATE,s.STUDENT_REG_ID FROM Block b" +
            " LEFT JOIN OFFERED_COURSE OC on b.ID = OC.BLOCK_ID" +
            " LEFT JOIN STUDENT_ENROLLED_COURSES SEC on OC.ID = SEC.ENROLLED_COURSES_ID" +
            " LEFT JOIN STUDENT S on SEC.STUDENTS_ID = S.ID" +
            " WHERE s.STUDENT_REG_ID=?1" +
            " GROUP BY b.ID,b.START_DATE,b.END_DATE,s.STUDENT_REG_ID", nativeQuery = true)
    public List<Block> findAllByStudentId(String studentId);
}
