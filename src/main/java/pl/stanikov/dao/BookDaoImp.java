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
    @Transactional
    public void save(Book book) {
        entityManager.persist(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book get(Long id) {
        Book book = entityManager.find(Book.class, id);
        return book;
    }

    @Override
    @Transactional
    public void update(Book book) {
        //entityManager.merge(book);
        Book find = entityManager.find(Book.class, book.getId());
        if(find != null) {
            find.setTitle(book.getTitle());
            find.setIsbn(book.getIsbn());
            find.setAuthor(book.getAuthor());
        }
    }

    @Override
    @Transactional
    public void remove(Long bookId) {
        Book objToRemove = entityManager.find(Book.class, bookId);
        entityManager.remove(objToRemove);
        System.out.println("DELETE: " + objToRemove.getId());
    }

    @PrePersist
    public void prePersist() {
        System.out.println("Zapis obiektu " + this);
    }

    @PostPersist
    public void postPersist() {
        System.out.println("Zapisano obiekt " + this);
    }
}
