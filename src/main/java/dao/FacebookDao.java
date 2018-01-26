package dao;

import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.springmvc.Entity.FacebookEntity;


public class FacebookDao implements DaoInterface {
	
	EntityManagerFactory emf;
	EntityManager em;
	EntityTransaction et;
	
	
	public FacebookDao(){
		
	
		emf = Persistence.createEntityManagerFactory("ajaxtest");
		
		em= emf.createEntityManager();
		et=em.getTransaction();
		
	}
	public int createProfile(FacebookEntity fu) throws Exception{
		int i = 0;
		et.begin();
		em.persist(fu);
		et.commit();
		i=1;
		/*Connection con =DaoInterface.getConnection();
		
		PreparedStatement ps = con.prepareStatement("insert into itc_employee values(?,?,?,?)");
		
		ps.setString(1, fu.getName());
		ps.setString(2, fu.getPass());
		ps.setString(3, fu.getEmail());
		ps.setString(4, fu.getAddr());
		i = ps.executeUpdate();
		con.close();*/
		return i;
	}
	
	public String viewProfile(FacebookEntity fu) {
		Query q=em.createQuery("from com.springmvc.Entity.FacebookEntity e where e.email=:mail");
		q.setParameter("mail", fu.getEmail());
		List<FacebookEntity> fl=q.getResultList();
		Gson gson = new Gson();
		String jsonData = gson.toJson(fl);
		return jsonData;
		
		//System.out.println(fu.getEmail());
		/*FacebookEntity student1 = em.find(FacebookEntity.class, fu.getEmail());
		if (null != student1) {
		System.out.println("Retrived object .. " + student1);
		i=1;
		} else {
		System.out.println("No object found...");
		}
		*/
	}
	public int login(FacebookEntity fu,HttpServletRequest request) {
		int i=0;
		HttpSession s=request.getSession();
		Query q=em.createQuery("from com.springmvc.Entity.FacebookEntity e where e.email=:mail and e.password=:pass");
		q.setParameter("mail", fu.getEmail());
		q.setParameter("pass", fu.getPassword());
		List<FacebookEntity> fl=q.getResultList();
		for(FacebookEntity fe:fl) {
			s.setAttribute("nm", fe.getName());
			s.setAttribute("em", fe.getEmail());
		}
		if(fl!=null)
			i=1;
		return i;
	}
}
