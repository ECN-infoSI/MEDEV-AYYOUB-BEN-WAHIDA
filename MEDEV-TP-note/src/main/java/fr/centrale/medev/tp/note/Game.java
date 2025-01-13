/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.centrale.medev.tp.note;

import java.util.Scanner;

/**
 *
 * @author samer
 */
public class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    
    public Game(){
        
    }
   // methode qui initialize Board et les Joueurs 
   public void start(){
       Scanner scanner = new Scanner(System.in);
       board.initialize();
       System.out.println("insert Player 1 name (black pieces):");
       String player1Name = scanner.nextLine();
       player1 = new Player(player1Name, 'N');
       System.out.println("insert Player 2 name (white pieces):");
       String player2Name = scanner.nextLine();
       player2 = new Player(player2Name, 'B');
       currentPlayer = player1;
       
   }
   
   public void takeTurn(){
       Scanner scanner = new Scanner(System.in);
       int x = -1, y = -1; // Variables for the position
       while (true) {
            System.out.print("Enter the desired position (e.g., d3): ");
            String input = scanner.nextLine().toLowerCase().trim();

            // Validate input format
            if (input.length() == 2) {
                char letter = input.charAt(0);
                char number = input.charAt(1);

                // Check if the letter is between 'a' and 'h'
                if (letter >= 'a' && letter <= 'h') {
                    // Check if the number is between '1' and '8'
                    if (number >= '1' && number <= '8') {
                        // Convert letter to y (1 to 8) and number to x
                        y = letter - 'a' + 1; // 'a' -> 1, 'b' -> 2, ..., 'h' -> 8
                        x = Character.getNumericValue(number); // '1' -> 1, ..., '8' -> 8
                        break; // Exit the loop when the input is valid
                    }
                }
            }

            // If input is invalid, prompt again
            System.out.println("Invalid input. Please enter a valid position (e.g., d3).");
        }

        System.out.println("You entered: x = " + x + ", y = " + y);
        
    }
       
   }

