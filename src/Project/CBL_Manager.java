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

import ma02_resources.participants.Participant;
import ma02_resources.participants.Student;
import ma02_resources.project.Edition;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;


public class CBL_Manager {
    
    private EditionImpl[] edicao;
    private int n_edits;
    
    public CBL_Manager (){
        this.n_edits = 0;
        this.edicao = new EditionImpl[n_edits];
    }
    
    public void addEdition(EditionImpl edit) {
        try {
            if (edit == null) {
                throw new IllegalArgumentException("A edição não pode ser nula.");
            }

            if (n_edits == edicao.length) {
                // O array está cheio, redimensiona o array
                EditionImpl[] editcop = new EditionImpl[n_edits + 1];
                System.arraycopy(edicao, 0, editcop, 0, n_edits);
                edicao = edicao;
            }

            edicao[n_edits] = edit;
            n_edits++;
        } catch (IllegalArgumentException e){}
    }
    
    public void removeEdition(String name) {
        int i = 0;
        // Verifica se o projeto já está na edição
        for (EditionImpl editions : edicao) {
            if (editions.getName().equals(name)) {
                edicao[i] = null;
                for (int j = i; j < n_edits; j++) {
                    edicao[j] = edicao[j + 1];
                }
                edicao[n_edits] = null;
                n_edits--;
                return;
            }
            i++;
        }
        System.out.println("NÃO EXISTE");
    }
    
    public EditionImpl[] getEditions(){
        return edicao;
    }
    public int getN_Edits(){
        return this.n_edits;
    }
    
    public String checkStudent(int num){
        for(int i=0;i<this.n_edits;i++){
            for(int j=0;j<edicao[i].getNumberOfProjects();j++){
                for (Participant participant : edicao[i].getProjectsImpl()[j].getParticipants()) {
                    if (participant instanceof Student) {
                        if(((Student) participant).getNumber() == num){
                            return participant.getEmail();
                        }
                    }
                }
            }
            
        }
        return null;
    }
}
