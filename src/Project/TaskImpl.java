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

import java.time.LocalDate;
import ma02_resources.project.Submission;
import ma02_resources.project.Task;


public class TaskImpl implements Task{
    
    private final LocalDate start;
    private int duration;
    private final String title;
    private final String description;
    private int n_subs;
    private Submission[] subm;
    
    public TaskImpl(String title, String description, LocalDate start, int duration) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.duration = duration;
        this.n_subs =0;
        this.subm = new Submission[n_subs];
    }
    
    @Override
    public LocalDate getStart() {
        return this.start;
    }

    @Override
    public LocalDate getEnd() {
        return this.start.plusDays(duration);
    }

    @Override
    public int getDuration() {
        return this.duration;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Submission[] getSubmissions() {
        return subm;
    }

    @Override
    public int getNumberOfSubmissions() {
        return this.n_subs;
    }

    @Override
    public void addSubmission(Submission sbmsn) {
        try {
            if (sbmsn == null) {
                throw new IllegalArgumentException("A submissão não pode ser nula.");
            }

            if (n_subs == subm.length) {
                // O array está cheio, redimensiona o array
                Submission[] submcop = new Submission[n_subs * 2];
                System.arraycopy(subm, 0, submcop, 0, n_subs);
                subm = submcop;
            }

            subm[n_subs] = sbmsn;
            n_subs++;
        } catch (IllegalArgumentException e){}
    }

    @Override
    public void extendDeadline(int i) {
        try {
            if (i < 0) {
                throw new IllegalArgumentException("O número de dias não pode ser negativo.");
            }

            this.duration += i;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int compareTo(Task task) {
        return this.start.compareTo(task.getStart());
    }
}
