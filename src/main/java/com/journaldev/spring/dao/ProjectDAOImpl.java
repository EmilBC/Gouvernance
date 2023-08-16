package com.journaldev.spring.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Project;

@Repository
public class ProjectDAOImpl implements ProjectDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ProjectDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addProject(Project p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Person saved successfully, Person Details="+p);
	}

	@Override
	public void updateProject(Project p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Person updated successfully, Person Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> listProject() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Project> personsList = session.createQuery("from Project").list();
		for(Project p : personsList){
			logger.info("Person List::"+p);
		}
		return personsList;
	}

	@Override
	public Project getProjectById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Project p = (Project) session.load(Project.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+p);
		return p;
	}

	@Override
	public void removeProject(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Project p = (Project) session.load(Project.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Person deleted successfully, person details="+p);
	}

}
