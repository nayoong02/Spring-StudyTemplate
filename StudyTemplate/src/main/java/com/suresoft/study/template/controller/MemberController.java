package com.suresoft.study.template.controller;

import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.suresoft.study.template.common.constant.StudyConstant;
import com.suresoft.study.template.common.enums.ResultCode;
import com.suresoft.study.template.jdbc.service.JdbcMemberService;
import com.suresoft.study.template.jpa.domain.Member;
import com.suresoft.study.template.jpa.service.MemberService;

/**
 * @author Choi Yeon Ho
 */

@Controller
@RequestMapping("/member")
public class MemberController {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	MemberService memberService;
	
	@Autowired
	JdbcMemberService jdbcMemberService;

	/**
	 * GET MEMBER LIST
	 * 
	 * JPA Way
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/memberlist", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJPA(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.memberService.getMemberList();
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * GET MEMBER LIST
	 * 
	 * JPA Way - Vue
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/memberlistVue", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJPA2(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.memberService.getMemberList();
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * PUT MEMBER LIST
	 * 
	 * JPA Way - Vue
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jpa/addMember", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, Object>> addMember(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		this.memberService.addMember();
		//log.info("Member List size - " + memberList.size());

		//HashMap<String, Object> conditionData = new HashMap<String, Object>();
		//conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		//retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	
	/**
	 * GET MEMBER LIST
	 * 
	 * JDBC Way
	 * 
	 * @param principal
	 * @return
	 */
	@RequestMapping(value = "/jdbc/memberlist", method = RequestMethod.GET)
	public ResponseEntity<HashMap<String, Object>> getMemberListByJDBC(Principal principal) {
		HashMap<String, Object> retVal = new HashMap<String, Object>();

		List<Member> memberList = this.jdbcMemberService.getMemberList();		
		log.info("Member List size - " + memberList.size());

		HashMap<String, Object> conditionData = new HashMap<String, Object>();
		conditionData.put("MEMBERLIST", memberList);

		retVal.put(StudyConstant.KEY_STR_RESULT_CODE, ResultCode.OK.name());
		retVal.put(StudyConstant.KEY_STR_RESULT_DATA, conditionData);
		retVal.put(StudyConstant.KEY_STR_RESULT_MESSAGE, "Data load success");
		return new ResponseEntity<HashMap<String, Object>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * REGISTER
	 * 
	 * JDBC Way
	 * 
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/jdbc/register", method = RequestMethod.POST)
	public boolean registerByJDBC(
			@RequestParam("userId") String userId,
			@RequestParam("password") String password,
			@RequestParam("userName") String userName,
			@RequestParam("department") String department,
			@RequestParam("position") String position
			) {
		
		boolean resultMessage = false;
		
		resultMessage = this.jdbcMemberService.register(userId, password, userName, department, position);
		
		return resultMessage;
	}
	
	
	//회원 가입 시 아이디 중복 체크
	@ResponseBody
	@RequestMapping(value = "/jdbc/idCheck", method = RequestMethod.GET)
	public int idCheck(@RequestParam("userId") String userId) throws SQLException {
		int cnt = this.jdbcMemberService.idCheck(userId);
		return cnt;
	}
	
}
