package com.journaldev.spring.dao;

import java.util.List;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Project;

public interface ProjectDAO {

	public void addProject(Project p);
	public void updateProject(Project p);
	public List<Project> listProject();
	public Project getProjectById(int id);
	public void removeProject(int id);
}
