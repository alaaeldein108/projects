package vectorass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Vectorass  {

    int vectorWith;
    int vectorHight;
    int numberOfBits;
    int X_axis ;
    int Y_axis ;
    List<Block> Blocks;
    List<Block> AverageBlocks;
    String[] result;

    public Vectorass() {
        this.vectorWith = 2;
        this.vectorHight = 2;
        this.numberOfBits = 2;
        this.Blocks = new ArrayList<Block>();
        this.AverageBlocks = new ArrayList<Block>();
        this.X_axis = 0 ;
        this.Y_axis = 0 ;
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GUI Obj = new GUI () ;
        Obj.main(args);
        //int[][] pexels = {{1, 2, 7, 9, 4, 11}, {3, 4, 6, 6, 12, 12}, {4, 9, 15, 14, 9, 9}, {10, 10, 20, 18, 8, 8}, {4, 3, 17, 16, 1, 4}, {4, 5, 18, 18, 5, 6}};
        //VectorAss obj = new VectorAss();
//        ImageClass image = new ImageClass() ;
//        obj.numberOfBits = (int)java.lang.Math.pow(2, obj.numberOfBits) ;
//        int[][] pexels = image.readImage("D:\\programing\\Java\\VectorAss\\cameraMan.jpg") ;
//        obj.X_axis =  pexels[0].length / obj.vectorWith ;
//        obj.Y_axis =  pexels.length / obj.vectorHight  ;
//        obj.Blocks = obj.getBlock(pexels);
//        obj.ready();
//        obj.result = obj.compress() ;
//        obj.writeCompressFile("D:\\programing\\Java\\VectorAss\\Ahmed.txt");
//        obj.ReadCompressFile("D:\\programing\\Java\\VectorAss\\Ahmed.txt");
//        obj.Decompress();
//        pexels = obj.getArr() ;
//        image.writeImage(pexels, "D:\\programing\\Java\\VectorAss\\cameraManOut.jpg");
       

    }

    
    
    
    public List<Block> getBlock( int[][] pexels) {
        List<Block> blocks = new ArrayList<Block>();
        int X_deff = pexels.length % this.vectorWith;
        int Y_deff = pexels[0].length % this.vectorHight;
        for (int i = 0; i < (pexels.length - X_deff); i += vectorHight) {
            for (int j = 0; j < (pexels[i].length - Y_deff); j += vectorWith) {
                Block newblock = new Block(vectorWith, vectorHight);
                for (int h = 0; h < vectorHight; h++) {
                    for (int w = 0; w < vectorWith; w++) {
                        newblock.block[h][w] = pexels[i + h][j + w];
                    }
                }
                blocks.add(newblock);
            }
        }
        return blocks;
    }

    public int[][] getArr() {
        int counter = 0;
        int[][] pexels = new int[this.Y_axis * this.vectorHight][this.X_axis * this.vectorWith];
        for (int i = 0; i < this.Y_axis; i++) {
            for (int j = 0; j < this.X_axis; j++) {
                Block copyBlock = this.Blocks.get(counter);
                counter++;
                for (int h = 0; h < this.vectorHight; h++) {
                    for (int w = 0; w < this.vectorWith; w++) {
                        pexels[h + i * this.vectorHight][w + j * this.vectorWith] = (int) copyBlock.block[h][w];
                    }
                }
            }
        }
        return pexels;
    }
    
    
    
    
    ////////////////////////////////////Assiment \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public void ready() {
        Block newBlock = new Block(this.vectorWith, this.vectorHight);
        this.AverageBlocks.add(newBlock);
        Block[] testList = new Block[0];
        do {
            if (!this.isEqual(this.AverageBlocks, testList)) {
                testList = this.equal(this.AverageBlocks, testList);
                this.nearestVector();
            }

            int size = this.AverageBlocks.size();
            for (int i = 0; (i < size) && (size < this.numberOfBits); i++) {
                this.Splitting();
            }

        } while (!this.isEqual(this.AverageBlocks, testList));
    }

    public void Splitting() {
        Block rightBlock = new Block(this.vectorHight, this.vectorWith);
        Block LiftBlock = new Block(this.vectorHight, this.vectorWith);
        for (int i = 0; i < this.vectorHight; i++) {
            for (int j = 0; j < this.vectorWith; j++) {
                int IntNumber = (int) this.AverageBlocks.get(0).block[i][j];
                if (IntNumber == this.AverageBlocks.get(0).block[i][j]) {
                    rightBlock.block[i][j] = IntNumber - 1;
                    LiftBlock.block[i][j] = IntNumber + 1;
                } else {
                    rightBlock.block[i][j] = IntNumber;
                    LiftBlock.block[i][j] = IntNumber + 1;
                }
            }

        }
        this.AverageBlocks.add(rightBlock);
        this.AverageBlocks.add(LiftBlock);
        this.AverageBlocks.remove(0);
        this.toBinary();
    }

    public void nearestVector() {
        for (int i = 0; i < this.AverageBlocks.size(); i++) {
            this.AverageBlocks.get(i).nearestVector.clear();
        }
        for (int x = 0; x < this.Blocks.size(); x++) {
            double min = 2147483647;
            int index = -1;

            for (int counter = 0; counter < this.AverageBlocks.size(); counter++) {
                double sum = 0;
                for (int i = 0; i < this.vectorHight; i++) {
                    for (int j = 0; j < this.vectorWith; j++) {
                        double X = this.Blocks.get(x).block[i][j] - this.AverageBlocks.get(counter).block[i][j];
                        if (X < 0) {
                            X *= -1;
                        }
                        sum += X;
                    }
                }

                if (sum < min) {
                    min = sum;
                    index = counter;
                }
            }
            this.AverageBlocks.get(index).nearestVector.add(this.Blocks.get(x));
            this.Blocks.get(x).Code = this.AverageBlocks.get(index).Code;
        }
        for (int index = 0; index < this.AverageBlocks.size(); index++) {
            this.AverageBlocks.get(index).CalculateAverage();
        }
    }

    public boolean isEqual(List<Block> X, Block[] Y) {
        if (X.size() != Y.length) {
            return false;
        }
        boolean flag = true;
        for (int index = 0; (index < X.size() && flag); index++) {
            for (int i = 0; (i < this.vectorHight && flag); i++) {
                for (int j = 0; (j < this.vectorWith && flag); j++) {
                    if (X.get(index).block[i][j] != Y[index].block[i][j]) {
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    public Block[] equal(List<Block> X, Block[] Y) {
        Y = new Block[X.size()];
        for (int index = 0; index < X.size(); index++) {
            Y[index] = new Block(this.vectorWith, this.vectorHight);
            for (int i = 0; i < this.vectorHight; i++) {
                for (int j = 0; j < this.vectorWith; j++) {
                    Y[index].block[i][j] = X.get(index).block[i][j];
                }
            }
        }
        return Y;
    }

    public void toBinary() {
        int bits = 0, counter = this.numberOfBits;
        while (counter != 1) {
            bits++;
            counter /= 2;
        }
        for (int i = 0; i < this.AverageBlocks.size(); i++) {
            String Code = "";
            int X = i;
            for (int j = 0; j < bits; j++) {
                int bit = X % 2;
                X /= 2;
                Code = bit + Code;
            }
            this.AverageBlocks.get(i).Code = Code;
        }
    }
    
    public void compress() {
        this.result = new String[this.Blocks.size()];
        for (int i = 0; i < this.Blocks.size(); i++) {
            this.result[i] = this.Blocks.get(i).Code;
        }
       
    }
   
    public int searchCode(String Code){
        for(int i = 0 ; i < this.AverageBlocks.size() ; i++)
            if (this.AverageBlocks.get(i).Code.equals(Code))
                return i ;
        return -1 ;
    }
    
    public void Decompress(){
        this.Blocks.clear();
        for (String Arr1 : this.result) {
            int index = this.searchCode(Arr1);
            this.Blocks.add(this.AverageBlocks.get(index)) ;
        }
        
    }
    
    ///////////////////////Show Functions \\\\\\\\\\\\\\\\\\\\\\
    
    public void ShowPexels(int[][] pexels) {
        for (int[] pexel : pexels) {
            for (int j = 0; j < pexel.length; j++) {
                System.out.print(pexel[j] + "  ");
            }
            System.out.println();
        }
    }
    
    public void ShowBlocks() {
        this.Blocks.forEach((Block) -> {
            for (double[] block : Block.block) {
                for (int j = 0; j < block.length; j++) {
                    System.out.print(block[j] + "        ");
                }
                System.out.println("\n");
            }
            System.out.println("*************************************");
        });

    }
     
    public String ShowResult (){
        String Text = "" ;
         for (int i = 0; i < this.result.length; i++){
             Text +=  this.result[i] + " " ;
             if (i % 62 == 61)
                Text += "\n";
         }
         return Text ;
    }
     
     /////////////////////File \\\\\\\\\\\\\\\\\\\\\\
    
    public void writeCompressFile(String fileName){
        try{
        File file = new File(fileName) ;
        if (!file.exists()){
            file.createNewFile() ;
        }
            try (PrintWriter output = new PrintWriter(file)) {
               output.println((int) this.vectorWith);
               output.println((int) this.vectorHight);
               output.println((int) this.X_axis);
               output.println((int) this.Y_axis);
               output.println((int) this.numberOfBits);
               output.println((int) this.result.length );

               for (int index = 0 ; index < this.AverageBlocks.size() ; index++ ){
                   for (int i = 0 ; i < this.vectorHight ; i++ ){
                       for (int j = 0 ; j < this.vectorWith ; j++)
                           output.println((double) this.AverageBlocks.get(index).block[i][j]);
                   }
               }
               
               for ( int i = 0 ; i < this.result.length ; i++  ){
                   output.println( this.result[i] );
               }
               output.close();
            }
        }catch(IOException e){
            System.out.println("File not creat");
        }
    }
    
    public void ReadCompressFile(String fileName){
        try{
            this.AverageBlocks.clear();
            this.Blocks.clear();
            
            try (Scanner readFile = new Scanner(new File(fileName))) {
                this.vectorWith = readFile.nextInt();
                this.vectorHight = readFile.nextInt() ;
                this.X_axis = readFile.nextInt() ;
                this.Y_axis = readFile.nextInt() ;
                this.numberOfBits = readFile.nextInt() ;
                int Size = readFile.nextInt() ;
                

                for ( int index = 0 ; index < this.numberOfBits ; index++){
                    Block New = new Block(this.vectorWith , this.vectorHight) ;
                    for ( int i = 0 ; i < this.vectorHight ; i++ ){
                        for ( int j = 0 ; j < this.vectorWith ; j++ ){
                            New.block[i][j] =(double) readFile.nextDouble() ;
                            System.out.println(New.block[i][j]) ;
                        }
                    }
                    this.AverageBlocks.add(New) ;
                }
                
                this.toBinary();
                readFile.nextLine() ;
                this.result = new String [Size] ;
                System.out.println(Size) ;
                for (int i = 0  ; i < Size ; i++ ){
                    
                    this.result[i] = readFile.nextLine() ;
                    System.out.println(this.result[i] + "  " + i) ;
                }
                    
                    
                readFile.close();
            }
            
        }catch(FileNotFoundException e){
           System.out.println("this file not open ") ;
        }
    }
       
}