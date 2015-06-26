package com.gugu.dao.admin;

import java.util.List;
import java.util.Map;

import com.gugu.models.admin.Member;


/**
 * 
 * <br>
 * <b>description：</b>MemberMapper, 用户信息表 基本接口<br>
 * <b>author：</b>Daniel<br>
 * <b>datetime：</b> 2015-06-25 19:34:00 <br>
 * <b>copyright：<b>copyright(C) 2015, gugu151.com<br>
 */
 
public interface MemberMapper {

	public void insert(Member member);

	public void insertMap(Map<String, Object> map);

	public void update(Member member);

	public void updateMap(Map<String, Object> map);

	public void delete(int id);

	public Member getById(int id);

	public int count(Map<String, Object> map);

	public Member getByMap(Map<String, Object> map);

	public List<Member> list(Map<String, Object> map);

}