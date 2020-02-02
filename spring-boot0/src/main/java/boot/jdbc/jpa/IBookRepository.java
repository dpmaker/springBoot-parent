package boot.jdbc.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<BookEntity, Long>{
	
	List<BookEntity> findAll();
	
	List<BookEntity> findByBookName(String name);
}
