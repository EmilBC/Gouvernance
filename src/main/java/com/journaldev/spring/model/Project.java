package com.journaldev.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity bean with JPA annotations Hibernate provides JPA implementation
 *
 * @author pankaj
 *
 */
@Entity
@Table(name = "Project")
public class Project {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String git;

    private String projectName;

    private String token;

    private String qGate;

    private String sonarURL;

    private String sonarProjectName;

    private String pdfPath;

    private String gitStats;
    
    private String jenkinsFilePAth;
    
   private String  initOnourSystem;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSonarURL() {
        return sonarURL;
    }

    public void setSonarURL(String sonarURL) {
        this.sonarURL = sonarURL;
    }

    public String getSonarProjectName() {
        return sonarProjectName;
    }

    public void setSonarProjectName(String sonarProjectName) {
        this.sonarProjectName = sonarProjectName;
    }

    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String getGitStats() {
        return gitStats;
    }

    public void setGitStats(String gitStats) {
        this.gitStats = gitStats;
    }
    
    public String getqGate() {
        return qGate;
    }

    public void setqGate(String qGate) {
        this.qGate = qGate;
    }

    public String getJenkinsFilePAth() {
        return jenkinsFilePAth;
    }

    public void setJenkinsFilePAth(String jenkinsFilePAth) {
        this.jenkinsFilePAth = jenkinsFilePAth;
    }

    public String getInitOnourSystem() {
        return initOnourSystem;
    }

    public void setInitOnourSystem(String initOnourSystem) {
        this.initOnourSystem = initOnourSystem;
    }

    
}
