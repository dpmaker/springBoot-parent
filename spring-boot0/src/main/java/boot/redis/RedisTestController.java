package boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.Book;
import boot.jwt.ExcludeJwtAnnotion;

@Controller
@RequestMapping("redis")
public class RedisTestController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	
	
	@Autowired
	private RedisTemplate<String, Object> objRedisTemplate;	
	
	
	@RequestMapping("/test1")
	@ResponseBody
	@ExcludeJwtAnnotion
	public Object test1(@RequestParam String userId){
		this.stringRedisTemplate.opsForValue().set("userId", userId);
		return this.stringRedisTemplate.opsForValue().get("userId");
	}
	
	
	@RequestMapping("/test2")
	@ResponseBody
	@ExcludeJwtAnnotion
	public Object test2(){
		String key="list2";
        ListOperations<String, String> listOperations = stringRedisTemplate.opsForList();
        listOperations.leftPush(key,"ddd");
		return listOperations.range(key, 0, 10);
	}
	
	@RequestMapping("/test4Map")
	@ResponseBody
	@ExcludeJwtAnnotion
	public Object test4Map(){
		String key = "book2";
        HashOperations<String, String, Book> hashOperations = objRedisTemplate.opsForHash();
		
        Book b=new Book();
        b.setBookName("name1");
        hashOperations.put(key, "book1", b);
         
        Book b2=new Book();
        b2.setBookName("name2");
        hashOperations.put(key, "book2", b2);
		return hashOperations.get("book", "book1");
	}
}
