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
import ma02_resources.participants.Participant;


public class ParticipantImpl implements Participant{

    private String name, email;
    private Contact contact;
    private Instituition instituition;
    
    public ParticipantImpl(String name, String email, Contact ctc,
            Instituition inst){
        this.name = name;
        this.email = email;
        this.contact = ctc;
        this.instituition = inst;
    }
    
    @Override
    public String getName() {        
        return this.name;
    }

    @Override
    public String getEmail() {
        return this.email;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public Instituition getInstituition() {
        return this.instituition;
    }

    @Override
    public void setInstituition(Instituition instn) {
        this.instituition = instn;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }
    
    
    @Override
    public String toString(){
        return "\t\t|Participant| \nName: " + name + "\nEmail: " + email + 
                contact.toString() + "\nInstituition: " 
                + instituition.getName();
    }
}
