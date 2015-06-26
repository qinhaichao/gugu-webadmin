package com.gugu.service.admin;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gugu.dao.admin.MemberMapper;
import com.gugu.models.admin.Member;

/**
 * 
 * <br>
 * <b>description：</b>MemberServiceImpl, MemberService 基本接口实现<br>
 * <b>author：</b>Daniel<br>
 * <b>datetime：</b> 2015-06-25 19:34:00 <br>
 * <b>copyright：<b>copyright(C) 2015, gugu151.com<br>
 */
 

@Service
public class MemberServiceImpl implements MemberService {

	Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private MemberMapper memberDao;

	@Override
	public void insert(Member member) {
		if(null!=member){
			memberDao.insert(member);
		}
		log.info("MemberServiceImpl:insert()"+member.toString());
	}

	@Override
	public void insertMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			memberDao.insertMap(map);
		}
		log.info("MemberServiceImpl:insertMap()"+map.toString());
	}

	@Override
	public void update(Member member) {
		if(null!=member){
			memberDao.update(member);
		}
		log.info("MemberServiceImpl:update()"+member.toString());
	}

	@Override
	public void updateMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			memberDao.updateMap(map);
		}
		log.info("MemberServiceImpl:updateMap()"+map.toString());
	}

	@Override
	public void delete(int id) {
		memberDao.delete(id);
		log.info("MemberServiceImpl:delete()"+id);
	}

	@Override
	public int count(Map<String, Object> map) {
		log.info("MemberServiceImpl:count()"+map.toString());
		return memberDao.count(map);
	}

	@Override
	public Member getById(int id) {
		log.info("MemberServiceImpl:getById()"+id);
		return memberDao.getById(id);
	}

	@Override
	public Member getByMap(Map<String, Object> map) {
		if(!map.isEmpty()){
			log.info("MemberServiceImpl:getByMap()"+map.toString());
			return memberDao.getByMap(map);
		}else{
			log.info("MemberServiceImpl:getByMap(),map key-value is null!");
			return null;
		}
	}

	@Override
	public List<Member> list(Map<String, Object> map) {
		log.info("MemberServiceImpl:list()"+map.toString());
		return memberDao.list(map);
	}

}
