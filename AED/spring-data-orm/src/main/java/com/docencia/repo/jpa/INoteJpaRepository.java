package com.docencia.repo.jpa;

import com.docencia.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface INoteJpaRepository extends JpaRepository<Note, String> {
    Optional<Note> findFirstByTitle(String title);

    @Query(value = "SELECT * FROM notes WHERE title LIKE %?1%", nativeQuery = true)
    List<Note> searchByTitleContains(String titlePart);
}
