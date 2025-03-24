import java.util.*;
import java.io.IOException;
import java.nio.file.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileWriter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;


import javax.xml.bind.*;


import java.util.List;

public class Main {
	static String Url = "jdbc:mysql://localhost:3306/bibliotheque";
	static String User = "root";
	static String Password = "";
	
	static void Adherent_FetchAll(HashMap<String,Adherent>AdherentsMap)throws IOException {
		//---- Ahderent --------
		ObjectMapper objectMapper = new ObjectMapper();
		Adherent[] adherentsArray = objectMapper.readValue(
				new File("Data/Adherent1.json"), Adherent[].class);
		List<Adherent> adherents = Arrays.asList(adherentsArray);
		for(Adherent a : adherents) {AdherentsMap.put(a.CIN, a);}
		Files.lines(Paths.get("Data/Adherent2.csv"))
	    .map(line -> line.split(","))
	    .forEach(k -> {
	        String key = k[0].trim().replaceAll("^\"|\"$", "");
	        if (AdherentsMap.containsKey(key)) {
	            Adherent adherent = AdherentsMap.get(key);
	            adherent.situation = k[1].trim().replaceAll("^\"|\"$", "");
	            adherent.profession = k[2].trim().replaceAll("^\"|\"$", "");
	        }
	    });
		//----- Fin Adherent ------
	}
	
	static void Livre_FetchAll(HashMap<Integer,Livre>LivresMap)throws IOException {
		//-----Livre-----------
		Files.lines(Paths.get("Data/Livre.csv"))
		.skip(1)
		.map(line -> line.split(","))
		.forEach(k -> {
	        String idLivre1 = k[0].trim().replaceAll("^\"|\"$", "");    
	        	int idLivre2 = Integer.parseInt(k[0].trim().replaceAll("^\"|\"$", ""));
                String titre = k[1].trim().replaceAll("^\"|\"$", "");
                String auteur = k[2].trim().replaceAll("^\"|\"$", "");
                int DateSortie = Integer.parseInt(k[3].trim().replaceAll("^\"|\"$", ""));
                String editeur = k[4].trim().replaceAll("^\"|\"$", "");
                double prixAchat = Double.parseDouble(k[5].trim().replaceAll("^\"|\"$", ""));
                int numDep = Integer.parseInt(k[6].trim().replaceAll("^\"|\"$", ""));
                LivresMap.computeIfAbsent(idLivre2, x -> new Livre(idLivre2, titre, 
                		auteur, DateSortie, editeur, prixAchat, numDep));
        
	    });	
		//----- Fin Livre ---------------------
		
	}

	static void Inscriptions_FetchAll(List<Inscriptions> InscriptionsList) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		Inscriptions[] InscriptionsArray = objectMapper.readValue(
				new File("Data/Inscriptions.json"), Inscriptions[].class);
		InscriptionsList .addAll( Arrays.asList(InscriptionsArray));
		
	}
	
	static void Departement_FetchAll(HashMap<Integer,Departement>DepartementMap)
			throws IOException {
		Files.lines(Paths.get("Data/Departement.csv"))
		.skip(1)
		.map(lines ->lines.split(","))
		.forEach(k -> {
			int numDep = Integer.parseInt(k[0].trim().replaceAll("^\"|\"$", ""));
			String NomDep = k[1].trim().replaceAll("^\"|\"$", "");
			String categorie = k[2].trim().replaceAll("^\"|\"$", "");
			int nbrTables =Integer.parseInt(k[3].trim().replaceAll("^\"|\"$", ""));
			int superficie= Integer.parseInt(k[0].trim().replaceAll("^\"|\"$", ""));
			DepartementMap.computeIfAbsent(
					numDep, x -> new Departement(numDep,NomDep,categorie,
							nbrTables,superficie));
		});
	}
	
	static void Gerant_FetchAll(HashMap<Integer,Gerant> GerantMap)throws IOException, 
	JAXBException{
		File file = new File("Data/gerant.xml");
        // Create JAXB context and unmarshaller
        JAXBContext context = JAXBContext.newInstance(GerantList.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // Read XML and convert to GerantList
        GerantList gerantList = (GerantList) unmarshaller.unmarshal(file);
        List<Gerant> gerants = gerantList.getGerants();
        
        for (Gerant g : gerants) {
            GerantMap.put(g.getIdG(), g);
        }
		
		
		
	}
	
	public static void main(String[] args) throws IOException, JAXBException, SQLException {
		HashMap<String,Adherent> AdherentsMap = new HashMap<>(); 
		Adherent_FetchAll(AdherentsMap);
		HashMap<Integer,Livre> LivresMap = new HashMap<>();
		Livre_FetchAll(LivresMap);
		List<Inscriptions> InscriptionsList = new ArrayList<>();
		Inscriptions_FetchAll(InscriptionsList);
		HashMap<Integer,Departement> DepartementMap = new HashMap<>();
		Departement_FetchAll(DepartementMap);
		HashMap<Integer,Gerant> GerantMap = new HashMap<>();
		Gerant_FetchAll(GerantMap);
		DataBaseCustomConnector BD = new DataBaseCustomConnector(Url,User,Password);
		BD.Connect();
		ResultSet R = BD.SimpleQueryWithResult("SELECT DISTINCT g.Specialite\r\n"
				+ "FROM gerant g\r\n"
				+ "JOIN departement d ON g.IdDep = d.IdDep\r\n"
				+ "JOIN inscription i ON d.IdDep = i.IdDep\r\n"
				+ "JOIN adherent a ON i.IdAd = a.IdAd\r\n"
				+ "WHERE a.profession = 'médecin';");
		while(R.next()) {
		System.out.println("Specialite Gerant: "+R.getString(1));
		}
		BD.Disconnect();
		
		
		
		
	}


	

}
