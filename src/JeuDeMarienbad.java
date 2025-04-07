import java.util.Scanner;

// Une variante du jeu de Nim rendue célèbre par le film d'Alain Resnais 
// "L'année dernière à Marienbad" en 1961.
// voir http://fr.wikipedia.org/wiki/Jeu_de_Marienbad

public class JeuDeMarienbad {
  private static final int NB_JOUEURS = 2;
  private static final int[] ALLUMETTES = { 1, 3, 5, 7 };
  private int[] allumettes;

  public JeuDeMarienbad() {
    allumettes = ALLUMETTES.clone();
  }

  public boolean partieFinie() {
    for (int n : allumettes)
      if (n != 0)
        return false;
    return true;
  }

  public void faireJouer(int j) {
    assert (!partieFinie());
    Scanner sc = new Scanner(System.in);
    int ligne, nombre;
    do {
      System.out.println(this);
      System.out.println("Joueur n°" + j);
      System.out
          .print("Entrez un numéro de la ligne où prendre des allumettes : ");
      ligne = sc.nextInt();
      System.out
          .print("Entrez le nombre d'allumettes à retirer de cette ligne : ");
      nombre = sc.nextInt();
    } while (ligne < 0 || ligne >= allumettes.length || nombre <= 0
        || nombre > allumettes[ligne]);
    allumettes[ligne] -= nombre;
  }

  @Override
  public String toString() {
    String s = "";
    for (int i = 0; i < allumettes.length; ++i)
      s += "ligne n°" + i + " : " + allumettes[i] + " allumette(s)\n";
    return s;
  }

  public void jouerUnePartie() {
    int j = 0;
    allumettes = ALLUMETTES.clone();
    while (true) {
      faireJouer(j);
      if (partieFinie())
        break;
      else
        j = (j + 1) % NB_JOUEURS;
    }
    System.out.println("le joueur n°" + ((j + 1) % NB_JOUEURS)
        + " a gagné la partie");
  }

  public static void main(String[] args) {
    JeuDeMarienbad j = new JeuDeMarienbad();
    j.jouerUnePartie();
  }
}
