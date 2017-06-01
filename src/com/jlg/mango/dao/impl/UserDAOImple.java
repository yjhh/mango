package com.jlg.mango.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.jlg.mango.dao.DBlink;
import com.jlg.mango.dao.UserDao;
import com.jlg.mango.entity.User;

public class UserDAOImple implements UserDao {
	// 判别sentence类型格式 (昵称0 邮箱1 手机号2)
	public int strType(String sentence) {
		String em = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";// 邮箱
		String ph = "^[1][3578]\\d{9}$";// 电话

		if (sentence.matches(em)) {
			return 0;// 邮箱
		} else if (sentence.matches(ph)) {
			return 1;// 电话
		}
		return 2;// 昵称
	}

	/**
	 * 用户登录
	 */
	@Override
	public User userLogin(String sentence, String password) {
		int type = strType(sentence);
		User user = null;
		String sql = "select * from user where 1=1 ";
		switch (type) {
		case 0:
			sql += "and email ='" + sentence + "'";
			break;
		case 1:
			sql += "and telNum = '" + sentence + "'";
			break;
		case 2:
			sql += "and loginName='" + sentence + "'";
			break;
		}
		sql += " and password = md5('" + password + "') ";

		// System.out.println(sql);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("loginName"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setIcon(rs.getString("icon"));
				user.setInfo(rs.getString("info"));
				user.setSex(rs.getString("sex"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setTelNum(rs.getString("telNum"));
				user.setRegister_time(rs.getDate("register_time"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		// System.out.println(user);
		return user;
	}

	// 用户注册
	@Override
	public int userRegister(User user) {
		String sql = "insert into user(loginName,name,password,sex,email,telNum,address,register_time,icon,status,info) "
				+ "values('" + user.getLoginName() + "', '" + user.getName() + "' , md5('" + user.getPassword()
				+ "' ), '" + user.getSex() + "' , '" + user.getEmail() + "' , '" + user.getTelNum() + "' , '"
				+ user.getAddress() + "' , '" + user.getRegister_time() + "' , '" + user.getIcon() + "' , '"
				+ user.getStatus() + "' , '" + user.getInfo() + "') ";
		/* System.out.println(sql); */
		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}

	@Override
	public boolean isLogNameExist(String logName) {
		String sql = "select * from user where loginName = " + logName;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return false;
	}

	@Override
	public boolean isTelNumExist(String telNum) {
		String sql = "select * from user where telNum = " + telNum;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return false;
	}

	@Override
	public boolean isEmailExist(String email) {
		String sql = "select * from user where email = " + email;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return false;
	}

	@Override
	public int editUserInfo(User user) {
		String sql = "update user  set  loginName =?, " + "name=?, password=?,sex=?,email=?,telNum=?,"
				+ "address=?,icon=?,info=? where id=?";
		Connection conn = null;
		int count = 0;
		try {
			conn = DBlink.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, user.getLoginName());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setString(4, user.getSex());
			ps.setString(5, user.getEmail());
			ps.setString(6, user.getTelNum());
			ps.setString(7, user.getAddress());
			ps.setString(8, user.getIcon());
			ps.setString(9, user.getInfo());
			ps.setInt(10, user.getId());

			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}

	// 查询用户信息根据id
	public User searchUserById(int id) {
		String sql = "select * from user where id =" + id;
		User user = new User();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {

				user.setId(rs.getInt("id"));
				user.setLoginName(rs.getString("loginName"));
				user.setAddress(rs.getString("address"));
				user.setEmail(rs.getString("email"));
				user.setIcon(rs.getString("icon"));
				user.setInfo(rs.getString("info"));
				user.setSex(rs.getString("sex"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setStatus(rs.getInt("status"));
				user.setTelNum(rs.getString("telNum"));
				user.setRegister_time(rs.getDate("register_time"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return user;
	}

	@Override
	public List<User> searchUserByName(String name) {
		String sql = "select * from user where name like ?";
		List<User> users = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("loginName"), rs.getString("name"),
						rs.getString("password"), rs.getString("sex"), rs.getString("email"), rs.getString("telNum"),
						rs.getString("address"), rs.getDate("register_time"), rs.getString("icon"), rs.getInt("status"),
						rs.getString("info")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}

		return users;
	}

	@Override
	public List<User> searchUserByStatus(int status) {
		String sql = "select * from user where status=?";
		List<User> users = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBlink.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, status);
			rs = ps.executeQuery();
			while (rs.next()) {

				users.add(new User(rs.getInt("id"), rs.getString("loginName"), rs.getString("name"),
						rs.getString("password"), rs.getString("sex"), rs.getString("email"), rs.getString("telNum"),
						rs.getString("address"), rs.getDate("register_time"), rs.getString("icon"), rs.getInt("status"),
						rs.getString("info")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return users;
	}

	// 添加收货地址
	public int addAdress(String conName, String address, String phone, int i) {
		String sql = "insert into `receiveinfo`(name,address,telNum,user_id) values(?,?,?,?)";
		Connection conn = null;
		int count = 0;
		try {
			conn = DBlink.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, conName);
			ps.setString(2, address);
			ps.setString(3, phone);
			ps.setInt(4, i);

			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}

	// 修改用户信息
	public int changeUserInfo(String loginName, String sex,String name, String bornAdress, String info, String phone, int id) {
		String sql = "";
		if(bornAdress == null || bornAdress.equals("null")){
			sql = "update user set loginName='" + loginName + "',name='"+name+"',sex='" + sex + "'," 
					+ "telNum='" + phone + "',info='" + info + "' where id=" + id + ";";
		}else{
			sql = "update user set loginName='" + loginName + "',"+"name='"+name+"'," + "sex='" + sex + "',address='" + bornAdress
					+ "',telNum='" + phone + "',info='" + info + "' where id=" + id + ";";
		}
		int count = 0;
		Connection conn = null;
		try {
			conn = DBlink.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBlink.closeConnection(conn);
		}
		return count;
	}

}
