package com.jlg.mango.dao;

import java.util.List;

import com.jlg.mango.entity.ReceiveInfo;


/**
 * 收货信息
 * @author xiaolili
 *
 */
public interface ReceiveInfoDAO {
	/**
	 * 根据用户编号查找收货信息
	 */
	List<ReceiveInfo> searchInfoByUser_id(int user_id);
}
