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

import Participants.ParticipantImpl;
import Participants.StudentImpl;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Export { //Classe Export é responsável por exportar informações para arquivos JSON e CSV.
    
    //Exporta uma edição para um arquivo JSON.
    //@param edition a edição a ser exportada
    //@throws IOException se ocorrer um erro de E/S durante a exportação
    public void exportEditionToJSON(EditionImpl edition) throws IOException{
        
        // Criação de um array JSON para armazenar os projetos
        JSONArray projarray = new JSONArray();
        // Criação de um objeto JSON para armazenar os dados da edição
        JSONObject jsonobject = new JSONObject();
        
        // Itera sobre os projetos da edição
        for (ProjectImpl proj : edition.getProjectsImpl()) {
            // Criação de um objeto JSON para armazenar os dados do projeto
            JSONObject projobject = new JSONObject();
            projobject.put("name", proj.getName());
            projobject.put("description", proj.getDescription());
            
            // Criação de um array JSON para armazenar as tags do projeto
            JSONArray tagsarray = new JSONArray();
            for(String tags: proj.getTags()){
                tagsarray.add(tags);
            }
            
            projobject.put("tags", tagsarray);
            projobject.put("number_of_facilitors", proj.getMaximumNumberOfFacilitators());
                
            // Criação de um array JSON para armazenar os facilitadores do projeto
                JSONArray facilarray = new JSONArray();
                for(Participant part: proj.getParticipants()){
                    if (part instanceof Facilitator facilitator) {
                        JSONObject facilobject = new JSONObject();
                        facilobject.put("name", part.getName());
                        facilobject.put("email", part.getEmail());
                        
                        JSONArray cttarray = new JSONArray();
                        JSONObject cttobject = new JSONObject();
                        cttobject.put("street", part.getContact().getStreet());
                        cttobject.put("city", part.getContact().getCity());
                        cttobject.put("state", part.getContact().getState());
                        cttobject.put("zipcode", part.getContact().getZipCode());
                        cttobject.put("country", part.getContact().getCountry());
                        cttobject.put("phone", part.getContact().getPhone());
                        
                        cttarray.add(cttobject);
                        facilobject.put("contact", cttarray);
                        
                        JSONArray instarray = new JSONArray();
                        JSONObject instobject = new JSONObject();
                        instobject.put("name", part.getInstituition().getName());
                        instobject.put("email", part.getInstituition().getEmail());
                        instobject.put("website", part.getInstituition().getWebsite());
                        instobject.put("description", part.getInstituition().getDescription());
                        instobject.put("type", part.getInstituition().getType().toString());
                        
                        JSONArray cttinstarray = new JSONArray();
                        JSONObject cttinstobject = new JSONObject();
                        cttinstobject.put("street", part.getInstituition().getContact().getStreet());
                        cttinstobject.put("city", part.getInstituition().getContact().getCity());
                        cttinstobject.put("state", part.getInstituition().getContact().getState());
                        cttinstobject.put("zipcode", part.getInstituition().getContact().getZipCode());
                        cttinstobject.put("country", part.getInstituition().getContact().getCountry());
                        cttinstobject.put("phone", part.getInstituition().getContact().getPhone());
                        
                        cttinstarray.add(cttinstobject);
                        instobject.put("contact", cttinstarray);
                        
                        instarray.add(instobject);
                        facilobject.put("instituition", instarray);
                        facilobject.put("area", facilitator.getAreaOfExpertise());
                        
                        facilarray.add(facilobject);
                    }
                }
            projobject.put("facilitors", facilarray);
            
            projobject.put("number_of_students", proj.getMaximumNumberOfStudents());
            
            // Criação de um array JSON para armazenar os estudantes do projeto
                JSONArray studentarray = new JSONArray();
                for(Participant part: proj.getParticipants()){
                    if (part instanceof StudentImpl student) {
                        JSONObject studentobject = new JSONObject();
                        studentobject.put("name", part.getName());
                        studentobject.put("email", part.getEmail());
                        
                        JSONArray cttarray = new JSONArray();
                        JSONObject cttobject = new JSONObject();
                        cttobject.put("street", part.getContact().getStreet());
                        cttobject.put("city", part.getContact().getCity());
                        cttobject.put("state", part.getContact().getState());
                        cttobject.put("zipcode", part.getContact().getZipCode());
                        cttobject.put("country", part.getContact().getCountry());
                        cttobject.put("phone", part.getContact().getPhone());
                        
                        cttarray.add(cttobject);
                        studentobject.put("contact", cttarray);
                        
                        JSONArray instarray = new JSONArray();
                        JSONObject instobject = new JSONObject();
                        instobject.put("name", part.getInstituition().getName());
                        instobject.put("email", part.getInstituition().getEmail());
                        instobject.put("website", part.getInstituition().getWebsite());
                        instobject.put("description", part.getInstituition().getDescription());
                        instobject.put("type", part.getInstituition().getType().toString());
                        
                        JSONArray cttinstarray = new JSONArray();
                        JSONObject cttinstobject = new JSONObject();
                        cttinstobject.put("street", part.getInstituition().getContact().getStreet());
                        cttinstobject.put("city", part.getInstituition().getContact().getCity());
                        cttinstobject.put("state", part.getInstituition().getContact().getState());
                        cttinstobject.put("zipcode", part.getInstituition().getContact().getZipCode());
                        cttinstobject.put("country", part.getInstituition().getContact().getCountry());
                        cttinstobject.put("phone", part.getInstituition().getContact().getPhone());
                        
                        cttinstarray.add(cttinstobject);
                        instobject.put("contact", cttinstarray);
                        
                        instarray.add(instobject);
                        studentobject.put("instituition", instarray);
                        studentobject.put("num", student.getNumber());
                        studentobject.put("autoavaliacao",student.getNotaAuto());
                        studentobject.put("heteroavaliacao",student.getNotaHet());
                        studentobject.put("nota_projetos",student.getNotaProjs());
                        
                        studentarray.add(studentobject);
                    }
                }
            projobject.put("students", studentarray);
            
            projobject.put("number_of_partners", proj.getMaximumNumberOfPartners());
            
            // Criação de um array JSON para armazenar os parceiros do projeto
                JSONArray partarray = new JSONArray();
                for(Participant part: proj.getParticipants()){
                    if (part instanceof Partner) {
                        JSONObject partobject = new JSONObject();
                        partobject.put("name", part.getName());
                        partobject.put("email", part.getEmail());
                        
                        JSONArray cttarray = new JSONArray();
                        JSONObject cttobject = new JSONObject();
                        cttobject.put("street", part.getContact().getStreet());
                        cttobject.put("city", part.getContact().getCity());
                        cttobject.put("state", part.getContact().getState());
                        cttobject.put("zipcode", part.getContact().getZipCode());
                        cttobject.put("country", part.getContact().getCountry());
                        cttobject.put("phone", part.getContact().getPhone());
                        
                        cttarray.add(cttobject);
                        partobject.put("contact", cttarray);
                        
                        JSONArray instarray = new JSONArray();
                        JSONObject instobject = new JSONObject();
                        instobject.put("name", part.getInstituition().getName());
                        instobject.put("email", part.getInstituition().getEmail());
                        instobject.put("website", part.getInstituition().getWebsite());
                        instobject.put("description", part.getInstituition().getDescription());
                        instobject.put("type", part.getInstituition().getType().toString());
                        
                        JSONArray cttinstarray = new JSONArray();
                        JSONObject cttinstobject = new JSONObject();
                        cttinstobject.put("street", part.getInstituition().getContact().getStreet());
                        cttinstobject.put("city", part.getInstituition().getContact().getCity());
                        cttinstobject.put("state", part.getInstituition().getContact().getState());
                        cttinstobject.put("zipcode", part.getInstituition().getContact().getZipCode());
                        cttinstobject.put("country", part.getInstituition().getContact().getCountry());
                        cttinstobject.put("phone", part.getInstituition().getContact().getPhone());
                        
                        cttinstarray.add(cttinstobject);
                        instobject.put("contact", cttinstarray);
                        
                        instarray.add(instobject);
                        partobject.put("instituition", instarray);
                        partobject.put("vat", ((Partner) part).getVat());
                        partobject.put("website", ((Partner) part).getWebsite());
                        
                        partarray.add(partobject);
                    }
                }
                
            projobject.put("partners", partarray);
            
            // Criação de um array JSON para armazenar as tarefas do projeto
            JSONArray tasksarray = new JSONArray();
            for(Task task : proj.getTasks()){
                JSONObject tasksobject = new JSONObject();
                tasksobject.put("title", task.getTitle());
                tasksobject.put("description", task.getDescription());
                tasksobject.put("start_at", task.getStart().format(DateTimeFormatter.ISO_DATE));
                tasksobject.put("duration", task.getDuration());
                tasksobject.put("number_of_submissions", task.getNumberOfSubmissions());
                    
                JSONArray subsarray = new JSONArray();    
                for(Submission subs: task.getSubmissions()){
                    JSONObject subsobject = new JSONObject();
                    subsobject.put("date", subs.getDate().format(DateTimeFormatter.ISO_DATE));
                    subsobject.put("text", subs.getText());
                    
                    JSONArray studarray = new JSONArray(); 
                    JSONObject studobject = new JSONObject();
                    studobject.put("num", subs.getStudent().getNumber());
                    studobject.put("name", subs.getStudent().getName());
                    studarray.add(studobject);
                    
                    subsobject.put("student", studarray);
                    
                    subsarray.add(subsobject);
                }
                
                tasksobject.put("submission", subsarray);
                
                tasksarray.add(tasksobject);
            }
            
            
            projobject.put("tasks", tasksarray);
            
            projarray.add(projobject);
        }
        jsonobject.put("name", edition.getName());
        jsonobject.put("date", edition.getStart().format(DateTimeFormatter.ISO_DATE));
        jsonobject.put("number_of_projects", edition.getNumberOfProjects());
        jsonobject.put("progresso", edition.progress());
        jsonobject.put("status", edition.getStatus().toString());
        jsonobject.put("projects", projarray);
        
        String path = "test/" + edition.getName() +".json";
        
        try (FileWriter file = new FileWriter(path)) {
            file.write(jsonobject.toJSONString());
        }
        System.out.println(jsonobject.toJSONString());
    }
    
    //Exporta várias edições para um arquivo CSV.
    //@param editions as edições a serem exportadas
    //@param fileName o nome do arquivo CSV de destino
    public void exportToCSV(EditionImpl[] editions, String fileName) {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Edições");

            
            int rowIndex = 0;

            // Cria o cabeçalho do arquivo CSV
            Row headerRow = sheet.createRow(rowIndex++);
            headerRow.createCell(0).setCellValue("Data");
            headerRow.createCell(1).setCellValue("Nome"); 
            headerRow.createCell(2).setCellValue("Status");
            headerRow.createCell(3).setCellValue("Progresso");
            headerRow.createCell(4).setCellValue("Numero de projetos");
            headerRow.createCell(5).setCellValue("Nome dos projetos");
            headerRow.createCell(6).setCellValue("Descricao dos projetos");
            headerRow.createCell(7).setCellValue("Numero de estudantes");
            headerRow.createCell(8).setCellValue("Numero de patrocinadores");
            headerRow.createCell(9).setCellValue("Numero de facilitadores");            

            // Adiciona as edições ao arquivo CSV
            for (EditionImpl edition : editions) {
                    Row dataRow = sheet.createRow(rowIndex++);
                    dataRow.createCell(0).setCellValue(edition.getStart().toString());
                    dataRow.createCell(1).setCellValue(edition.getName());
                    dataRow.createCell(2).setCellValue(edition.getStatus().toString());
                    dataRow.createCell(3).setCellValue(edition.progress());
                    dataRow.createCell(4).setCellValue(edition.getNumberOfProjects());
                    String[] novoNome = new String[edition.getNumberOfProjects()];
                    String[] novoDescricao = new String[edition.getNumberOfProjects()];
                    String valueNumStudents = "";
                    String valueNumPartners = "";
                    String valueNumFacilitators = "";
                    for(int i=0;i<edition.getNumberOfProjects();i++){
                        novoNome[i] = edition.getProjects()[i].getName();
                        novoDescricao[i] = edition.getProjects()[i].getDescription();
                        valueNumStudents += Integer.toString(edition.getProjects()[i].getNumberOfStudents());
                        valueNumPartners += Integer.toString(edition.getProjects()[i].getNumberOfPartners());
                        valueNumFacilitators += Integer.toString(edition.getProjects()[i].getNumberOfFacilitators());
                        if (i < edition.getNumberOfProjects() - 1) {
                            valueNumStudents += ", ";
                            valueNumPartners += ", ";
                            valueNumFacilitators += ", ";
                        }
                    String valueNome = String.join(", ", novoNome);
                    String valueDescricao = String.join(", ", novoDescricao);
                    dataRow.createCell(5).setCellValue(valueNome);
                    dataRow.createCell(6).setCellValue(valueDescricao);
                    dataRow.createCell(7).setCellValue(valueNumStudents);
                    dataRow.createCell(8).setCellValue(valueNumPartners);
                    dataRow.createCell(9).setCellValue(valueNumFacilitators);
                    
                }
            }
            // Grava o conteúdo no arquivo
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}