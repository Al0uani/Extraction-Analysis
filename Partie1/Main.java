import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.io.IOException;




public class Main {
	//Variable global
	static String File = "donnees_etudiantsLight.csv";
	static String Folder = "fillier";
	//Header list c'est la list des colomn de fichier csv
	static List<String> Header = List.of(
            "cne", "cin", "Nom", "Prenom", "anneeNaissance", "VilleBac", 
            "filiere", "niveau",
            "noteS1", "noteS2", "noteS3", "noteS4", "noteS5", "noteS6",
            "nbrAbsences", "nbrRapportsMauvaiseConduite", "nbrRapportsTriche",
            "noteStage1", "lieuxStage1", "noteStage2", "lieuxStage2",
            "noteStage3", "lieuxStage3"
        );

	static HashMap<String, ArrayList<Integer>> CategoryMap = new HashMap<>() {{
        put("personnels", new ArrayList<>(Arrays.asList(1, 5)));
        put("evaluations", new ArrayList<>(Arrays.asList(8, 13)));
        put("discipline", new ArrayList<>(Arrays.asList(14, 16)));
        put("stages", new ArrayList<>(Arrays.asList(17, 22)));
    }};
    static HashMap<String, String> CategoryCol = new HashMap<>() {{
        put("personnels","cne,fillier,niveau,cin,nom,prenom,anneeNaissance,VilleBac");
        put("evaluations", "cne,fillier,niveau,noteS1,noteS2,noteS3,noteS4,noteS5,noteS6");
        put("discipline", "cne,fillier,niveau,nbrAbsences,nbrRapportsMauvaiseConduite,nbrRapportsTriche");
        put("stages", "cne,fillier,niveau,noteStage1,lieuxStage1,noteStage2,lieuxStage2,noteStage3,lieuxStage3");
    }};
	

	
	public static void main(String[] args) throws IOException, InterruptedException {
		Analyzer A1 = new Analyzer(Folder,File,Header,CategoryMap,CategoryCol);
		ArrayList<Etudiant> ArrayList1 = new ArrayList<>();
		HashSet<Etudiant> HashSet1 = new HashSet<>();
		LinkedList<Etudiant> LinkedList1 = new LinkedList<>();
		LinkedHashSet<Etudiant> LinkedHashSet1 = new LinkedHashSet<>();
		
		A1.Folders_creation(); // Creation de tout les dossier et fichier .csv
		A1.WriteAllDataThreads();// Population de tout les fichier avec chaque thread assignee a une fillier
		A1.Benchmark(HashSet1);
		A1.Benchmark(ArrayList1);
		A1.Benchmark(LinkedList1);
		A1.Benchmark(LinkedHashSet1);
		
	    
	    
	   

	    

		
	

				
		
		
		
		

	}

}
