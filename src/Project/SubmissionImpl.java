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

import java.time.LocalDateTime;
import ma02_resources.participants.Student;
import ma02_resources.project.Submission;


public class SubmissionImpl implements Submission{

    private final LocalDateTime date;
    private final Student student;
    private final String text;
    
    public SubmissionImpl (LocalDateTime date, Student stud, String text){
        this.date = date;
        this.student = stud;
        this.text = text;
    }
    
    
    @Override
    public LocalDateTime getDate() {
        return this.date;
    }

    @Override
    public Student getStudent() {
        return this.student;
    }

    @Override
    public String getText() {
        return this.text;
    }

    @Override
    public int compareTo(Submission sbmsn) {
        return this.date.compareTo(sbmsn.getDate());
    }
    
    public String toString(){
        return "\t\t|Submission|\n" + student + "\nDate: " + date + 
                "\nText: " + text;
    }
    
}
