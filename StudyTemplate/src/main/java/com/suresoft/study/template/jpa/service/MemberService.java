package com.suresoft.study.template.jpa.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresoft.study.template.jpa.domain.Member;
import com.suresoft.study.template.jpa.repository.MemberRepository;

/**
 * @author Choi Yeon Ho
 */

@Service
public class MemberService {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	@Autowired
	MemberRepository memberRepository;

	/**
	 * 
	 * @return
	 */
	public List<Member> getMemberList() {
		log.info("Select Member List by JPA Way.");
		return this.memberRepository.findAll();
	}
	
	/**
	 * 
	 * @param userId
	 * @return
	 */
	public Member getMemberByUserId(String userId) {
		log.info("Select Member by User Id Parameter.");
		return this.memberRepository.findOneByUserId(userId);
	}
	
	
	public void addMember() {
		
		Member member = new Member();
		
		member.setId("nayoungKim");
		member.setuserId("nayoung");
		member.setName("김나영");
		member.setPW("020101");
		member.setAuth("ROLE_NORMAL");
		
		member.setDepartment("VPES");
		member.setIsmaster(2);
		member.setPosition("Suresoft");
		member.setRank("Normal");
	
		memberRepository.saveAndFlush(member);
		System.out.println(member.getId());
		
		
	}

}
