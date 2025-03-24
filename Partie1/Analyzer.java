import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileWriter;

public class Analyzer {
	public String File;
	public String Folder;
	public List<String> Header;
	public HashMap<String, ArrayList<Integer>> CategoryMap ;
	public HashMap<String, String> CategoryCol;
	public HashMap<String, HashSet<String>> FilliersNiveau;
	public List<String>Fillier;
	public boolean initialzed = false;
	
	public Analyzer(String Folder,String File,List<String>Header,
			HashMap<String, ArrayList<Integer>> CategoryMap,HashMap<String, String> CategoryCol) {
		this.File =File;
		this.Folder = Folder;
		this.CategoryCol = CategoryCol;
		this.Header = Header;
		this.CategoryMap = CategoryMap;
		this.FilliersNiveau = new HashMap<>();
		
	}
	
	private void Filliers_protocol() throws IOException {
		if(this.Header != null) {
		List<String> Fillier = Files.lines(Paths.get(this.File))
                .map(line -> line.split(","))
                .filter(line -> this.Header.stream()
                        .map(String::toLowerCase)
                        .noneMatch(header -> Arrays.stream(line)
                        		.anyMatch(column -> column.toLowerCase()
                        				.contains(header))))
                .map(part -> {
                    String Key = part[6].trim().replaceAll("^\"|\"$", "");
                    String value =part[7].trim().replaceAll("^\"|\"$", "");
                    
                    this.FilliersNiveau.computeIfAbsent(Key, k -> new HashSet<>()).add("Niveau"+value); 
                    return Key;  
                })
                .distinct()
                .collect(Collectors.toList());
			this.Fillier = Fillier;
		}
		else {throw new ArithmeticException("Header is Empty");}
	}
	
	public void Folders_creation() throws IOException {
		if (this.initialzed != true) {
		Filliers_protocol();
		Path FillierPath = Paths.get(this.Folder);
		Files.createDirectories(FillierPath);
		for(String k : this.FilliersNiveau.keySet()) {
			Path FolderPath = FillierPath.resolve(k);
			Files.createDirectories(FolderPath);
			for(String v :this.FilliersNiveau.get(k)) {
				Path SubFolderPath = FolderPath.resolve(v);
				Files.createDirectories(SubFolderPath);
				 for (String x : this.CategoryCol.keySet()) {
	                   
	                    Path filePath = SubFolderPath.resolve(x + ".csv");
	                    try (FileWriter writer = new FileWriter(filePath.toFile())) {
	                        
	                        writer.write(this.CategoryCol.get(x)+"\n");
	                        writer.close();
	                    } catch (IOException e) {
	                        System.err.println("Error writing file: " + filePath);
	                        e.printStackTrace();
	                    }}
		}}
		this.initialzed = true;
		
		}
		else {throw new ArithmeticException("The Analyzer Already Create and Initialized folder & files csv");}
	}
	
	public  HashMap<String,ArrayList<String>> Filtre_category(String Category,String fillier) throws IOException {
		HashMap<String,ArrayList<String>>Students = new HashMap<>();
		List<String> res = Files.lines(Paths.get(this.File))
                .map(line -> line.split(","))
                .map(part -> {
                    if (part[6].toLowerCase().contains(fillier.toLowerCase())) {
                    	int start = this.CategoryMap.get(Category.toLowerCase()).get(0);
                    	int end = this.CategoryMap.get(Category.toLowerCase()).get(1);
                    	String value =part[0]+part[6]+part[7];
                    	for(int i=start;i<=end;i++) {value+= part[i];}
                    	String niveau =part[7].trim().replaceAll("^\"|\"$", "");
                    	Students.computeIfAbsent(niveau, k-> new ArrayList()).add(value.trim().replaceAll("^\"|\"$", ""));
                    	return value.trim().replaceAll("^\"|\"$", "");}
					return null;})
                .filter(part -> part != null) 
                .collect(Collectors.toList());
		
		return Students;
	}
	
	
	public void WriteDataFillier(String Fillier) throws IOException {
		Path mainfolder = Paths.get(this.Folder).resolve(Fillier);
		
		for(String m : this.CategoryCol.keySet()) {
		HashMap<String,ArrayList<String>>Students = Filtre_category(m,Fillier);
		for(String x : Students.keySet()) {
			Path subfolder = mainfolder.resolve("Niveau"+x).resolve(m+".csv");
			for(String s : Students.get(x)) {
				String input = s.replace("\"\"", ",")+"\n";
				//System.out.println(input);
				
				try (FileWriter writer = new FileWriter(subfolder.toFile(),true)) {
                    writer.write(input);
                    writer.close();
                } catch (IOException e) {
                    System.err.println("Error writing file: " + subfolder);
                    e.printStackTrace();
                }
			}
			
		}
		}	
	}

