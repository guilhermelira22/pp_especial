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


import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Participant;
import ma02_resources.participants.Partner;
import ma02_resources.participants.Student;
import ma02_resources.project.Project;
import ma02_resources.project.Task;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.IllegalNumberOfTasks;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import ma02_resources.project.exceptions.TaskAlreadyInProject;

/**
 * Implementation of the Project interface.
 * Replace 'YourProjectName' with your actual project name.
 * Replace other methods' implementations accordingly.
 */
public class ProjectImpl implements Project {
    
    private final int maxNumTasks = 1;
    
    private final int maxNumStudents;
    private final int maxNumPartners;
    private final int maxNumFacilitators; 

    private int maxNumParticipants;
    private final String name;
    private final String description;
    private Participant[] participants;
    private Task[] tasks;
    private String[] tags;

    // Constructor
    public ProjectImpl(String name, String description, String[] tags, 
            int max_studs, int max_part, int max_facil) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.maxNumStudents = max_studs;
        this.maxNumPartners = max_part;
        this.maxNumFacilitators = max_facil;
        this.maxNumParticipants = max_studs + max_part + max_facil;
        this.participants = new Participant[maxNumParticipants];
        this.tasks = new Task[maxNumTasks];
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getNumberOfParticipants() {
        int count = 0;
        for (Participant participant : participants) {
            if (participant != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfStudents() {
        int count = 0;
        for (Participant participant : participants) {
            if (participant instanceof Student) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfPartners() {
        int count = 0;
        for (Participant participant : participants) {
            if (participant instanceof Partner) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfFacilitators() {
        int count = 0;
        for (Participant participant : participants) {
            if (participant instanceof Facilitator) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getNumberOfTasks() {
        int count = 0;
        for (Task task : tasks) {
            if (task != null) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int getMaximumNumberOfTasks() {
        return tasks.length;
    }

    @Override
    public long getMaximumNumberOfParticipants() {
        return this.maxNumStudents + this.maxNumPartners + this.maxNumFacilitators;
    }

    @Override
    public int getMaximumNumberOfStudents() {       
        return this.maxNumStudents;
    }

    @Override
    public int getMaximumNumberOfPartners() {
        return this.maxNumPartners;
    }

    @Override
    public int getMaximumNumberOfFacilitators() {
        return this.maxNumFacilitators;
    }

    @Override
    public void addParticipant(Participant p) {
        try {
            // Verifica se o número máximo de participantes já foi atingido
            if (getNumberOfParticipants() >= getMaximumNumberOfParticipants()) {
                throw new IllegalNumberOfParticipantType("Número máximo de participantes atingido.");
            }

            if (getNumberOfStudents() >= getMaximumNumberOfStudents()) {
                throw new IllegalNumberOfParticipantType("Número máximo de estudantes atingido.");
            }

            if (getNumberOfPartners() >= getMaximumNumberOfPartners()) {
                throw new IllegalNumberOfParticipantType("Número máximo de patrocinadores atingido.");
            }

            if (getNumberOfFacilitators() >= getMaximumNumberOfFacilitators()) {
                throw new IllegalNumberOfParticipantType("Número máximo de facilitadores atingido.");
            }

            // Verifica se o participante já está no projeto
            for (Participant participant : participants) {
                if (participant != null && participant.getEmail().equals(p.getEmail())) {
                    throw new ParticipantAlreadyInProject("O participante já está presente no projeto.");
                }
            }

            // Adiciona o participante ao projeto
            for (int i = 0; i < participants.length; i++) {
                if (participants[i] == null) {
                    participants[i] = p;
                    return;
                }
            }
        } catch (IllegalNumberOfParticipantType | ParticipantAlreadyInProject e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Participant removeParticipant(String email) {
        try {
            for (int i = 0; i < participants.length; i++) {
                if (participants[i] != null && participants[i].getEmail().equals(email)) {
                    participants[i] = null;
                    for (int j = i; j < participants.length - 1; j++) {
                        participants[j] = participants[j + 1];
                    }
                    return participants[i];
                }
            }
            throw new IllegalArgumentException("O aluno não existe.");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("O aluno não existe.");
        }
    }

    @Override
    public Participant getParticipant(String string) {
    try {
        if (string == null) {
            throw new IllegalArgumentException("Email cannot be null.");
        }

        for (Participant participant : participants) {
            if (participant != null && participant.getEmail().equals(string)) {
                return participant;
            }
        }

        throw new IllegalArgumentException("Participant does not exist.");
    } catch (IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    return null;
}
    
    public Participant[] getParticipants(){
        return participants;
    }


    @Override
    public String[] getTags() {
        return this.tags;
    }

    @Override
    public boolean hasTag(String tag) {
        for (String t : tags) {
            if (t.equals(tag)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addTask(Task task) {
        try {
            // Verifica se o número máximo de tarefas já foi atingido
            if (getNumberOfTasks() >= getMaximumNumberOfTasks()) {
                throw new IllegalNumberOfTasks("Número máximo de tarefas atingido.");
            }

            // Verifica se a tarefa já está no projeto
            for (Task t : tasks) {
                if (t != null && t.getTitle().equals(task.getTitle())) {
                    throw new TaskAlreadyInProject("A tarefa já está presente no projeto.");
                }
            }

            // Adiciona a tarefa ao projeto
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] == null) {
                    tasks[i] = task;
                    System.out.println("A tarefa foi adicionada com sucesso.");
                    return;
                }
            }
        } catch (IllegalNumberOfTasks | TaskAlreadyInProject e) {
        }
    }

    @Override
    public Task getTask(String string) {
        // Procura a tarefa com o titulo fornecido na lista de participantes
        for (Task task : tasks) {
            if (task != null && task.getTitle().equals(string)) {
                return task;
            }
        }

        return null; // Retorna null se a tarefa não for encontrado
    }

    @Override
    public Task[] getTasks() {
        return this.tasks;
    }

    @Override
    public boolean isCompleted() {
        // Verifica se o número de tarefas inseridas é igual ao número especificado na criação do projeto
        if (getNumberOfTasks() != maxNumTasks) {
            return false;
        }

        // Verifica se todas as tarefas têm pelo menos uma submissão
        for (Task task : tasks) {
            if (task == null || task.getNumberOfSubmissions() == 0) {
                return false;
            }
        }

        // Se todas as tarefas têm pelo menos uma submissão e o número de tarefas é igual ao número especificado, o projeto está completo
        return true;
    }
    
    public float progress(){

        int i=0;
        float total;
        
        // Verifica se todas as tarefas têm pelo menos uma submissão
        for (Task task : tasks) {
            if (task == null || task.getNumberOfSubmissions() != 0) {
                i++;
            }
        }
        
        total = (i / tasks.length) * 100;
        return total;
    }
    
    @Override
    public String toString(){
        
        String tagsTotal = "";
            for (String tag : tags) {
                tagsTotal += tag + ", ";
            }
            
        tagsTotal = tagsTotal.substring(0, tagsTotal.length() - 2);
        return "\t\t|Project|\nName:" + name + "\nDescription: " + description + 
                "\nTags: " + tagsTotal;
    }

}