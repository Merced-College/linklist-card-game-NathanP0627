//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;



public class CardGame {
	
	private static LinkList cardList = new LinkList();  // make list

	public static void main(String[] args) {

		// File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print the loaded cards
        System.out.println("Cards loaded:");
        cardList.displayList();
		
		Card[] playerHand = new Card[5];
		for(int i = 0; i < playerHand.length; i++)
			playerHand[i] = cardList.getFirst();
		
		System.out.println("players hand");
		for(int i = 0; i < playerHand.length; i++)
			System.out.println(playerHand[i]);
		
		System.out.println();
		System.out.println("the deck");
		cardList.displayList();

        // Setup computer hand and player totals
        Card[] computerHand = new Card[5];
        computerHand[0] = cardList.getFirst();
        computerHand[1] = cardList.getFirst();

        int playerTotal = playerHand[0].getCardValue() + playerHand[1].getCardValue();
        int computerTotal = computerHand[0].getCardValue() + computerHand[1].getCardValue();

        System.out.println("Your starting total: " + playerTotal);
        System.out.println("Computer's starting total: " + computerTotal);

        // Add player hit/stay input loop
        Scanner scnr = new Scanner(System.in);
        int playerIndex = 2;

        while (playerTotal < 21 && playerIndex < 5) {
            System.out.print("Type 'hit' to draw another card, or any other key to stay: ");
            String input = scnr.nextLine();

            if (input.equalsIgnoreCase("hit")) {
                playerHand[playerIndex] = cardList.getFirst();
                System.out.println("You drew: " + playerHand[playerIndex]);
                playerTotal += playerHand[playerIndex].getCardValue();
                playerIndex++;
                System.out.println("New total: " + playerTotal);
            } else {
                break;
            }
        }
        
	}//end main

}//end class
