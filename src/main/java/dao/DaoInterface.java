package dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.springmvc.Entity.FacebookEntity;

public interface DaoInterface {
	public int createProfile(FacebookEntity fu) throws Exception;
	public String viewProfile(FacebookEntity fu) throws Exception;
	public int login(FacebookEntity fu,HttpServletRequest request) throws Exception; 
}
