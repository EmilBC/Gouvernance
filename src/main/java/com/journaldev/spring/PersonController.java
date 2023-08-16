package com.journaldev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.journaldev.spring.model.Person;
import com.journaldev.spring.model.Project;
import com.journaldev.spring.service.PersonService;
import com.journaldev.spring.service.ProjectService;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired(required = true)
    @Qualifier(value = "personService")
    public void setPersonService(PersonService ps) {
        this.personService = ps;
    }

    private ProjectService projectService;

    @Autowired(required = true)
    @Qualifier(value = "projectService")
    public void setProjectService(ProjectService prjs) {
        this.projectService = prjs;
    }

    @RequestMapping(value = "/projects", method = RequestMethod.GET)
    public String listPersons(Model model) {
        //  model.addAttribute("person", new Person());
        // model.addAttribute("project", new Project());

        model.addAttribute("listPersons", this.projectService.listProject());
        return "sample-page";
    }

    @RequestMapping(value = "/addProjectOnOurSystem", method = RequestMethod.GET)
    public String addPOOS(Model model) {
        //  model.addAttribute("person", new Person());
        model.addAttribute("project", new Project());
        //model.addAttribute("listPersons", this.projectService.listProject());
        return "person";
    }

    @RequestMapping(value = "/giveStatsToOurSystem", method = RequestMethod.GET)
    public String giveSTS(Model model) {
        //  model.addAttribute("person", new Person());
        model.addAttribute("project", new Project());
        //model.addAttribute("listPersons", this.projectService.listProject());
        return "index";
    }

    @RequestMapping(value = "/operations", method = RequestMethod.GET)
    public String operations(Model model) {
        //  model.addAttribute("person", new Person());
        model.addAttribute("projectDetail", new Project());
        //model.addAttribute("listPersons", this.projectService.listProject());
        return "ui-buttons";
    }

    @RequestMapping(value = "/runSonarReport", method = RequestMethod.GET)
    public String runSonarReport(@RequestParam String projectId) {
        Project p = projectService.getProjectById(Integer.valueOf(projectId));
        Process process;

        try {
            process = Runtime.getRuntime()
                    .exec("java -jar C:\\Users\\Administrateur\\Desktop\\InitProject\\sonar-cnes-report-master\\sonar-cnes-report-master\\ReportGen\\target\\cnesreport-4.2.0.jar -t squ_ca41189bfb66b95acbb530f681dbf46ea6bc419a -s http://10.12.1.254:9000 -p " + p.getProjectName() + " -r ./template1.docx");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Path source = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\output.pdf");
        Path target = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\output" + p.getId() + ".pdf");

        try {

            Files.move(source, target);

        } catch (IOException e) {
            e.printStackTrace();
        }
        p.setPdfPath("output" + p.getId() + ".pdf");
        p.setInitOnourSystem("4");
        projectService.updateProject(p);
        return "redirect:/projects";

    }

    @RequestMapping(value = "/runSonarReportGen", method = RequestMethod.GET)
    public String runSonarReportGen(@RequestParam String projectId) {
        Project p = projectService.getProjectById(Integer.valueOf(projectId));
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080/ -auth ebouchebel:Salma_2201EBC build " + p.getProjectName());
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }

        p.setInitOnourSystem("3");
        projectService.updateProject(p);

        return "ui-card";

    }

    @RequestMapping(value = "/redirectToPage", method = RequestMethod.GET)
    public String redirectToPage(@RequestParam String pageName) {
        //model.addAttribute("person", new Person());
        //model.addAttribute("listPersons", this.personService.listPersons());
        /*   Process process;

        try {
            process = Runtime.getRuntime()
                    .exec("java -jar C:\\Users\\Administrateur\\Desktop\\InitProject\\sonar-cnes-report-master\\sonar-cnes-report-master\\ReportGen\\target\\sonar-cnes-report-4.2.0.jar -t squ_ca41189bfb66b95acbb530f681dbf46ea6bc419a -s http://10.12.1.254:9000 -p testoutsidegit -r ./template1.docx");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        if (pageName.equals("ui-bottons")) {

        }
        return pageName;
    }

    //For add and update person both
    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute("person") Person p) {

        if (p.getId() == 0) {
            //new person, add it
            this.personService.addPerson(p);
        } else {
            //existing person, call update
            this.personService.updatePerson(p);
        }

        return "redirect:/persons";

    }

    @RequestMapping(value = "/project/runTomcatWithNexus", method = RequestMethod.POST)
    public String runTomcatWithNexus(@ModelAttribute("project") Project p) throws IOException {
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080/ -auth ebouchebel:Salma_2201EBC build downloadandrun");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/operations";
    }

    @RequestMapping(value = "/project/finalCall", method = RequestMethod.POST)
    public String finalCall(@ModelAttribute("project") Project p) throws IOException {
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080/ -auth ebouchebel:Salma_2201EBC build testGlobal");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/operations";
    }

    @RequestMapping(value = "/project/sqlFile", method = RequestMethod.POST)
    public String sqlFile(@ModelAttribute("project") Project p) throws IOException {
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080/ -auth ebouchebel:Salma_2201EBC build warnexus");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/operations";
    }

    @RequestMapping(value = "/project/runTomcat", method = RequestMethod.POST)
    public String tryRunTomcat(@ModelAttribute("project") Project p) throws IOException {
        Process process;
        try {
            process = Runtime.getRuntime()
                    .exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080/ -auth ebouchebel:Salma_2201EBC build DeployWar");
            System.out.println("no errors------------------emil");

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException ex) {
            System.out.println("------------result" + ex.toString());
            Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/operations";
    }

    @RequestMapping(value = "/project/init", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") Project p) throws IOException {

        if (p.getSonarURL() != null && !p.getSonarURL().isEmpty()) {
            Process process;

            try {
                process = Runtime.getRuntime()
                        .exec("java -jar C:\\Users\\Administrateur\\Desktop\\InitProject\\sonar-cnes-report-master\\sonar-cnes-report-master\\ReportGen\\target\\sonar-cnes-report-4.2.0.jar -t " + p.getToken() + " -s " + p.getSonarURL() + " -p " + p.getSonarProjectName() + " -r ./template1.docx -z ");
                System.out.println("no errors------------------emil");

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
                System.out.println("Here is the standard output of the command:\n");
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }

// Read any errors from the attempted command
                System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException ex) {
                System.out.println("------------result" + ex.toString());
                Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Path source = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\output.pdf");
            Path target = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\output" + p.getId() + ".pdf");

            try {

                Files.move(source, target);

            } catch (IOException e) {
                e.printStackTrace();
            }
            p.setProjectName(p.getSonarProjectName());
            p.setPdfPath("output" + p.getId() + ".pdf");
            p.setInitOnourSystem("1");
            projectService.addProject(p);
            return "redirect:/projects";
        } else {
            Path pathSource = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\sampleJob.xml");
            Path pathDestination = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\" + p.getProjectName() + ".xml");
            Files.copy(pathSource, pathDestination);
            File textFile = new File("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\" + p.getProjectName() + ".xml");
            Charset charset = StandardCharsets.UTF_8;
            String content = IOUtils.toString(new FileInputStream(textFile), charset);
            content = content.replaceAll("https://github.com/EmilBC/ReportGen", p.getGit());

            IOUtils.write(content, new FileOutputStream(textFile), charset);
            pathDestination = Paths.get("C:\\Users\\Administrateur\\Desktop\\Serveur\\apache-tomcat-9.0.78\\apache-tomcat-9.0.78\\bin\\" + p.getProjectName() + ".bat");
            pathSource = Paths.get("C:\\Users\\Administrateur\\Desktop\\Serveur\\apache-tomcat-9.0.78\\apache-tomcat-9.0.78\\bin\\toexecute.bat");
            Files.copy(pathSource, pathDestination);
            textFile = new File("C:\\Users\\Administrateur\\Desktop\\Serveur\\apache-tomcat-9.0.78\\apache-tomcat-9.0.78\\bin\\" + p.getProjectName() + ".bat");
            charset = StandardCharsets.UTF_8;
            content = IOUtils.toString(new FileInputStream(textFile), charset);
            content = content.replaceAll("hellohallo123", p.getProjectName());
            content = content.replaceAll("file.xml", "C:/Users/Administrateur/Desktop/InitProject/ProjetBase/SpringMVCHibernate/target/SpringMVCHibernate/assets/" + p.getProjectName() + ".xml");
            FileOutputStream fos = new FileOutputStream(textFile);
            IOUtils.write(content, fos, charset);
            fos.close();
            Process process;

            try {

                process = Runtime.getRuntime().exec(p.getProjectName() + ".bat");
                //.exec("java -jar jenkins-cli.jar -s http://10.12.1.254:8080 -auth ebouchebel:Salma_2201EBC create-job " + p.getProjectName() + " < sampleJob.xml");
                System.out.println("no errors------------------emil");

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

                BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

// Read the output from the command
                System.out.println("Here is the standard output of the command:\n");
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }

// Read any errors from the attempted command
                System.out.println("Here is the standard error of the command (if any):\n");
                while ((s = stdError.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException ex) {
                System.out.println("------------result" + ex.toString());
                Logger.getLogger(PersonController.class.getName()).log(Level.SEVERE, null, ex);
            }

            pathDestination = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\jenkinsfile");
            pathSource = Paths.get("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\" + p.getProjectName() + "jenkinsfile");
            Files.copy(pathDestination, pathSource);
            textFile = new File("C:\\Users\\Administrateur\\Desktop\\InitProject\\ProjetBase\\SpringMVCHibernate\\target\\SpringMVCHibernate\\assets\\" + p.getProjectName() + "jenkinsfile");
            charset = StandardCharsets.UTF_8;
            content = IOUtils.toString(new FileInputStream(textFile), charset);
            content = content.replaceAll("projectABC", p.getProjectName());
            //content = content.replaceAll("file.xml", "C:/Users/Administrateur/Desktop/InitProject/ProjetBase/SpringMVCHibernate/target/SpringMVCHibernate/assets/" + p.getProjectName() + ".xml");
            FileOutputStream fos1 = new FileOutputStream(textFile);
            IOUtils.write(content, fos1, charset);
            fos.close();

            p.setJenkinsFilePAth("http://localhost:8080/SpringMVCHibernate/assets/" + p.getProjectName() + "jenkinsfile");

            /*if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}*/
            //Stat of git
            String stats = "";
            //-----https://github.com/EmilBC/Gouvernance
            //----https://api.github.com/repos/EmilBC/Gouvernance/contributors
            String json = IOUtils.toString(new URL(p.getGit().replace("https://github.com/", "https://api.github.com/repos/") + "/contributors"), Charset.forName("UTF-8"));
            JSONArray array = new JSONArray(json);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                System.out.println(object.getString("login"));
                System.out.println(object.getInt("contributions"));
                stats = stats + " dev : " + object.getString("login") + " nbr commit : " + object.getInt("contributions") + ";<br>";
            }

            //--------------------------------------------------------------
            p.setInitOnourSystem("2");
            p.setGitStats(stats);
            projectService.addProject(p);

            System.out.println("----------------------------");
            System.out.println(p.getToken());
            System.out.println("----------------------------");

            return "redirect:/projects";
        }
    }

    @RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id) {

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }

}
