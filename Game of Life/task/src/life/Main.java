package life;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void print(int n, String[][] mat){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(mat[i][j]);
            }
            System.out.println();
        }
    }

    public static int gener(int n, int h){
        if(h == n){
            h = 0;
        }
        else if(h == -1){
            h = n - 1;
        }
        return h;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        Random ran = new Random();
        String[][] mat = new String[n][n];
        String[][] matN = new String[n][n];
        int count;
        int ali = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (ran.nextBoolean()) {
                    mat[i][j] = "0";
                    ali++;
                } else {
                    mat[i][j] = " ";
                }
            }
        }
        for(int j = 0; j < mat.length; j++){
            matN[j] = mat[j].clone();
        }
        System.out.println("Generations #1");
        System.out.println("Alive: " + ali);
        print(n, matN);
        for(int i = 0; ali > 0; i++){
            for(int j = 0; j < n; j++){
                for(int g = 0; g < n; g++){
                    count = 0;
                    if(mat[gener(n, j - 1)][gener(n, g - 1)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j - 1)][gener(n, g)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j - 1)][gener(n, g + 1)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j)][gener(n, g - 1)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j)][gener(n, g + 1)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j + 1)][gener(n, g - 1)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j + 1)][gener(n, g)] == "0"){
                        count++;
                    }
                    if(mat[gener(n, j + 1)][gener(n, g + 1)] == "0"){
                        count++;
                    }
                    if((count < 2 || count > 3) && mat[j][g] == "0"){
                        matN[j][g] = " ";
                    }
                    else if(mat[j][g] == " " && count == 3){
                        matN[j][g] = "0";
                    }
                }
            }
            for(int j = 0; j < mat.length; j++){
                mat[j] = matN[j].clone();
            }
            ali = 0;
            Thread.sleep(1500);
            System.out.println("\f");
            System.out.println("Generations #" + (i + 2));
            for(int j = 0; j < n; j++){
                for(int g = 0; g < n; g++){
                    if(matN[j][g] == "0"){
                        ali++;
                    }
                }
            }
            System.out.println("Alive: " + ali);
            print(n, mat);
        }
    }
}

