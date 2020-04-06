package boot.redis;

import org.bouncycastle.jcajce.provider.asymmetric.dsa.DSASigner.noneDSA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bean.User;

@Controller
@RequestMapping("redis/annotion")
public class RedisAnnotationController {

	
	@Autowired
	private RedisUserDao redisUserDao;
	
	@RequestMapping("find")
	@ResponseBody
	@Cacheable(value="user")
	public Object findUser(){
		User u=new User();
		u.setName("tom2");
		return u;
	}
	
	@RequestMapping("selectUserById")
	@ResponseBody
	@Cacheable(value="user")
	public Object selectUserById(@RequestParam String id){
		return this.redisUserDao.selectUserById(id);
	}
	
	
	@RequestMapping("/insert")
	@ResponseBody
	@CachePut(value="user",key="#p0")
	public Object insert(@RequestParam String id){
		User u=new User();
		u.setName(id);
		u.setId(Integer.valueOf(id));
		 this.redisUserDao.insert(u);
		return u;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	@CachePut(value="user",key="#p0")
	public Object update(@RequestParam String id,@RequestParam String name){
		User u=new User();
		u.setName(name);
		u.setId(Integer.valueOf(id));
		 this.redisUserDao.update(u);
		return u;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	@CacheEvict(value="user",key="#p0")
	public Object delete(@RequestParam String id){
		User u=new User();
		u.setName(id);
		u.setId(Integer.valueOf(id));
		 this.redisUserDao.delete(u);
		return u;
	}
}
