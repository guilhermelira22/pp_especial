/*
Nome: <Guilherme Fonseca Lira de Meireles>
Número: <8210415>
Turma: <LSIRCT1>
*
Nome: <Miguel Diogo Cunha>
Número: <8210426>
Turma: <LSIRCT1>
*/
package Project;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class EditionImpl implements Edition{

    private final String name;
    private final String projectTemplate;
    private final LocalDate start;
    private LocalDate end;
    private Status status;
    private ProjectImpl[] project;
    private int n_projs;

    
    public EditionImpl (String name, String projTempl, LocalDate start){
        this.name = name;
        this.projectTemplate = projTempl;
        this.start = start;
        this.n_projs = 0;
        this.project = new ProjectImpl[n_projs];
        this.status = Status.INACTIVE;
    }
    
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public String getProjectTemplate() {
        return this.projectTemplate;
    }
    

    @Override
    public Status getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(Status status) {
    try {
        if (status == null) {
            throw new IllegalArgumentException("Invalid status.");
        }
        this.status = status;
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }
}
    

    @Override
    public void addProject(String string, String string1, String[] strings) throws ParseException, IllegalArgumentException {
        try {
            // Verifica se o nome do projeto é nulo ou vazio
            if (string == null || string.isEmpty()) {
                throw new IllegalArgumentException("Project name cannot be null or empty.");
            }

            // Verifica se o projeto já está na edição
            for (ProjectImpl projec : project) {
                if (projec.getName().equals(string)) {
                    System.out.println("Ja existe");
                    return;
                }
            }

            // Fazer o parsing do template JSON
            JSONParser parser = new JSONParser();
            JSONObject template = (JSONObject) parser.parse(this.projectTemplate);

            int numberOfFacilitors = ((Long) template.get("number_of_facilitors")).intValue();
            int numberOfStudents = ((Long) template.get("number_of_students")).intValue();
            int numberOfPartners = ((Long) template.get("number_of_partners")).intValue();

            ProjectImpl proj = new ProjectImpl(string, string1, strings,
                    numberOfStudents, numberOfPartners, numberOfFacilitors);

            // Extrair as tarefas
            JSONArray tasks = (JSONArray) template.get("tasks");

            for (Object taskObj : tasks) {
                JSONObject task = (JSONObject) taskObj;
                String title = (String) task.get("title");
                String description = (String) task.get("description");
                long startAtLong = (long) task.get("start_at");
                LocalDate startAt = LocalDate.ofEpochDay(startAtLong);
                int duration = ((Long) task.get("duration")).intValue();
                Task taskcop = new TaskImpl(title, description, startAt, duration);
                proj.addTask(taskcop);
            }

            // Adiciona o projeto ao projeto
            if (n_projs == project.length) {
                // O array está cheio, redimensiona o array
                ProjectImpl[] projectcop = new ProjectImpl[n_projs + 1];
                System.arraycopy(project, 0, projectcop, 0, n_projs);
                project = projectcop;
            }
            project[n_projs] = proj;
            n_projs++;
        } catch (org.json.simple.parser.ParseException ex) {
            throw new ParseException("", 0);
        }
    }
        

    @Override
    public void removeProject(String string) {
        try {
            int i = 0;
            // Verifica se o projeto já está na edição
            for (Project projec : project) {
                if (projec.getName().equals(string)) {
                    project[i] = null;
                    for (int j = i; j < n_projs; j++) {
                        project[j] = project[j + 1];
                    }
                    project[n_projs] = null;
                    n_projs--;
                    throw new ParticipantAlreadyInProject("O participante já está presente no projeto.");
                }
                i++;
            }

            System.out.println("NÃO EXISTE");
        } catch (ParticipantAlreadyInProject e) {
            System.out.println(e.getMessage());
        }
    }

   @Override
    public Project getProject(String string) throws IllegalArgumentException {
        try {
            if (string == null || string.isEmpty()) {
                throw new IllegalArgumentException("Nome do projeto esta vazio");
            }

            for (ProjectImpl projec : project){
                if(projec.getName().equals(string)){
                    return projec;
                }
            }

            throw new IllegalArgumentException("Projeto nao existe");
        } catch (IllegalArgumentException e) {
            throw e;
        }
    }

    @Override
    public Project[] getProjects() {
        
        if(n_projs>0){
            return project;
        }
        
        return null;
    }
    
    public ProjectImpl[] getProjectsImpl() {
        
        if(n_projs>0){
            return project;
        }
        
        return null;
    }

    @Override
    public Project[] getProjectsByTag(String string) {
        
        int n_proj=0;
        ProjectImpl[] proj = new ProjectImpl[n_proj];

        for (ProjectImpl projec : project){
            for(String tags : projec.getTags()){
                if (tags.equals(string)) {
                    
                    // O array está cheio, redimensiona o array
                    ProjectImpl[] projcop = new ProjectImpl[n_proj + 1];
                    System.arraycopy(proj, 0, projcop, 0, n_proj);
                    proj = projcop;
                    proj[n_proj] = projec;
                    n_proj++;
                }
            }
        }
        
        if(n_proj>0){
            return proj;
        }
        
        return null;
    }

    @Override
    public Project[] getProjectsOf(String string) {
        
        int n_proj=0;
        ProjectImpl[] proj = new ProjectImpl[n_proj];

        for (ProjectImpl projec : project){
                if (projec.getParticipant(string) != null) {
                    
                    // O array está cheio, redimensiona o array
                    ProjectImpl[] projcop = new ProjectImpl[n_proj + 1];
                    System.arraycopy(proj, 0, projcop, 0, n_proj);
                    proj = projcop;
                    proj[n_proj] = projec;
                    n_proj++;
                }
        }
        
        if(n_proj>0){
            return proj;
        }
        
        return null;
        
    }

    @Override
    public int getNumberOfProjects() {
        return this.n_projs;
    }

    @Override
    public LocalDate getEnd() {
        return this.end;
    }
    
    public float progress(){

        float i = 0, total;
  
        for (ProjectImpl proj : project) {
            i += proj.progress();
        }
        
        total = i / this.n_projs;
        
        return total;
    }
    
    @Override
    public String toString(){
        
        return "\t\t|Edição|\nName:" + this.name + "\nTemplate: " + this.projectTemplate + 
                "\nInicio: " + this.start + "\nStatus: " + this.status + "\nNumero de Projetos: " + this.n_projs;
    }
    
}
