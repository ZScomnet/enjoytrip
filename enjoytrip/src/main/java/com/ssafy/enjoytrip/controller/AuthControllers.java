package com.ssafy.enjoytrip.controller;



import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

//import javax.servlet.http.HttpSession;

import com.ssafy.enjoytrip.dto.SignInDto;
import com.ssafy.enjoytrip.dto.SignUpDto;
import com.ssafy.enjoytrip.model.User;
import com.ssafy.enjoytrip.service.MemberService;
import com.ssafy.enjoytrip.service.UserService;
import com.ssafy.enjoytrip.util.JwtokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthControllers {

//	@Autowired
	private final UserService userService;
	private final MemberService memberService;

//	@Autowired
	private final JwtokenUtil jwtUtil;

	//로그인 요청 처리 - POST /user/login
	@PostMapping("/login")
	public ResponseEntity<?> doLogin( @RequestBody User user) throws UnsupportedEncodingException{
		//유저 정보 조회
		Long userId = userService.login(user);
		SignInDto signInDto = new SignInDto(user.getUser_id(),user.getEmail(),"",user.getPassword());
		if(user.getEmail().equals("admin@admin.com")) signInDto.setUsername("admin");
		else{
			String username = memberService.getUserName(userId);
			signInDto.setUsername(username);
		}
		signInDto.setUser_id(userId);
		//로그인 성공
		if(userId!=null) {

			String token = jwtUtil.createToken(signInDto, 60*60 );	//60초 * 60분

			Map<String, String> result = new HashMap<>();
			result.put("token", token);

			if(signInDto.getUsername().equals("admin")){
				result.put("state", "A");

			}else{
				result.put("state", "M");
			}

			return new ResponseEntity<Map<String, String>>(result, HttpStatus.OK);

			/*
			 *
			 * {
			 * 	"token":"12311445252112326fgdfgfd gdsf"
			 * }
			 */
		}
		//로그인 실패
		else return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/signup")
	public void signup(@RequestBody SignUpDto signUpDto ){
		userService.signupUser(signUpDto);
	}


}
