package com.hanope.readingList;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by heemanghan on 17/05/2017.
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
