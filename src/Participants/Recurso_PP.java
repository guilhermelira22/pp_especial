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

import CBL.Menu;
import Project.EditionImpl;
import Project.Export;
import Project.SubmissionImpl;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import ma02_resources.participants.InstituitionType;
import ma02_resources.project.exceptions.IllegalNumberOfParticipantType;
import ma02_resources.project.exceptions.ParticipantAlreadyInProject;
import org.json.simple.parser.ParseException;


public class Recurso_PP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException, ParseException, java.text.ParseException, IllegalNumberOfParticipantType, ParticipantAlreadyInProject {
        
        Menu menu = new Menu();
        
        menu.menu();
        
    }
    
}
