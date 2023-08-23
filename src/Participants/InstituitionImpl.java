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
import ma02_resources.participants.InstituitionType;


public class InstituitionImpl implements Instituition{
    
    private String name, email, website, description;
    private InstituitionType type;
    private Contact contact;
    
    
    public InstituitionImpl(String name, String email, String ws, 
            String desc, InstituitionType type, Contact ctc){
        this.name = name;
        this.email = email;
        this.website = ws;
        this.description = desc;
        this.type = type;
        this.contact = ctc;
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
    public InstituitionType getType() {
        return this.type;
    }

    @Override
    public Contact getContact() {
        return this.contact;
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public void setWebsite(String string) {
        this.website = string;
    }

    @Override
    public void setDescription(String string) {
        this.description = string;
    }

    @Override
    public void setContact(Contact cntct) {
        this.contact = cntct;
    }

    @Override
    public void setType(InstituitionType it) {
        this.type = it;
    }
    
    
    public String toString(){
        return "\t\t|Instituition| \nName: " + name + "\nEmail: " + email + 
                "\nWebsite: " + website + "\nDescription: " + description 
                + contact.toString();
    }
}
