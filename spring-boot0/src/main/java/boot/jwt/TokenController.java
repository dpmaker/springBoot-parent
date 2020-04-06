package boot.jwt;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private JwtConfig jwtConfig;
	
	
	
	@RequestMapping("/login")
	@ResponseBody
	public Object login(@RequestParam("userId") String userId){
       Map json=new HashMap();
       jwtConfig.getSecret();
        String token=jwtConfig.createToken(userId);
        json.put("token", token);
        
        return json;
	}
	
	
	
	@RequestMapping("/test")
	@ResponseBody
	
	public Object testToken(){
		return "SUCCESS";
	}
	
}
