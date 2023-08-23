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
import ma02_resources.participants.Facilitator;
import ma02_resources.participants.Instituition;


public class FacilitatorImpl extends ParticipantImpl implements Facilitator{

    private String area;
    
    public FacilitatorImpl(String name, String email, Contact ctc,
            Instituition inst, String area){
        super(name, email, ctc, inst);
        this.area = area;
    }
    
    @Override
    public String getAreaOfExpertise() {
        return this.area;
    }

    @Override
    public void setAreaOfExpertise(String string) {
        this.area = string;
    }
    
    public String toString(){
        return super.toString() + "\nArea Of Expertise:" + area;
    }
    
}
