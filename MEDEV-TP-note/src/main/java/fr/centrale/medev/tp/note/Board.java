package fr.centrale.medev.tp.note;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Board représente le plateau de jeu pour un jeu de type Othello.
 * Elle gère l'initialisation du plateau, l'affichage, la validation des mouvements,
 * ainsi que la capture des pions adverses.
 * 
 * @author Nadhem
 */
public class Board {
    final Cell[][] grid;

    // Dimensions du plateau
    private static final int SIZE = 8;

    /**
     * Constructeur de la classe Board.
     * Initialise le plateau avec des cellules vides et place les pions initiaux au centre.
     */
    public Board() {
        grid = new Cell[SIZE][SIZE];
        initializeBoard();
    }

    /**
     * Initialise le plateau avec des cellules vides et place les pions de départ.
     * Les pions noirs ('N') et blancs ('B') sont placés au centre du plateau.
     */
    private void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell(i, j); // Chaque cellule connaît ses coordonnées
            }
        }

        // Placement initial des pions au centre du plateau
        grid[3][3].setState('B');
        grid[4][4].setState('B');
        grid[3][4].setState('N');
        grid[4][3].setState('N');
    }

    /**
     * Affiche l'état actuel du plateau de jeu.
     * Affiche les colonnes (a-h) et les lignes (1-8).
     */
    public void display() {
        System.out.println("  a b c d e f g h"); // En-tête des colonnes
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " "); // En-tête des lignes
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Vérifie si une cellule est dans les limites du plateau.
     *
     * @param x La position X de la cellule.
     * @param y La position Y de la cellule.
     * @return true si la cellule est dans les limites du plateau, false sinon.
     */
    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }

    /**
     * Vérifie si une direction donnée peut capturer des pions adverses.
     *
     * @param x La position X de la cellule de départ.
     * @param y La position Y de la cellule de départ.
     * @param dx La direction horizontale.
     * @param dy La direction verticale.
     * @param state L'état du joueur (Noir ou Blanc).
     * @return true si une ligne de pions adverses peut être capturée, false sinon.
     */
    private boolean hasCapturableLine(int x, int y, int dx, int dy, char state) {
        char opponent = (state == 'N') ? 'B' : 'N';
        int i = x + dx, j = y + dy;
        boolean hasOpponentBetween = false;

        while (isInBounds(i, j) && grid[i][j].getState() == opponent) {
            hasOpponentBetween = true;
            i += dx;
            j += dy;
        }

        return hasOpponentBetween && isInBounds(i, j) && grid[i][j].getState() == state;
    }

    /**
     * Vérifie s'il est possible de capturer des pions dans toutes les directions possibles.
     *
     * @param x La position X de la cellule de départ.
     * @param y La position Y de la cellule de départ.
     * @param state L'état du joueur (Noir ou Blanc).
     * @return true si au moins un pion peut être capturé, false sinon.
     */
    private boolean canCapture(int x, int y, char state) {
        // Directions possibles : horizontale, verticale et diagonale
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1}, // Verticale et horizontale
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonale
        };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            if (hasCapturableLine(x, y, dx, dy, state)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Retourne les pions dans une direction donnée et les capture.
     *
     * @param x La position X de la cellule de départ.
     * @param y La position Y de la cellule de départ.
     * @param dx La direction horizontale.
     * @param dy La direction verticale.
     * @param state L'état du joueur (Noir ou Blanc).
     */
    private void flipLine(int x, int y, int dx, int dy, char state) {
        char opponent = (state == 'N') ? 'B' : 'N';
        int i = x + dx, j = y + dy;

        while (isInBounds(i, j) && grid[i][j].getState() == opponent) {
            grid[i][j].setState(state); // Retourne le pion
            i += dx;
            j += dy;
        }
    }

    /**
     * Capture les pions dans toutes les directions valides.
     *
     * @param x La position X de la cellule de départ.
     * @param y La position Y de la cellule de départ.
     * @param state L'état du joueur (Noir ou Blanc).
     */
    private void capturePions(int x, int y, char state) {
        int[][] directions = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
        };

        for (int[] dir : directions) {
            int dx = dir[0], dy = dir[1];
            if (hasCapturableLine(x, y, dx, dy, state)) {
                flipLine(x, y, dx, dy, state);
            }
        }
    }

    /**
     * Vérifie si un mouvement est valide.
     * Un mouvement est valide si la cellule est vide et si au moins un pion peut être capturé.
     *
     * @param x La position X de la cellule où le pion doit être placé.
     * @param y La position Y de la cellule où le pion doit être placé.
     * @param state L'état du joueur (Noir ou Blanc).
     * @return true si le mouvement est valide, false sinon.
     */
    public boolean isValidMove(int x, int y, char state) {
        if (!isInBounds(x, y) || (grid[x][y]).getState() != ' ') {
            return false;
        }

        // Vérifie s'il y a au moins un pion à capturer
        return canCapture(x, y, state);
    }

    /**
     * Place un pion à la position (x, y) et capture les pions adverses si possible.
     *
     * @param x La position X de la cellule où le pion doit être placé.
     * @param y La position Y de la cellule où le pion doit être placé.
     * @param state L'état du joueur (Noir ou Blanc).
     * @throws IllegalArgumentException si le mouvement est invalide.
     */
    public void makeMove(int x, int y, char state) {
        if (!isValidMove(x, y, state)) {
            throw new IllegalArgumentException("Mouvement invalide !");
        }

        grid[x][y].setState(state);
        capturePions(x, y, state);
    }

    /**
     * Compte le nombre de pions d'un joueur sur le plateau.
     *
     * @param state L'état du joueur (Noir ou Blanc).
     * @return Le nombre de pions du joueur sur le plateau.
     */
    public int countPieces(char state) {
        int c = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j].getState() == state) {
                    c += 1;
                }
            }
        }
        return c;
    }
}
