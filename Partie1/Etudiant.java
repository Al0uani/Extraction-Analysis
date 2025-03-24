public class Etudiant {
    public int cne;
    public String cin;
    public String nom;
    public String prenom;
    public int anneeNaissance;
    public String villeBac;
    public String filiere;
    public int niveau;
    public double noteS1;
    public double noteS2;
    public double noteS3;
    public double noteS4;
    public double noteS5;
    public double noteS6;
    public int nbrAbsences;
    public int nbrRapportsMauvaiseConduite;
    public int nbrRapportsTriche;
    public double noteStage1;
    public String lieuxStage1;
    public double noteStage2;
    public String lieuxStage2;
    public double noteStage3;
    public String lieuxStage3;

    public Etudiant(String[] part) {
        this.cne = Integer.parseInt(part[0].trim().replaceAll("^\"|\"$", ""));
        this.cin = part[1].trim().replaceAll("^\"|\"$", "");
        this.nom = part[2].trim().replaceAll("^\"|\"$", "");
        this.prenom = part[3].trim().replaceAll("^\"|\"$", "");
        this.anneeNaissance = Integer.parseInt(part[4].trim().replaceAll("^\"|\"$", ""));
        this.villeBac = part[5].trim().replaceAll("^\"|\"$", "");
        this.filiere = part[6].trim().replaceAll("^\"|\"$", "");
        this.niveau = Integer.parseInt(part[7].trim().replaceAll("^\"|\"$", ""));
        this.noteS1 = Double.parseDouble(part[8].trim().replaceAll("^\"|\"$", ""));
        this.noteS2 = Double.parseDouble(part[9].trim().replaceAll("^\"|\"$", ""));
        this.noteS3 = Double.parseDouble(part[10].trim().replaceAll("^\"|\"$", ""));
        this.noteS4 = Double.parseDouble(part[11].trim().replaceAll("^\"|\"$", ""));
        this.noteS5 = Double.parseDouble(part[12].trim().replaceAll("^\"|\"$", ""));
        this.noteS6 = Double.parseDouble(part[13].trim().replaceAll("^\"|\"$", ""));
        this.nbrAbsences = Integer.parseInt(part[14].trim().replaceAll("^\"|\"$", ""));
        this.nbrRapportsMauvaiseConduite = Integer.parseInt(part[15].trim().replaceAll("^\"|\"$", ""));
        this.nbrRapportsTriche = Integer.parseInt(part[16].trim().replaceAll("^\"|\"$", ""));
        this.noteStage1 = Double.parseDouble(part[17].trim().replaceAll("^\"|\"$", ""));
        this.lieuxStage1 = part[18].trim().replaceAll("^\"|\"$", "");
        this.noteStage2 = Double.parseDouble(part[19].trim().replaceAll("^\"|\"$", ""));
        this.lieuxStage2 = part[20].trim().replaceAll("^\"|\"$", "");
        this.noteStage3 = Double.parseDouble(part[21].trim().replaceAll("^\"|\"$", ""));
        this.lieuxStage3 = part[22].trim().replaceAll("^\"|\"$", "");
    }

    @Override
    public String toString() {
        return "Etudiant [cne=" + cne + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", anneeNaissance="
                + anneeNaissance + ", villeBac=" + villeBac + ", filiere=" + filiere + ", niveau=" + niveau
                + ", noteS1=" + noteS1 + ", noteS2=" + noteS2 + ", noteS3=" + noteS3 + ", noteS4=" + noteS4
                + ", noteS5=" + noteS5 + ", noteS6=" + noteS6 + ", nbrAbsences=" + nbrAbsences
                + ", nbrRapportsMauvaiseConduite=" + nbrRapportsMauvaiseConduite + ", nbrRapportsTriche="
                + nbrRapportsTriche + ", noteStage1=" + noteStage1 + ", lieuxStage1=" + lieuxStage1 + ", noteStage2="
                + noteStage2 + ", lieuxStage2=" + lieuxStage2 + ", noteStage3=" + noteStage3 + ", lieuxStage3="
                + lieuxStage3 + "]";
    }
}
