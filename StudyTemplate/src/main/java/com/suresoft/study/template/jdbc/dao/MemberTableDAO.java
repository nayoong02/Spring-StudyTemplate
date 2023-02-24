package com.suresoft.study.template.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.suresoft.study.template.jpa.domain.Member;

public class MemberTableDAO {
	private static Logger log = LoggerFactory.getLogger("MemberControllerLog");

	Connection connection;

	/**
	 * 
	 * @param connection
	 */
	public MemberTableDAO(Connection connection) {
		this.connection = connection;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Member> selectAllMemberList() throws SQLException {
		List<Member> retVal = new ArrayList<Member>();

		StringBuffer query = new StringBuffer();
		query.append("  SELECT  	\n");
		query.append("        m.id, 	\n");
		query.append("        m.auth, 	\n");
		query.append("        m.department, 	\n");
		query.append("        m.name, 	\n");
		query.append("        m.position, 	\n");
		query.append("        m.pw, 	\n");
		query.append("        m.rank, 	\n");
		query.append("        m.user_id, 	\n");
		query.append("        m.ismaster 	\n");
		query.append("    FROM member m	\n");

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(query.toString());
			rs = ps.executeQuery();
			log.info(query.toString());

			while (rs.next()) {
				Member member = new Member();

				member.setId(rs.getString("id"));
				member.setAuth(rs.getString("auth"));
				member.setDepartment(rs.getString("department"));
				member.setName(rs.getString("name"));
				member.setPosition(rs.getString("position"));
				member.setPW(rs.getString("pw"));
				member.setRank(rs.getString("rank"));
				member.setuserId(rs.getString("user_id"));
				member.setIsmaster(rs.getInt("ismaster"));

				retVal.add(member);
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}

		return retVal;
	}
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Member selectMemberByUserId(String userId) throws SQLException {
		Member retVal = new Member();

		StringBuffer query = new StringBuffer();
		query.append("  SELECT  	\n");
		query.append("        m.id, 	\n");
		query.append("        m.auth, 	\n");
		query.append("        m.department, 	\n");
		query.append("        m.name, 	\n");
		query.append("        m.position, 	\n");
		query.append("        m.pw, 	\n");
		query.append("        m.rank, 	\n");
		query.append("        m.user_id, 	\n");
		query.append("        m.ismaster 	\n");
		query.append("    FROM member m	\n");
		
		if (userId != null) {
			query.append("   WHERE m.user_id = ?	\n");			
		}

		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			ps = connection.prepareStatement(query.toString());
			if (userId != null) {
				ps.setString(1, userId);				
			}
			
			rs = ps.executeQuery();
			log.info(query.toString());

			while (rs.next()) {
				Member member = new Member();

				member.setId(rs.getString("id"));
				member.setAuth(rs.getString("auth"));
				member.setDepartment(rs.getString("department"));
				member.setName(rs.getString("name"));
				member.setPosition(rs.getString("position"));
				member.setPW(rs.getString("pw"));
				member.setRank(rs.getString("rank"));
				member.setuserId(rs.getString("user_id"));
				member.setIsmaster(rs.getInt("ismaster"));

				retVal = member;
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}

		return retVal;
	}

	public boolean insertMember(String id, String userId, String password, String userName, String department, String position) throws SQLException {
		boolean resultMessage = false;

		StringBuffer query = new StringBuffer();
		query.append("  insert into 			\n");
		query.append("  member (		 		\n");
		query.append("  		id, 			\n");
		query.append("  		user_id, 		\n");
		query.append("          name, 			\n");
		query.append("  		pw, 			\n");
		query.append("  		auth, 			\n");
		query.append("   		department,		\n");
		query.append("  		position,		\n");
		query.append("			ismaster		\n");
		query.append("  		)				\n");
		query.append("  values (?, ?, ?, ?, ?, 	\n");
		query.append("  		?, ?, ?			\n");
		query.append("  		)				\n");
		
		PreparedStatement ps = null;
		
		try {
			ps = connection.prepareStatement(query.toString());
			
			ps.setString(1, id);
			ps.setString(2, userId);
			ps.setString(3, userName);
			ps.setString(4, password);
			ps.setString(5, "ROLE_NORMAL");
			ps.setString(6, department);
			ps.setString(7, position);
			ps.setInt(8, 2);
			
			int rs = ps.executeUpdate();
			ps.close();
			resultMessage = true;

		} catch (SQLException e) {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (ps != null) {
				ps.close();
			}
		}

		return resultMessage;
	}
	 
	public int idCheck(String userId) throws SQLException {
		StringBuffer query = new StringBuffer();
		int count = 0;
		
		query.append("  SELECT COUNT(user_id) as cnt	\n");
		query.append("    	FROM member      	        \n");
		query.append("     WHERE user_id = ?			\n");
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = connection.prepareStatement(query.toString());
			ps.setString(1, userId);
			
			rs = ps.executeQuery();
			
			while (rs.next()) {
				count = rs.getInt("cnt");
			}
			
			rs.close();
			ps.close();

		} catch (SQLException e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		}
		return count;
	}
		
	
	/**
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

}
