/*
Nome: <Guilherme Fonseca Lira de Meireles>
Número: <8210415>
Turma: <LSIRCT1>
*
Nome: <Miguel Diogo Cunha>
Número: <8210426>
Turma: <LSIRCT1>
*/
package CBL;

import Participants.StudentImpl;
import Project.CBL_Manager;
import Project.EditionImpl;
import Project.Export;
import Project.ProjectImpl;
import Project.SubmissionImpl;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import ma02_resources.participants.InstituitionType;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.Project;
import ma02_resources.project.Status;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;

public class Menu {

    Scanner sc = new Scanner(System.in);
    CBL_Manager cbl;
    Export export = new Export();

    public void menu() throws IOException, ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------");
            System.out.println("|\tCBL\t |");
            System.out.println("|    Bem vindo\t |");
            System.out.println("|\t\t |");
            System.out.println("|\t\t |");
            System.out.println("|Login:\t\t |");
            System.out.println("|    1-Aluno\t |");
            System.out.println("|    2-Admin\t |");
            System.out.println("|\t\t |");
            System.out.println("|\t\t |");
            System.out.println("|0-Sair\t\t |");
            System.out.println("------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    sc.nextLine();
                    System.out.println("Insira o seu numero de estudante: ");
                    int num = sc.nextInt();
                    String email = cbl.checkStudent(num);

                    if(email != null){
                        alunoMenu(email);
                        break;
                    }
                    System.out.println("Numero Invalido");
                    break;

                case 2:
                    System.out.println("Insira a password de administrador: ");
                    int num2 = sc.nextInt();

                    if(num2 == 1234){
                        adminMenu();
                        break;
                    }

                    System.out.println("Password incorreta");
                    break;

                case 0:
                    exit = true;
                    break;

                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void alunoMenu(String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------");
            System.out.println("|1-Edicao\t |");
            System.out.println("|2-Notas\t |");
            System.out.println("|3-Sair\t\t |");
            System.out.println("------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    edicaoMenu(email);
                    break;
                case 2:
                    notasMenu(email);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void adminMenu() throws IOException, ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Gerir edicoes       |");
            System.out.println("|2-Gerir participantes |");
            System.out.println("|3-Exportar CBL   |");
            System.out.println("|4-Sair\t\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    gerirEdicoes();
                    break;
                case 2:
                    gerirParticipantes();
                    break;
                case 3:
                    exportarCBL();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void exportarCBL() throws IOException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("-------------------");
            System.out.println("|1-Excel\t  |");
            System.out.println("|2-Json\t\t  |");
            System.out.println("|3-Todos\t\t  |");
            System.out.println("|4-Voltar\t  |");
            System.out.println("-------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    export.exportToCSV(cbl.getEditions(), "teste.csv");
                    break;
                case 2:
                    for(EditionImpl edition: cbl.getEditions()){
                        export.exportEditionToJSON(edition);
                    }
                    break;
                case 3:
                    for(EditionImpl edition: cbl.getEditions()){
                        export.exportEditionToJSON(edition);
                    }
                    export.exportToCSV(cbl.getEditions(), "teste.csv");
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirParticipantes(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Atribuir notas |");
            System.out.println("|2-Adicionar participante |");
            System.out.println("|3-Remover participante |");
            System.out.println("|4-Sair\t\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Insira o numero do aluno: ");
                    int num = sc.nextInt();
                    System.out.println("Insira o nome do projeto: ");
                    String projeto = sc.nextLine();
                    break;
                case 2:
                    break;
                case 3:

                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }


    public void verParticipantes(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Todos       |");
            System.out.println("|2-Estudantes |");
            System.out.println("|3-Facilitadores |");
            System.out.println("|4-Patrocinadores |");
            System.out.println("|5-Sair\t\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:
                    
                    break;
                case 3:
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }


    public void gerirEdicoes() throws IOException, ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("--------------------------");
            System.out.println("|1-Edicoes\t\t |");
            System.out.println("|2-Adicionar edicao\t |");
            System.out.println("|3-Apagar edicao\t |");
            System.out.println("|4-Ativar uma edicao|");
            System.out.println("|5-Numero total de edicoes|");
            System.out.println("|6-Voltar\t\t |");
            System.out.println("--------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    edicoes();
                    break;
                case 2:
                    System.out.println("Insira o nome da edição: ");
                    String name = sc.nextLine();
                    System.out.println("Insira o PATH da template: ");
                    String path = sc.nextLine();
                    System.out.print("Insira uma data (dd/MM/yyyy): ");
                    String dateString = sc.nextLine();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(dateString, formatter);
                    
                    String template = new String(Files.readAllBytes(Paths.get(path)));
                    
                    EditionImpl edit = new EditionImpl(name, template, date);
                    
                    cbl.addEdition(edit);
                    
                    break;
                case 3:
                    System.out.println("Insira o nome da edição: ");
                    cbl.removeEdition(sc.nextLine());
                    
                    break;
                case 4:
                    System.out.println("Insira o nome da edição: ");
                    String namechange = sc.nextLine();
                    
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getStatus().equals(Status.ACTIVE)){
                            edition.setStatus(Status.INACTIVE);
                        }
                    }
                    
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getName().equals(namechange)){
                            edition.setStatus(Status.ACTIVE);
                        }
                    }
                    
                    break;

                case 5:
                    cbl.getN_Edits();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void edicoes() throws ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ativa\t       |");
            System.out.println("|2-Inativas\t       |");
            System.out.println("|3-Canceladas\t       |");
            System.out.println("|4-Fechadas\t       |");
            System.out.println("|5-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    ativas();
                    break;
                case 2:
                    inativas();
                    break;
                case 3:
                    canceladas();
                    break;
                case 4:
                    fechadas();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void ativas() throws ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver projetos\t       |");
            System.out.println("|2-Gerir projetos  |");
            System.out.println("|3-Numero de projetos\t       |");
            System.out.println("|4-Atribuir nota a aluno\t       |");
            System.out.println("|5-Ver progresso da edição\t       |");
            System.out.println("|6-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    for(EditionImpl edition: cbl.getEditions() ){
                        for(ProjectImpl proj: edition.getProjectsImpl()){
                            proj.toString();
                            progress(proj.progress());
                        }
                    }
                    break;
                case 2:
                    gerirProjetosAtivos();
                    break;
                case 3:
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getStatus().equals(Status.ACTIVE)){
                            System.out.println(edition.getNumberOfProjects());
                        }
                    }
                    break;
                case 4:
                    System.out.println("Insira o email do aluno a dar a nota: ");
                    String emailal = sc.nextLine();
                    
                    for(int i=0;i<cbl.getN_Edits();i++){
                            if(cbl.getEditions()[i].getStatus() == Status.ACTIVE){
                                for(ProjectImpl proj: cbl.getEditions()[i].getProjectsImpl()){
                                    if(proj.getParticipant(emailal)!=null){
                                        StudentImpl stud = (StudentImpl) proj.getParticipant(emailal);
                                        System.out.println("\nNota de Projeto: ");
                                        stud.setNotaProjs(sc.nextFloat());
                                    }
                                }
                            }
                        }
                    
                    
                    break;
                case 5:
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getStatus().equals(Status.ACTIVE)){
                            progress(edition.progress());
                        }
                    }
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirProjetosAtivos() throws ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Adicionar projeto\t       |");
            System.out.println("|2-Remover projeto  |");
            System.out.println("|3-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("Insira o nome do projeto a adicionar: ");
                    String name = sc.nextLine();
                    System.out.println("Insira uma tag: ");
                    String tag[] = null;
                    tag[0] = sc.nextLine();
                    System.out.println("Insira outra tag: ");
                    tag[1] = sc.nextLine();
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getStatus().equals(Status.ACTIVE)){
                            edition.addProject(name, edition.getProjectTemplate(), tag);
                        }
                    }
                    break;

                case 2:
                    System.out.println("Insira o nome do projeto a remover: ");
                    String name2 = sc.nextLine();
                    for(EditionImpl edition: cbl.getEditions()){
                        if(edition.getStatus().equals(Status.ACTIVE)){
                            edition.removeProject(name2);
                        }
                    }

                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void inativas() throws ParseException{

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver edicoes\t       |");
            System.out.println("|2-Gerir edicao  |");
            System.out.println("|3-Numero de edicoes\t       |");
            System.out.println("|4-Edicoes que possuem projetos com submissao em falta|");
            System.out.println("|5-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    
                    break;
                case 2:
                    gerirEdicaoInativa();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirEdicaoInativa() throws ParseException{

        boolean exit = false;
        int option = 0;

        System.out.println("Insira o nome da edicao");
        String name = sc.nextLine();



        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver projetos\t       |");
            System.out.println("|2-Gerir projetos  |");
            System.out.println("|3-Numero de projetos\t       |");
            System.out.println("|4-Ver progresso da edição\t       |");
            System.out.println("|5-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    
                    break;
                case 2:
                    gerirProjetosAtivos();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void canceladas(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver edicoes\t       |");
            System.out.println("|2-Gerir edicao  |");
            System.out.println("|3-Numero de edicoes\t       |");
            System.out.println("|4-Edicoes que possuem projetos com submissao em falta|");
            System.out.println("|5-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:
                    gerirEdicaoCancelada();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirEdicaoCancelada(){

        boolean exit = false;
        int option = 0;

        System.out.println("Insira o nome da edicao");
        String name = sc.nextLine();



        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver projetos\t       |");
            System.out.println("|2-Numero de projetos\t       |");
            System.out.println("|3-Ver progresso da edição\t       |");
            System.out.println("|4-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void fechadas(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver edicoes\t       |");
            System.out.println("|2-Gerir edicao  |");
            System.out.println("|3-Numero de edicoes\t       |");
            System.out.println("|4-Edicoes que possuem projetos com submissao em falta|");
            System.out.println("|4-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:
                    gerirEdicaoFechadas();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirEdicaoFechadas(){

        boolean exit = false;
        int option = 0;

        System.out.println("Insira o nome da edicao");
        String name = sc.nextLine();



        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver projetos\t       |");
            System.out.println("|2-Numero de projetos\t       |");
            System.out.println("|3-Ver progresso da edição\t       |");
            System.out.println("|4-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void projetosEdicao(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Ver projetos\t       |");
            System.out.println("|2-Gerir projetos  |");
            System.out.println("|3-Numero de projetos\t       |");
            System.out.println("|4-Atribuir nota a projeto\t       |");
            System.out.println("|4-Ver progresso da edição\t       |");
            System.out.println("|5-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:
                    gerirProjetos();
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void gerirProjetos(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("------------------------");
            System.out.println("|1-Adicionar projeto\t       |");
            System.out.println("|2-Remover projeto  |");
            System.out.println("|3-Ver progresso de projeto\t       |");
            System.out.println("|4-Voltar\t       |");
            System.out.println("------------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }


    public void verProjeto(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("--------------------");
            System.out.println("|1-Por participante|");
            System.out.println("|2-Por tags\t   |");
            System.out.println("|3-Voltar\t   |");
            System.out.println("--------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void exportar(){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("-------------------");
            System.out.println("|1-Excel\t  |");
            System.out.println("|2-Json\t\t  |");
            System.out.println("|3-Txt\t\t  |");
            System.out.println("|4-Todos\t\t  |");
            System.out.println("|5-Voltar\t  |");
            System.out.println("-------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void edicaoMenu(String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("-------------------");
            System.out.println("|1-Edicao atual\t  |");
            System.out.println("|2-Edicoes antigas|");
            System.out.println("|3-Sair\t\t  |");
            System.out.println("-------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    for(int i=0;i<cbl.getN_Edits();i++){
                        if(cbl.getEditions()[i].getStatus() == Status.ACTIVE){
                            edicaoAtual(cbl.getEditions()[i], email);
                            break;
                        }
                    }
                    System.out.println("Não existe edição atual ativa");
                    break;
                case 2:
                    edicoesAntigas(email);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

        public void edicaoAtual(EditionImpl edition, String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("---------------------");
            System.out.println("|1-Ver projetos\t    |");
            System.out.println("|2-Tarefas\t    |");
            System.out.println("|3-Voltar\t    |");
            System.out.println("---------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    for(int i=0;i<edition.getNumberOfProjects();i++){
                        if(edition.getProjectsImpl()[i].getParticipant(email) != null){
                            edition.getProjectsImpl()[i].toString();
                            progress(edition.getProjectsImpl()[i].progress());
                        }
                    }
                    break;
                case 2:
                    tarefasMenu(edition,email);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void tarefasMenu(EditionImpl edition, String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("----------------------");
            System.out.println("|1-Tarefas concluidas|");
            System.out.println("|2-Tarefas pendentes |");
            System.out.println("|3-Submeter Tarefa   |");
            System.out.println("|4-Sair\t\t     |");
            System.out.println("----------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    for(Project projec: edition.getProjects()){
                        if(projec.getParticipant(email) != null){
                            for(Task task: projec.getTasks()){
                                if(task.getNumberOfSubmissions()>0){
                                    task.toString();
                                }
                            }
                        }
                    }
                    break;
                case 2:
                    for(Project projec: edition.getProjects()){
                        if(projec.getParticipant(email) != null){
                            for(Task task: projec.getTasks()){
                                if(task.getNumberOfSubmissions()==0){
                                    task.toString();
                                }
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("Insira o titulo da tarefa: ");
                    String title = sc.nextLine();

                    for(Project projec: edition.getProjects()){
                        if(projec.getParticipant(email) != null){
                            for(Task task: projec.getTasks()){
                                if(task.getTitle().equals(title)){
                                    System.out.print("Insira uma data (dd/MM/yyyy): ");
                                    String dateString = sc.nextLine();

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                                    LocalDateTime date = LocalDateTime.parse(dateString, formatter);


                                    System.out.print("Insira a resposta a tarefa: ");
                                    String text = sc.nextLine();

                                    Submission sub = new SubmissionImpl(date, (Student) projec.getParticipant(email),text);
                                    task.addSubmission(sub);
                                }
                            }
                        }
                    }



                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

        public void edicoesAntigas(String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("---------------------");
            System.out.println("|1-Ver projetos\t    |");
            System.out.println("|2-Numero de edicoes que participou\t    |");
            System.out.println("|3-Voltar\t    |");
            System.out.println("---------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    for(EditionImpl edit: cbl.getEditions()){
                        if(edit.getStatus()!= Status.ACTIVE){
                            for(ProjectImpl proj: edit.getProjectsImpl()){
                                if(proj.getParticipant(email) != null){
                                    proj.toString();
                                    progress(proj.progress());
                                }
                            }
                        }
                    }
                    break;

                case 2:
                    int n_edits_part = 0;

                    for(Edition edit: cbl.getEditions()){
                        for(Project proj: edit.getProjects()){
                            if(proj.getParticipant(email)!=null){
                                n_edits_part++;
                            }
                        }
                    }
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }

    public void notasMenu(String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("-------------------");
            System.out.println("|1-Edicao atual\t  |");
            System.out.println("|2-Edicoes antigas|");
            System.out.println("|3-Sair\t\t  |");
            System.out.println("-------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                    edicaoAtualN(Status.ACTIVE, email);
                    break;
                case 2:
                    edicaoAtualN(Status.CLOSED, email);
                    break;
                case 3:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }


    public void edicaoAtualN(Status status, String email){

        boolean exit = false;
        int option = 0;

        while (!exit) {

            System.out.println("---------------------");
            System.out.println("|1-Ver notas\t    |");
            System.out.println("|2-Autovaliação\t    |");
            System.out.println("|3-Heteroavaliação  |");
            System.out.println("|4-Voltar\t    |");
            System.out.println("---------------------");

            option = sc.nextInt();

            switch (option) {
                case 1:
                        for(int i=0;i<cbl.getN_Edits();i++){
                            if(cbl.getEditions()[i].getStatus() == Status.ACTIVE){
                                for(ProjectImpl proj: cbl.getEditions()[i].getProjectsImpl()){
                                    if(proj.getParticipant(email)!=null){
                                        StudentImpl stud = (StudentImpl) proj.getParticipant(email);
                                        
                                        float nota_final_het=0;
                                        for(int h=0; i<stud.getNAvaliacoesHet();h++){
                                            nota_final_het+=stud.getNotaHet()[h];
                                        }
                                        nota_final_het/=stud.getNAvaliacoesHet();
                                        
                                        float nota_final_progs=0;
                                        for(int h=0; i<stud.getNAvaliacoesProg();h++){
                                            nota_final_progs+=stud.getNotaProjs()[h];
                                        }
                                        nota_final_progs/=stud.getNAvaliacoesProg();
                                        
                                        
                                        float nota_final = (float) (stud.getNotaAuto()*0.05 + nota_final_het * 0.1 + nota_final_progs * 0.85);
                                        
                                        System.out.println("\nNota Final Projetos: " + nota_final_progs);
                                        System.out.println("\nNota Final HeteroAvaliação: " + nota_final_het);
                                        System.out.println("\nNota Final AutoAvaliação: " + stud.getNotaAuto());
                                        System.out.println("\nNota Final: " + nota_final);
                                    }
                                }
                            }
                        }
                    break;
                case 2:
                    if(status == Status.ACTIVE){
                        for(int i=0;i<cbl.getN_Edits();i++){
                            if(cbl.getEditions()[i].getStatus() == Status.ACTIVE){
                                for(ProjectImpl proj: cbl.getEditions()[i].getProjectsImpl()){
                                    if(proj.getParticipant(email)!=null){
                                        StudentImpl stud = (StudentImpl) proj.getParticipant(email);
                                        System.out.println("\nAutoAvaliação: ");
                                        stud.setNotaAuto(sc.nextFloat());
                                    }
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("\nA edição ja não se encontra ativa");
                    }

                    break;
                case 3:
                    System.out.println("\nEmail do aluno a avaliar: ");
                    String emailhet = sc.nextLine();
                    if(status == Status.ACTIVE){
                        for(int i=0;i<cbl.getN_Edits();i++){
                            if(cbl.getEditions()[i].getStatus() == Status.ACTIVE){
                                for(ProjectImpl proj: cbl.getEditions()[i].getProjectsImpl()){
                                    if(proj.getParticipant(email)!=null){
                                        if(proj.getParticipant(emailhet )!= null){
                                            StudentImpl stud = (StudentImpl) proj.getParticipant(emailhet);
                                            System.out.println("\nHeteroAvaliação do projeto " + proj.getName() + ": ");
                                            stud.setNotaHetero(sc.nextFloat());
                                        }
                                    }
                                }
                            }
                            else{
                                System.out.println("\nA edição ja não se encontra ativa");
                            }
                        }
                    }
                    else{
                        System.out.println("\nA edição ja não se encontra ativa");
                    }

                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }

    }


    public void progress(float progress){
        for(int j=0;j<=54;j++){
            System.out.print("-");
        }
        for(int i=0;i<=50;i++){
            if(i==0){
                System.out.print("\n|");
            }
            else if(i<24){
                if(i<progress/2){
                    System.out.print("/");
                }else{
                    System.out.printf(" ");
                }
            }
            else if(i==50){
                System.out.print("|\n");
            }
            else if(i==24){
                System.out.printf(" %.2f%% " , progress);
            }
            else if(i>27){
                if(i<progress/2){
                    System.out.print("/");
                }else{
                    System.out.printf(" ");
                }
            }


        }
        for(int j=0;j<=54;j++){
            System.out.print("-");
        }
    }

}
