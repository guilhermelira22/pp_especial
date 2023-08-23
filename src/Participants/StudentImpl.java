/*
Nome: <Guilherme Fonseca Lira de Meireles>
Número: <8210415>
Turma: <LSIRCT1>
*
Nome: <Miguel Diogo Cunha>
Número: <8210426>
Turma: <LSIRCT1>
*/
package Participants;

import ma02_resources.participants.Contact;
import ma02_resources.participants.Instituition;
import ma02_resources.participants.Student;


public class StudentImpl extends ParticipantImpl implements Student{

    private final int num;
    private int n_avaliacoesproj, n_avaliacoeshet;
    
    private float nota_projs[], nota_hetero[], nota_auto;
    
    
    public StudentImpl (String name, String email, Contact ctc,
            Instituition inst, int num){
        
        super(name, email, ctc, inst);
        this.num = num;
        this.n_avaliacoesproj=0;
        this.n_avaliacoeshet = 0;
        this.nota_projs= new float[n_avaliacoesproj];
        this.nota_hetero = new float[n_avaliacoeshet];
        this.nota_auto=-1;
        
        
    }
    
    @Override
    public int getNumber() {
        return this.num;
    }
    
    public float[] getNotaProjs(){
        return this.nota_projs;
    }
    
    public float[] getNotaHet(){
        return this.nota_hetero;
    }
    
    public float getNotaAuto(){
        return this.nota_auto;
    }
    
    public void setNotaProjs(float nota){
        // O array está cheio, redimensiona o array
        float[] notacop = new float[n_avaliacoesproj + 1];
        System.arraycopy(nota_projs, 0, notacop, 0, n_avaliacoesproj);
        this.nota_projs = notacop;
        this.nota_projs[n_avaliacoesproj] = nota;
        n_avaliacoesproj++;
        
    }
    
    
    public int getNAvaliacoesProg(){
        return n_avaliacoesproj;
    }
    
    public void setNotaAuto(float nota){
        this.nota_auto=nota;
    }
    
    public void setNotaHetero(float nota){
        // O array está cheio, redimensiona o array
        float[] notahetcop = new float[n_avaliacoeshet + 1];
        System.arraycopy(nota_hetero, 0, notahetcop, 0, n_avaliacoeshet);
        this.nota_hetero = notahetcop;
        this.nota_hetero[n_avaliacoeshet] = nota;
        n_avaliacoeshet++;
    }
    
    public int getNAvaliacoesHet(){
        return n_avaliacoeshet;
    }
    
    @Override
    public String toString(){
        return super.toString() + "\nNumber: " + num;
    }
    
}
