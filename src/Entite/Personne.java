package Entite;
public class Personne {
    
    private String nom;
    private String prenom;
    private String matricule;
    private String telephone;
    private String domaineActivite;
    private String motDePasse;
    private Enum categorie; 
    private boolean listeRouge;

    public Personne(String nom, String prenom, String matricule, String telephone,
                       String domaineActivite, String motDePasse, Enum categorie, boolean listeRouge) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.telephone = telephone;
        this.domaineActivite = domaineActivite;
        this.motDePasse = motDePasse;
        this.categorie = categorie;
        this.listeRouge = listeRouge;
    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDomaineActivite() {
        return domaineActivite;
    }

    public void setDomaineActivite(String domaineActivite) {
        this.domaineActivite = domaineActivite;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Enum getCategorie() {
        return categorie;
    }

    public void setCategorie(Enum categorie) {
        this.categorie = categorie;
    }

    public boolean isListeRouge() {
        return listeRouge;
    }

    public void setListeRouge(boolean listeRouge) {
        this.listeRouge = listeRouge;
    }

}
