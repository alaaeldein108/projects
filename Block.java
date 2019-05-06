package vectorass;

import java.util.ArrayList;
import java.util.List;
public class Block {
    double[][] block ;
    List <Block> nearestVector ;
    String Code ;
    public Block(int width , int height){
        this.block = new double[height][width];
        for (int i = 0 ; i < this.block.length ; i++){
            for (int j = 0 ; j < this.block[i].length ; j++){
                this.block[i][j] = 0.00 ;
            }
        }
        this.nearestVector = new ArrayList<Block>();
        this.Code = "" ;
    }
    
    public void CalculateAverage(){
        for (int i = 0 ; i < this.block.length ; i++ ){
            for (int j = 0 ; j < this.block[i].length ; j++)
                this.block[i][j] = 0.00 ;
        }
        
        for (Block nBlock : this.nearestVector) {
            for (int i = 0 ; i < this.block.length ; i++ ){
                for (int j = 0 ; j < this.block[i].length ; j++)
                    this.block[i][j] += nBlock.block[i][j] ;
            } 
        }
        
        for (int i = 0 ; i < this.block.length ; i++ ){
            for (int j = 0 ; j < this.block[i].length ; j++)
                this.block[i][j] /= this.nearestVector.size() ;
        }
        
    }
    
    public void ShowBlocks() {
        this.nearestVector.forEach((Block) -> {
            System.out.println("in block class");
            for (double[] block : Block.block) {
                for (int j = 0; j < block.length; j++) {
                    System.out.print(block[j] + "        ");
                }
                System.out.println("\n");
            }
            System.out.println("*************************************");
        });

    }
    
}