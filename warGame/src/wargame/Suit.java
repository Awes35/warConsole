//author @Kollen Gruizenga
//class: Suit
//this class is to define the Suits used for Card objects

package wargame;

public class Suit implements Comparable{
    public static final Suit spade = new Suit(4, "spades");
    public static final Suit heart = new Suit(3, "hearts");
    public static final Suit diamond = new Suit(2, "diamonds");
    public static final Suit club = new Suit(1, "clubs");
    
    private int order;
    private String name;
    
    private Suit(int ord, String nm){
        name = nm;
        order = ord;
    }
    
    public int compareTo(Object other){
        if (! (other instanceof Suit))
            throw new IllegalArgumentException("Parameter must be a Suit");
        Suit otherSuit = (Suit)other;
        return order - otherSuit.order;
    }
    
    public String toString(){
        return name;
    }
    
}
