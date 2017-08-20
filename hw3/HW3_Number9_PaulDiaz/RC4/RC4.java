/**
 * HW3 CS166: Chapter 3 and 4
 * @author Paul Williams Diaz
 * San Jose State University
 * Reference: http://paginas.fe.up.pt/~ei10109/ca/rc4.html
 * Reference: http://merlot.usc.edu/cs531-s11/papers/Fluhrer01a.pdf
 */
import java.util.ArrayList;
public class RC4 {
    
    private static final int[] keys = {0x1A, 0x2B, 0x3C, 0x4D, 0x5E, 0x6F, 0x77};
    private static final ArrayList<Integer> S = new ArrayList<>();
    private static final ArrayList<Integer> K = new ArrayList<>();
    
    private final static int SIZE = 256;
    private final static int PROBLEMAKEYSTREAM = 100;
    private final static int PROBLEMBKEYSTREAM = 1000;
    
    public static void main(String[]args){
        int j = 0;
        for(int i = 0; i < SIZE; i++){
            S.add(i, i);
            K.add(i, keys[i % keys.length]);
        }
        for(int i = 0; i < SIZE; i++){
            j = (j + S.get(i) + K.get(i)) % SIZE;
            switchThem(i, j);
        }
        System.out.println("Permutation S and indices i and j after Initialization");
        print16x16Array();
       
        System.out.println();
        System.out.println("Permutation S and indices i and j after 100 bytes keystream");
        keystreamGeneration(PROBLEMAKEYSTREAM);
        print16x16Array();
        
        System.out.println();
        System.out.println("Permutation S and indices i and j after 1000 bytes keystream");
        keystreamGeneration(PROBLEMBKEYSTREAM);
        print16x16Array();
        
        System.out.println();
    }
    /**
     * Method that prints our 16x16 array
     */
    public static void print16x16Array() {
        for (int i = 0; i < S.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(Integer.toHexString(S.get(i)).toUpperCase());
            if(sb.length() < 2){
                sb.insert(0, '0');
            }
            if (i % 16 == 0) {
                System.out.print("\n");
            }
            System.out.printf("%4s", sb);
        }
        System.out.println();
    }   
    /**
     * Method that switches i and j
     * @param i is location where j is supposed to be
     * @param j is the number to be switch in instead
     */
    public static void switchThem(int i, int j){
        int temp1 = S.get(i);
        int temp2 = S.get(j);
        S.set(i, temp2);
        S.set(j, temp1);
    }
    /**
     * Reference: http://paginas.fe.up.pt/~ei10109/ca/rc4.html
     * Reference: http://merlot.usc.edu/cs531-s11/papers/Fluhrer01a.pdf
     * Method that generates keystream 
     * @param bytesKeyStream is the byte keystream to be generated
    */
    public static void keystreamGeneration(int bytesKeyStream){
        int i = 0, j = 0, keystream = 0;
        for(int k = 0; k < bytesKeyStream; k++){
            i = (i + 1) % SIZE;
            j = (j + S.get(i)) % SIZE;
            switchThem(i, j);
            int t = (S.get(i) + S.get(j)) % SIZE;
            keystream = S.get(t);
        }
    }
}
