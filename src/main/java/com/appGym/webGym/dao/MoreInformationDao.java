package com.appGym.webGym.dao;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.appGym.webGym.entities.Membership;
import com.appGym.webGym.entities.MoreInformation;
import com.appGym.webGym.entities.User;

public interface MoreInformationDao {
	@CommitAfter
	public boolean save(MoreInformation newMoreInformation);

	@CommitAfter
	public boolean delete(MoreInformation newMoreInformation);

	@CommitAfter
	public boolean update(MoreInformation newMoreInformation);

	public List<MoreInformation> findAll();


	public MoreInformation findByID(Long id);

	@CommitAfter
	public boolean deleteByID(Long id);
	
	public MoreInformation findByUser(Long id);
}
