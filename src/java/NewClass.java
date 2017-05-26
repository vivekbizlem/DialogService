
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Akhilesh yadav
 */
public class NewClass {
    public static void main(String[] args) {
        Random rand = new Random(); 
 int pickedNumber = rand.nextInt(100000)+10000; 
        System.out.println("pickedNumber : "+pickedNumber);
        System.out.println("rand : "+rand.nextInt());
    }
}
