//author @Kollen Gruizenga
//class: WarGame
//this class is the main runner for the program

package wargame;

import java.util.ArrayList;

public class WarGame {
    
    private static ArrayList<Card> warPile = new ArrayList<Card>();
    private static ArrayList<Card> p1deck = new ArrayList<Card>();
    private static ArrayList<Card> p2deck = new ArrayList<Card>();
    private static int p1=25, p2=25, rounds=0;
    private static boolean canWar=false;
    
    public WarGame(){
        Deck deal = new Deck();
        deal.shuffle();
        
        for(Card n : deal.deal(26)){
            p1deck.add(n);
        }
        for(Card d : deal.deal(26)){
            p2deck.add(d);
        }
    }
        
    public static void main(String[] args) {
        WarGame game = new WarGame();
        
        while (p1deck.size() < Deck.MAX_SIZE && p2deck.size() < Deck.MAX_SIZE){
            rounds++;
            System.out.println("---------------");
            //print out the draws
            System.out.println("P1 draws: " + p1deck.get(p1).toString());
            System.out.println("P2 draws: " + p2deck.get(p2).toString());
            
            //compare NOW.
            if (p1deck.get(p1).rank > p2deck.get(p2).rank){//if p1 wins
                System.out.println("P1 wins the round!");
                p1deck.add(0, p1deck.remove(p1));
                p1deck.add(0, p2deck.remove(p2));
                update();
            }
            else if (p1deck.get(p1).rank < p2deck.get(p2).rank){//if p2 wins
                System.out.println("P2 wins the round!");
                p2deck.add(0, p2deck.remove(p2));
                p2deck.add(0, p1deck.remove(p1));
                update();
            }
            else if ((p1deck.get(p1).rank) == (p2deck.get(p2).rank)) {//if WAR!
                if(warEligible() == 1){//if someone aint be able
                    System.out.println("WAR, but P1 isn't eligible!");
                    System.out.println("P2 wins game by default!");
                    break;
                }
                else if(warEligible() == 2){
                    System.out.println("WAR, but P2 isn't elibile!");
                    System.out.println("P1 wins game by default!");
                    break;
                }
                else System.out.println("WAR");
                
                while(!canWar){//canWar will be true if war is done.
                    doWar();
                    
                }//if war has been completed.
                switch (doWar()){
                    case 1:{
                        for(Card w1 : warPile){
                            p1deck.add(0, w1);
                        }
                        System.out.println("P1 wins war");
                        break;
                    }
                    case 2:{
                        for(Card w2 : warPile){
                            p2deck.add(0, w2);
                        }
                        System.out.println("P2 wins war");
                        break;
                    }
                }
                update();
            }
            else System.out.println("there is something fundamentally wrong with this program");
            
        }
        System.out.println(p1deck.size());
        System.out.println(p2deck.size());
        if(p1deck.size() == Deck.MAX_SIZE) System.out.println("P1 WINS THE GAME AFTER: " + rounds + " ROUNDS!");
        else if(p2deck.size() == Deck.MAX_SIZE) System.out.println("P2 WINS THE GAME AFTER: " + rounds + " ROUNDS!");
    }
    
    static private int doWar(){

        warPile.add(0, p1deck.remove(p1));//card that started war
        warPile.add(0, p1deck.remove(p1-1));
        warPile.add(0, p1deck.remove(p1-2));
        
        Card w1 = p1deck.remove(p1-3);
        warPile.add(0, w1);
        System.out.println("P1 pulls: " + warPile.get(2).toString() + " , " + warPile.get(1).toString() + " , " + warPile.get(0).toString());
        
        warPile.add(0, p2deck.remove(p2));//card that started war
        warPile.add(0, p2deck.remove(p2-1));
        warPile.add(0, p2deck.remove(p2-2));
        
        Card w2 = p2deck.remove(p2-3);
        warPile.add(0, w2);
        System.out.println("P2 pulls: " + warPile.get(2).toString() + " , " + warPile.get(1).toString() + " , "+ warPile.get(0).toString());

        if (w1.rank == w2.rank) canWar = false;
        else canWar = true;
        
        update();
        System.out.println(p1deck.size());
        System.out.println(p2deck.size());
        if (w1.rank > w2.rank) return 1;
        else return 2;
        
    }
    
    static private void update(){
        //update the tings
        p1 = p1deck.size() - 1;
        p2 = p2deck.size() - 1;
    }
    
    static private int warEligible(){
        if(p1deck.size() < 4) return 1;
        if(p2deck.size() < 4) return 2;
        return -1;
    }
}
