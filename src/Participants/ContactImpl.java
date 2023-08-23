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


public class ContactImpl implements Contact{

    private String street, city, state, zipcode, country, phone; 
    
    public ContactImpl(String street, String city, String state, 
            String zipcode, String country, String phone){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipcode = zipcode;
        this.country = country;
        this.phone = phone;
    }
    
    @Override
    public String getStreet() {
        return this.street;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String getZipCode() {
        return this.zipcode;
    }

    @Override
    public String getCountry() {
        return this.country;
    }

    @Override
    public String getPhone() {
        return this.phone;
    }
    
    public String toString(){
        return "\nAddress: " + street + ", " + city + ", " + state + ", " 
                + zipcode + ", " + country + "\nContact: " + phone;
    }
    
}
