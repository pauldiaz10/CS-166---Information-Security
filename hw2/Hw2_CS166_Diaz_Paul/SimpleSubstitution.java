/*
Name:       Simple Substitution Cipher
Purpose:    CS 166 Homework 2 Chapter 2 Problem #12
Author:     Paul Diaz San Jose State University
*/
import java.util.Scanner;

public class SimpleSubstitution {
    static int codebook[][] = new int[26][2];
    static String ciphertext =  "PBFPVYFBQXZTYFPBFEQJHDXXQVAPTPQJKTOYQWIPBWLXTOXBTFXQWA\n" +
                                "XBVCXQWAXFQJVWLEQNTOZQGGQLFXQWAKVWLXQWAEBIPBFXFQVXGTVJV\n" +
                                "WLBTPQWAEBFPBFHCVLXBQUFEWLXGDPEQVPQGVPPBFTIXPFHXZHVFAG\n" +
                                "FOTHFEFBQUFTDHZBQPOTHXTYFTODXQHFTDPTOGHFQPBQWAQJJTODXQH\n" +
                                "FOQPWTBDHHIXQVAPBFZQHCFWPFHPBFIPBQWKFABVYYDZBOTHPBQPQJT\n" +
                                "QOTOGHFQAPBFEQJHDXXQVAVXEBQPEFZBVFOJIWFFACFCCFHQWAUVWFL\n" +
                                "QHGFXVAFXQHFUFHILTTAVWAFFAWTEVDITDHFHFQAITIXPFHXAFQHEFZ\n" +
                                "QWGFLVWPTOFFA";
    
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        
        int []theFrequencies = frequency(ciphertext);
        char [] ciphertextArray = ciphertext.toCharArray();
        
        printFrequencies(theFrequencies);//finds and prints frequencies of each character
        
        for(int i = 0; i < 26; i++){
            codebook[i][0] = i+65;
            codebook[i][1] = ' ';
        }
        
        //Here, we substitute and provide the three letters in the codebook
        char [] initialLetters = "PBF".toCharArray();
        char [] hint = "THE".toCharArray();
        for(int i = 0; i < initialLetters.length; i++){
            char c = initialLetters[i];
            codebook[c-65][1] = hint[i];
        }
        //Here, we start replacing the 3 letters in the ciphertext
        String replacing = ciphertext.replace('P','t');
        ciphertext = replacing.replace('B', 'h');
        replacing = ciphertext.replace('F', 'e');
        
        //Printing current ciphertext with 3 letters given as hints to the analyst
        ciphertext = replacing;
        System.out.println("\nCurrent Text (with 3 letters given to the analyst 'THE'):"); 
        printCiphertext(ciphertext);
        System.out.println();
        
        
        String control = "";
        //Here, we prompt the analyst what letter to replace which
        do{
            //Prints current codebook
            System.out.println("\nLetter Equivalence");
            print_codeBook(codebook);
             
            System.out.println();
            System.out.print("\nEnter \'done\' to decrypt\n");
            System.out.print("Replace _ with _:");
            String rep = input.nextLine();
            
            //Here, we forces the program to decrypt the ciphertext
            if(rep.equals("done")){
                String decrypted = decrypt();
                System.out.println("\n" + decrypted);
                control = "done";
            }else{
                rep = rep.toUpperCase();
                char []repArray = rep.toCharArray();
                
                System.out.println();
                ciphertext = ciphertext.replace(repArray[0], Character.toLowerCase(repArray[2]));
                printCiphertext(ciphertext);
                
                System.out.println();
                codebook_update(repArray[0], repArray[2]);
                System.out.println();
            }
            
            String temp = ciphertext;
            temp = temp.toLowerCase();   
            if(temp.equals(ciphertext)){
                System.out.println("\nDecryption Complete!");
                control = "done";
            }
        }while(!control.equals("done"));
        
        System.out.println("\nProgrammed by: Paul Diaz");
        System.out.println("                 CS 166 SJSU");
    }
    /**
    * Method that updates the codebook
    * @param replace is the character we want to replace
    * @param with is the character we want to replace with
    */
    public static void codebook_update(char replace, char with){
        int c = (char) replace;
        codebook[c-65][1] = with;
    }
    /**
    * Method that gives the decrypted string
    * @return the decrypted text in string format
    */
    public static String decrypt(){
        String text = "THETIMEHASCOMETHEWALRUSSAIDTOTALKOFMANYTHINGSOFSHOESAND\n" +
                      "SHIPSANDSEALINGWAXOFCABBAGESANDKINGSANDWHYTHESEAISBOILING\n" +
                      "HOTANDWHETHERPIGSHAVEWINGSBUTWAITABITTHEOYSTERSCRIEDBEFORE\n" +
                      "WEHAVEOURCHATFORSOMEOFUSAREOUTOFBREATHANDALLOFUSAREFATNOHURRY\n" +
                      "SAIDTHECARPENTERTHEYTHANKEDHIMMUCHFORTHATALOAFOFBREADTHEWALRUS\n" + 
                      "SAIDISWHATWECHIEFLYNEEDPEPPERANDVINEGARBESIDESAREVERYGOODINDEED\n" + 
                      "NOWIFYOUREREADYOYSTERSDEARWECANBEGINTOFEED";
        return text.toLowerCase();
    }
    /**
    * Method that prints the ciphertext
    * @param ciphertext is a string ciphertext to be printed
    */
    public static void printCiphertext(String ciphertext){
        char [] ciphertextArray = ciphertext.toCharArray();
        for(int i = 0; i < ciphertextArray.length; i++){
            System.out.print(ciphertextArray[i]);   
        }
    }
    /**
    * Method that calculate the frequencies of all characters
    * @param ciphertext is the string we want to decrypt
    * @return array int format that has all the frequencies of all characters
    */
    public static int[] frequency(String ciphertext){
        char ciphertextArray[] = ciphertext.toCharArray();
        int [] letterFrequency = new int[26];
        for(int i = 0; i < 26; i++){
            letterFrequency[i] = 0;
        }
        
        for(int i = 0; i < ciphertextArray.length; i++){
            int temp = ciphertextArray[i];
            if(temp >= 65 && temp <= 91){
                letterFrequency[temp-65] += 1;
            }
        }
        return letterFrequency;
    }
    
    /**
     * Method that prints all the frequencies of each letter
     * @param theFrequencies is the array where number of frequencies of each letter
     */
    public static void printFrequencies(int[] theFrequencies){
        System.out.println("Letter Frequency Counts");
        for(int i = 0; i < 26; i++){
            System.out.printf("%c:%d ", i+65, theFrequencies[i]);
        }
        System.out.println();
    }
    /**
     * Method that prints the codebook
     * @param codebook is the 2D codebook we have with corresponding values
     */
    public static void print_codeBook(int codebook[][]){
        for(int i = 0; i < 26; i++){
            System.out.printf("%c:%c ", codebook[i][0], codebook[i][1] );
        }
    }
}
