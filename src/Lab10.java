/*  
Description: Lab 10 
Author: alex rodriguez 
Date: 4.28.22
Bugs: A) if the tree gets too full, the lower subtrees can overlap as 
width is currently connected to the height of the tree. 
      B) it won't validate your input after the initial build so be careful!
Reflection: you weren't kidding. for an easy lab, this was the toughest one yet. 
upside I'm damn comfortable working with trees now. Wish I had started sooner to 
make it a little cleaner


DISCLAIMER : height is +1 in my bbst class. suspend your beliefs for a moment. I did 
attempt to use "level" as much as I could to suggest that we're starting at 1 and counting
up as we climb the tree from the root to the leaves. (it just worked better when calculating
coordinates for the stdDraw.)
*/ 
public class Lab10 {
    public static void main(String[] args) throws Exception {
        CustomTreeArtist.terminalInterface();
    }
}