	public void WriteAllDataThreads() {
		if(this.initialzed == true) {
		int threadCount = 0; // Counter to track the number of threads
	    List<Thread> threads = new ArrayList<>(); // List to hold references to threads
	    for (String f : this.Fillier) {
	        Thread x = new Thread(() -> {
	            try {
	                WriteDataFillier(f);
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }, "th" + threadCount);
	        x.start();
	        threadCount++;
	        System.out.println("Thread " + threadCount + " started for fillier: " + f);
	        threads.add(x);
	    }
	    for (Thread thread : threads) {
	        try {
	            thread.join();
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

	    System.out.println("All threads have finished execution.");
		}else {throw new ArithmeticException("Illegal Use, Please use Folders_creation() First");}
	}
	
	public void ClearAllData() throws IOException {
		Path folder = Paths.get(Folder);
		 Files.walk(folder)
         .sorted((a, b) -> b.compareTo(a)) // Delete files before deleting folders
         .forEach(path -> {
             try {
                 Files.delete(path);
             } catch (IOException e) {
                 System.err.println("Failed to delete: " + path);
             }
         });
		 this.initialzed = false;
	}
	
	public void Benchmark(Collection<Etudiant> collection) throws IOException {
	    long startTime, endTime;

	    Collection<Etudiant> etudiants = Files.lines(Paths.get(this.File))
	        .map(line -> line.split(","))
	        .filter(line -> this.Header.stream()
	            .map(String::toLowerCase)
	            .noneMatch(header -> Arrays.stream(line)
	                .anyMatch(column -> column.toLowerCase().contains(header))))
	        .map(Etudiant::new) 
	        .collect(Collectors.toList());
	   
	    
	 // Mesure de l'opération addAll
        startTime = System.nanoTime();
        collection.addAll(etudiants);
        endTime = System.nanoTime();
        System.out.println(collection.getClass().getSimpleName() + " add: " + (endTime - startTime) / 1e6 + " ms");
    
        // Sélection d'un étudiant pour les tests contains et remove
        Etudiant testEtudiant = etudiants.iterator().next();
        Etudiant testEtudiant2 = etudiants.iterator().next();
        // Mesure de l'opération contains
        startTime = System.nanoTime();
        boolean contains = collection.contains(testEtudiant);
        endTime = System.nanoTime();
        System.out.println(collection.getClass().getSimpleName() + " contains: " + (endTime - startTime) / 1e6 + " ms");

        // Mesure de l'opération remove
        startTime = System.nanoTime();
        boolean removed = collection.remove(testEtudiant);
        endTime = System.nanoTime();
        System.out.println(collection.getClass().getSimpleName() + " remove: " + (endTime - startTime) / 1e6 + " ms");
        // Mesure de l'opération get
        if (collection instanceof List) {
            List<Etudiant> list = (List<Etudiant>) collection;
            startTime = System.nanoTime();
            Etudiant etudiant = list.get(0);
            endTime = System.nanoTime();
            System.out.println(collection.getClass().getSimpleName() + " get: " + (endTime - startTime) / 1e6 + " ms");
        }
	    
	}

	
	
	
	
}
