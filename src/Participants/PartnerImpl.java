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
import ma02_resources.participants.Partner;


public class PartnerImpl extends ParticipantImpl implements Partner{

    private String vat, website;
    
    public PartnerImpl (String name, String email, Contact ctc,
            Instituition inst, String vat, String ws){
        
        super(name, email, ctc, inst);
        this.vat = vat;
        this.website = ws;
    }
    
    @Override
    public String getVat() {
        return this.vat;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }
    
    public String toString(){
        return super.toString() + "\nVat: " + vat + "\nWebsite: " + website;
    }
    
}
