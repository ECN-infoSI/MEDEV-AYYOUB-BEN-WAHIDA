package fr.centrale.medev.tp.note;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests unitaires pour la classe Board.
 * Ces tests vérifient le bon fonctionnement des fonctionnalités de la classe Board,
 * telles que l'affichage du plateau, la validité des mouvements, la capture des pions, etc.
 * 
 * @author Nadhem
 */
public class BoardTest {

    /**
     * Test de l'affichage du plateau de jeu.
     * Vérifie que l'affichage ne lève pas d'exception.
     */
    @Test
    public void testDisplay() {
        System.out.println("Test de l'affichage du plateau");
        Board instance = new Board();
        instance.display();
        // L'affichage n'est pas testé automatiquement, mais on vérifie qu'il n'y a pas d'exception.
        assertDoesNotThrow(() -> instance.display(), "L'affichage a levé une exception.");
    }

    /**
     * Test d'un mouvement valide.
     * Vérifie qu'un coup est valide lorsqu'il peut capturer des pions.
     */
    @Test
    public void testIsValidMove_Valid() {
        System.out.println("Test d'un mouvement valide");
        Board instance = new Board();

        // Coup valide pour le joueur noir au début du jeu
        int x = 2, y = 3;
        char state = 'N';
        boolean result = instance.isValidMove(x, y, state);

        assertTrue(result, "Le mouvement devrait être valide pour Noir.");
    }

    /**
     * Test d'un mouvement invalide.
     * Vérifie qu'un coup est invalide lorsqu'il essaie de jouer sur une case déjà occupée.
     */
    @Test
    public void testIsValidMove_Invalid() {
        System.out.println("Test d'un mouvement invalide");
        Board instance = new Board();

        // Coup invalide : case déjà occupée
        int x = 3, y = 3;
        char state = 'N';
        boolean result = instance.isValidMove(x, y, state);

        assertFalse(result, "Le mouvement devrait être invalide pour une case déjà occupée.");
    }

    /**
     * Test du placement d'un pion valide.
     * Vérifie qu'un placement valide de pion ne lève pas d'exception.
     */
    @Test
    public void testMakeMove_Valid() {
        System.out.println("Test d'un placement de pion valide");
        Board instance = new Board();

        // Coup valide pour Noir
        int x = 2, y = 3;
        char state = 'N';
        assertDoesNotThrow(() -> instance.makeMove(x, y, state), "Le placement du pion valide a échoué.");
    }

    /**
     * Test du placement d'un pion invalide.
     * Vérifie qu'un placement invalide de pion lève une exception.
     */
    @Test
    public void testMakeMove_Invalid() {
        System.out.println("Test d'un placement de pion invalide");
        Board instance = new Board();

        // Coup invalide : case déjà occupée
        int x = 3, y = 3;
        char state = 'N';

        assertThrows(IllegalArgumentException.class, () -> instance.makeMove(x, y, state),
                "Un mouvement invalide aurait dû lever une exception.");
    }

    /**
     * Test de la capture de pions.
     * Vérifie que la capture de pions se fait correctement après un coup valide.
     */
    @Test
    public void testCapturePions() {
        System.out.println("Test de la capture de pions");
        Board instance = new Board();

        // Noir joue en (2,3), ce qui devrait capturer un pion Blanc en (3,3)
        int x = 2, y = 3;
        char state = 'N';
        instance.makeMove(x, y, state);

        // Vérifions si le pion Blanc en (3,3) a été capturé
        assertEquals('N', instance.grid[3][3].getState(), "Le pion Blanc aurait dû être capturé par Noir.");
    }

    /**
     * Test de l'état initial du plateau.
     * Vérifie que les pions sont correctement placés au centre du plateau au début du jeu.
     */
    @Test
    public void testInitialSetup() {
        System.out.println("Test de l'état initial du plateau");
        Board instance = new Board();

        // Vérifions l'état initial des pions au centre
        assertEquals('B', instance.grid[3][3].getState(), "La case (3,3) aurait dû contenir un pion Blanc.");
        assertEquals('B', instance.grid[4][4].getState(), "La case (4,4) aurait dû contenir un pion Blanc.");
        assertEquals('N', instance.grid[3][4].getState(), "La case (3,4) aurait dû contenir un pion Noir.");
        assertEquals('N', instance.grid[4][3].getState(), "La case (4,3) aurait dû contenir un pion Noir.");
    }
}
