package com.enote.repository;

import com.enote.entity.Notes;
import com.enote.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Notes, Integer> {

    public Page<Notes> findByUser(User user , Pageable pageable);
}
