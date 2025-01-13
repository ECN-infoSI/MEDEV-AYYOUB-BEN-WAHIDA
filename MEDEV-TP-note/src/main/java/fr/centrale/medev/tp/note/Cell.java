package fr.centrale.medev.tp.note;

/**
 * La classe Cell représente une cellule sur un plateau de jeu avec une position (x, y)
 * et un état ('N' pour Noir, 'B' pour Blanc, ' ' pour vide).
 * Cette classe permet de gérer l'état de la cellule et de récupérer ses coordonnées.
 * 
 * @author Nadhem
 */
public class Cell {
    private int x; // Position X de la cellule
    private int y; // Position Y de la cellule
    private char state; // 'N' pour Noir, 'B' pour Blanc, ' ' pour vide
    
    /**
     * Constructeur de la classe Cell.
     * Initialise une cellule avec les coordonnées spécifiées (x, y) et définit son état par défaut à ' ' (vide).
     *
     * @param x La position X de la cellule.
     * @param y La position Y de la cellule.
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.state = ' '; // Par défaut, la cellule est vide
    }
    
    /**
     * Récupère la position X de la cellule.
     *
     * @return La position X de la cellule.
     */
    public int getX() {
        return x;
    }

    /**
     * Récupère la position Y de la cellule.
     *
     * @return La position Y de la cellule.
     */
    public int getY() {
        return y;
    }

    /**
     * Récupère l'état de la cellule (Noir, Blanc ou vide).
     *
     * @return L'état de la cellule, un caractère ('N', 'B' ou ' ').
     */
    public char getState() {
        return state;
    }

    /**
     * Définit l'état de la cellule.
     *
     * @param state Le nouvel état de la cellule, qui doit être 'N', 'B' ou ' '.
     */
    public void setState(char state) {
        this.state = state;
    }

    /**
     * Représente la cellule sous forme de chaîne de caractères pour l'affichage.
     * Retourne un caractère correspondant à l'état de la cellule.
     *
     * @return La représentation de l'état de la cellule sous forme de chaîne de caractères.
     */
    @Override
    public String toString() {
        return String.valueOf(state);
    }
}

