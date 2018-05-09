package pl.stanikov.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.stanikov.model.Book;

import javax.persistence.*;


@Repository
@Transactional
public class BookDaoImp implements BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    BookDaoImp() {
    }

    @Override
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book get(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }
}
