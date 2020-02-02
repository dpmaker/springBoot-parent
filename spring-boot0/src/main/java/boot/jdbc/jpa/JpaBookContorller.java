package boot.jdbc.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

@RestController
@RequestMapping("/jpa")
@SpringBootApplication
public class JpaBookContorller {

	@Autowired
	private IBookRepository iBookRepository;
	
	@ResponseBody
	@RequestMapping("/findAll")
	public List<BookEntity> findAll(){
		return iBookRepository.findAll();
	}
}
