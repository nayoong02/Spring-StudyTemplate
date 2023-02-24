package com.suresoft.study.template.jdbc.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.suresoft.study.template.jdbc.dao.MemberTableDAO;
import com.suresoft.study.template.jpa.domain.Member;

/**
 * @author Choi Yeon Ho
 */

@Service
public class JdbcMemberService {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	private JdbcTemplate jdbc;

	/**
	 * 
	 * @return
	 */
	public List<Member> getMemberList() {
		List<Member> retVal = new ArrayList<Member>();

		MemberTableDAO memberTableDAO = null;
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());

			retVal = memberTableDAO.selectAllMemberList();

			// DB Close
			memberTableDAO.closeConnection();

		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		
		return retVal;
	}
	
	public Member getMemberByUserId(String userId) {
		Member retVal = new Member();
		
		MemberTableDAO memberTableDAO = null;
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());

			retVal = memberTableDAO.selectMemberByUserId(userId);

			// DB Close
			memberTableDAO.closeConnection();

		} catch (SQLException e) {
			log.error("SQLException", e);
		}
		
		return retVal;
	}
	
	/** 회원가입 **/
	public boolean register(String userId, String password, String userName, String department, String position) {
		
//		Member retVal = new Member();
		boolean resultMessage = false;
		
		MemberTableDAO memberTableDAO = null;
		try {
			String id = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
			
			//비밀번호 암호화
			BCryptPasswordEncoder bcryptPwEncoder = new BCryptPasswordEncoder();
			String encodedPW = bcryptPwEncoder.encode(password);
			
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());
			
			resultMessage = memberTableDAO.insertMember(id, userId, encodedPW, userName, department, position);
					
			// DB Close
			memberTableDAO.closeConnection();
			
		} catch(SQLException e) {
			log.error("SQLException", e);
		}
		return resultMessage;
		
	}

	public int idCheck(String userId) {
		MemberTableDAO memberTableDAO = null;
		int cnt = 0;
		
		try {
			memberTableDAO = new MemberTableDAO(jdbc.getDataSource().getConnection());
			
			cnt = memberTableDAO.idCheck(userId);
			
			// DB Close
			memberTableDAO.closeConnection();
			
		} catch(SQLException e) {
			log.error("SQLException", e);
		}
		
		return cnt;
	}
}
