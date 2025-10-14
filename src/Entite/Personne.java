package Entite;

public class Personne {
    private int id;
    private String nom;
    private String prenom;
    private String matricule;
    private String telephone;
    private String adresseCourriel; 
    private String domaineActivite;
    private String motDePasse;
    private String categorie; // je remplacerai peut etre par ENUM ON verra bien
    private boolean listeRouge;


    //  Constructeur vide (OBLIGATOIRE pour le DAO)
    public Personne() {
    }
    public Personne(int id, String nom, String prenom, String matricule, String telephone,String adresseCourriel,
            String domaineActivite, String motDePasse, String categorie, boolean listeRouge) {

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.telephone = telephone;
        this.adresseCourriel = adresseCourriel;
        this.domaineActivite = domaineActivite;
        this.motDePasse = motDePasse;
        this.categorie = categorie;
        this.listeRouge = listeRouge;
    }

       // Constructeur sans ID (pour les insertions)
    public Personne(String nom, String prenom, String matricule, String telephone,
                    String adresseCourriel, String domaineActivite, String motDePasse, 
                    String categorie, boolean listeRouge) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.telephone = telephone;
        this.adresseCourriel = adresseCourriel;
        this.domaineActivite = domaineActivite;
        this.motDePasse = motDePasse;
        this.categorie = categorie;
        this.listeRouge = listeRouge;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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

     public void setAdresseCourriel(String AdresseCourriel) {
        this.adresseCourriel = AdresseCourriel;
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

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public boolean isListeRouge() {
        return listeRouge;
    }

    public void setListeRouge(boolean listeRouge) {
        this.listeRouge = listeRouge;
    }


      @Override
    public String toString() {
        return "Personne{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", matricule='" + matricule + '\'' +
                ", telephone='" + telephone + '\'' +
                ", adresseCourriel='" + adresseCourriel + '\'' +
                ", domaineActivite='" + domaineActivite + '\'' +
                ", categorie='" + categorie + '\'' +
                ", listeRouge=" + listeRouge +
                '}';
    }
}
