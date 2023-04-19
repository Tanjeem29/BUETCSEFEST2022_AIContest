import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
 class Minion implements Comparable<Minion>{
     int id;
     int posR;
     int posC;
     int health;
     int timeout;
     int d;
     public Minion(int a, int b, int c, int d, int e){
         id = a;
         posR = b;
         posC = c;
         health = d;
         timeout = e;
     }
     public Minion(Minion m){
         id = m.id;
         posR = m.posR;
         posC = m.posC;
         health = m.health;
         timeout = m.timeout;
     }
     public String toString(){
             return id + " " + posR + " " + posC + " " + health + " " + timeout + " " + d; 
    }
    public int compareTo(Minion m) {
        return this.d - m.d;
    }
 }
class Player {
    static int dist(int x1, int y1, int x2, int y2){
        return Math.abs(x1-x2)  + Math.abs(y1-y2) ; 
    }
    static int canSee(Minion m1, Minion m2, StringBuilder[] map){
        int seeFlag = 1;
        if(m1.posR == m2.posR){
            for(int i=Math.min(m1.posC, m2.posC); i<=Math.max(m1.posC, m2.posC); i++){
                //System.err.print(m1);
                if(map[m1.posR].charAt(i) == '#'){
                    //seeFlag = 0;
                    //break;
                    System.err.println("--------1------");
                    System.err.println(m1);
                    System.err.println(m2);
                    System.err.println("----------------");
                    return 0;
                }
            }
            System.err.println("--------2------");
            System.err.println(m1);
            System.err.println(m2);
            System.err.println("----------------");
            return 1;
        }
        else if(m1.posC == m2.posC){
            for(int i=Math.min(m1.posR, m2.posR); i<=Math.max(m1.posR, m2.posR); i++){
                //System.err.println(map[i].charAt(m1.posC));
                if(map[i].charAt(m1.posC) == '#'){
                    //seeFlag = 0;
                    //break;
                    
                    System.err.println("-------3-------");
                    System.err.println(m1);
                    System.err.println(m2);
                    System.err.println("----------------");
                    return 0;
                }
            }
            System.err.println("--------4-------");
            System.err.println(m1);
            System.err.println(m2);
            System.err.println("----------------");
            return 1;
        }
        System.err.println("--------5-------");
        System.err.println(m1);
        System.err.println(m2);
        System.err.println("----------------");
        return 0;
    }
    static int canSee2(Minion m1, Minion m2, StringBuilder[] map, HashMap <Integer, Minion> prevMap){
        int seeFlag = 1;
        if(m1.posR == m2.posR){
            for(int i=Math.min(m1.posC, m2.posC); i<=Math.max(m1.posC, m2.posC); i++){
                //System.err.print(m1);
                if(map[m1.posR].charAt(i) == '#'){
                    //seeFlag = 0;
                    //break;
                    System.err.println("--------1------");
                    System.err.println(m1);
                    System.err.println(m2);
                    System.err.println("----------------");
                    return 0;
                }
            }
            System.err.println("--------2------");
            System.err.println(m1);
            System.err.println(m2);
            System.err.println("----------------");
            if(prevMap.get(m2.id) != null){
                return 1;
            }
            else{
                return 0;
            }
            
        }
        else if(m1.posC == m2.posC){
            for(int i=Math.min(m1.posR, m2.posR); i<=Math.max(m1.posR, m2.posR); i++){
                //System.err.println(map[i].charAt(m1.posC));
                if(map[i].charAt(m1.posC) == '#'){
                    //seeFlag = 0;
                    //break;
                    
                    System.err.println("-------3-------");
                    System.err.println(m1);
                    System.err.println(m2);
                    System.err.println("----------------");
                    return 0;
                }
            }
            System.err.println("--------4-------");
            System.err.println(m1);
            System.err.println(m2);
            System.err.println("----------------");
            if(prevMap.get(m2.id) != null){
                return 1;
            }
            else{
                return 0;
            }
        }
        System.err.println("--------5-------");
        System.err.println(m1);
        System.err.println(m2);
        System.err.println("----------------");
        return 0;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int height = in.nextInt();
        int width = in.nextInt();
        StringBuilder[] Map = new StringBuilder[height];
        for (int i = 0; i < height; i++) {
            //String row = in.next();
            Map[i] = new StringBuilder(in.next());
        }
        int loopcnt = 0;
        int myFlagBaseR = in.nextInt();
        int myFlagBaseC = in.nextInt();
        int opponentFlagBaseR = in.nextInt();
        int opponentFlagBaseC = in.nextInt();
        String fireName = in.next();
        int firePrice = in.nextInt();
        int fireDamage = in.nextInt();
        String freezeName = in.next();
        int freezePrice = in.nextInt();
        int freezeDamage = in.nextInt();
        String mineName = in.next();
        int minePrice = in.nextInt();
        int mineDamage = in.nextInt();
                
        //my var
        int firstReach[] = new int[5];
        int senSet = 0; // 0 means not at flag pos +-1 // 1 means reached flag waiting to burn // 2 means carrier has flag, chasing
        int reachDest[] = new int[5];
        Vector<Minion> q4 = new Vector<Minion>();
        Vector<Minion> q3 = new Vector<Minion>();
        Vector<Minion> q2 = new Vector<Minion>();



        HashMap <Integer, Minion> oppMinMap = new HashMap<Integer, Minion>();
        HashMap <Integer, Minion> oppMinMapPrev = new HashMap<Integer, Minion>();
        HashMap <Integer, Minion> myMinMap = new HashMap<Integer, Minion>();
        HashMap <Integer, Minion> prevMap = new HashMap<Integer, Minion>();
        Vector<Minion> myMinVect = new Vector<Minion>();
        Minion tempOppMin;
        Minion tempMyMin;
        Minion tempMyMin2;
        Minion SE, NE, NW, SW;
        int nthCarrier = 0;
        int isStuck=0;
        int moveFlag = 0;
        SE = new Minion(0,0,0,0,0);
        SW = new Minion(0,0,0,0,0);
        NE = new Minion(0,0,0,0,0);
        NW = new Minion(0,0,0,0,0);
        int fl =0;
        int count = 0;
        int R, C;
        int freezefire = 1;
        int initShift = 0;
        int freezefire2 = 0;
        R=0;C=0;
        while(fl==0){
            if(Map[R].charAt(C) != '#'){
                fl=1;
                NW = new Minion(0,R,C,0,0);
            }
            else{
                count++;
                if(count%2==0){
                    R++;
                }
                else{
                    C++;
                }
            }
        }
        R=0; C= width-1; fl = 0;
        while(fl==0){
            if(Map[R].charAt(C) != '#'){
                fl=1;
                NE = new Minion(0,R,C,0,0);
            }
            else{
                count++;
                if(count%2==0){
                    R++;
                }
                else{
                    C--;
                }
            }
        }
        R=height -1; C= 0; fl = 0;
        while(fl==0){
            if(Map[R].charAt(C) != '#'){
                fl=1;
                SW = new Minion(0,R,C,0,0);
            }
            else{
                count++;
                if(count%2==0){
                    R--;
                }
                else{
                    C++;
                }
            }
        }
        R=height -1; C= width-1; fl = 0;
        while(fl==0){
            if(Map[R].charAt(C) != '#'){
                fl=1;
                SE = new Minion(0,R,C,0,0);
            }
            else{
                count++;
                if(count%2==0){
                    R--;
                }
                else{
                    C--;
                }
            }
        }
        ////System.err.println(SW);
        ////System.err.println(SE);
        ////System.err.println(NW);
        ////System.err.println(NE);

        //My minion Coin Collectors
        Minion[][] myMinPath = new Minion[5][4];
        for(int i=0;i<5;i++){
            myMinPath[i] = new Minion[4];
        }
        

        int currTarget[] = new int[5];
        currTarget[0] = 0;
        currTarget[1] = 0;
        currTarget[2] = 0;
        currTarget[3] = 0;
        currTarget[4] = 0;

        int init = 0;
        int startingHalf = 0; //
        // game loop
        while (true) {
            int myScore = in.nextInt();
            int opponentScore = in.nextInt();
            int myFlagPosR = in.nextInt();
            int myFlagPosC = in.nextInt();
            int myFlagCarrier = in.nextInt();
            int opponentFlagPosR = in.nextInt();
            int opponentFlagPosC = in.nextInt();
            int opponentFlagCarrier = in.nextInt();
            int myAliveMinionCnt = in.nextInt();
            int fireCount = 0;
            //System.err.println("Debug messages 1");
            oppMinMap.clear();
            myMinMap.clear();
            
            int ID_Serial[] = new int[5];
            for(int i=0;i<5;i++){
                ID_Serial[i] = -1;
            }
            for (int i = 0; i < myAliveMinionCnt; i++) {
                int id = in.nextInt();
                int posR = in.nextInt();
                int posC = in.nextInt();
                int health = in.nextInt();
                int timeout = in.nextInt();
                tempMyMin = new Minion(id, posR, posC, health, timeout);
                tempMyMin.d = Player.dist(opponentFlagPosC, opponentFlagPosR, posC, posR);
                if(init == 0){
                    startingHalf += (width/2 - posC); // if+ then LHS, if - then RHS
                }
                
                myMinMap.put(id,tempMyMin);
                if(init == 0){
                    myMinVect.add(tempMyMin);
                    Collections.sort(myMinVect);
                }
                
            }
            //initialize ID_Serial
            if(init == 0){
                for(int i=0;i<myMinVect.size();i++){
                    ID_Serial[myMinVect.get(i).id] = i;
                }
            }
            
            for(int i=0;i<myMinVect.size();i++){
                System.err.println(i + " " + myMinVect.get(i));
            }



            if(init!=0){
                for(int i=2;i<myMinVect.size();i++){
                    tempMyMin = myMinMap.get((myMinVect.get(i).id));
                    if(tempMyMin!=null){
                        tempMyMin2 = prevMap.get((myMinVect.get(i).id));
                        if(tempMyMin.posR == tempMyMin2.posR && tempMyMin.posC == tempMyMin2.posC && loopcnt<3){
                            isStuck = 1;
                            //System.err.println("------------------");
                            System.err.println(tempMyMin);
                            //System.err.println(tempMyMin2);
                            //System.err.println( myMinPath[myMinVect.get(i).id][currTarget[myMinVect.get(i).id]]);
                            //System.err.println("------------------");
                            R = myMinPath[tempMyMin.id][0].posR;
                            C = myMinPath[tempMyMin.id][0].posC;
                            //NW
                            if((R < height/2 && C < width/2)){
                                NW.posR++;
                                NW.posC++;
                                //NE.posR++;
                                //NE.posC--;
                                while(true){
                                    System.err.println("THIS IS NW: "+Map[NW.posR].charAt(NW.posC));
                                    if(Map[NW.posR].charAt(NW.posC) != '#'){
                                        NW = new Minion(0,NW.posR,NW.posC,0,0);
                                        //NE = new Minion(0,NE.posR,NE.posC,0,0);
                                        break;
                                    }
                                    else{
                                        NW.posR++;
                                        NW.posC++;
                                       // NE.posR++;
                                       // NE.posC--;
                                    }
                                }
                                
                            }
                            //NE
                            if(R < height/2 && C > width/2){
                                NE.posR++;
                                NE.posC--;
                                while(true){
                                    if(Map[NE.posR].charAt(NE.posC) != '#'){
                                        NE = new Minion(0,NE.posR,NE.posC,0,0);
                                        break;
                                    }
                                    else{
                                        NE.posR++;
                                        NE.posC--;
                                    }
                                }
                                
                            }
                            //SE
                            if((R > height/2 && C > width/2)){
                                SE.posR--;
                                SE.posC--;
                                //SW.posR--;
                                //SW.posC++;
                                while(true){
                                    if(Map[SE.posR].charAt(SE.posC) != '#'){
                                        SE = new Minion(0,SE.posR,SE.posC,0,0);
                                       // SW = new Minion(0,SW.posR,SW.posC,0,0);
                                        break;
                                    }
                                    else{
                                        SE.posR--;
                                        SE.posC--;
                                        //SW.posR--;
                                        //SW.posC++;
                                    }
                                }
                                
                            }
                            //SW
                            if(R > height/2 && C < width/2){
                                SW.posR--;
                                SW.posC++;
                                while(true){
                                    if(Map[SW.posR].charAt(SW.posC) != '#'){
                                        SW = new Minion(0,SW.posR,SW.posC,0,0);
                                        break;
                                    }
                                    else{
                                        SW.posR--;
                                        SW.posC++;
                                    }
                                }
                                
                            }
                        }
                    }
                }
            }
            if(isStuck == 1){
                //System.err.println("STUUUUUUUUUUCK");
            }
            //System.err.println(SW);
            //System.err.println(SE);
            //System.err.println(NW);
            System.err.println("THIS IS NW: " +  NW);
            System.err.println("THIS IS NE: " +  NE);

            for(int i=0;i<myMinVect.size();i++){
                tempMyMin = myMinMap.get(i);
                if(tempMyMin!=null){
                    //System.err.println("------------------");
                    //System.err.println(tempMyMin);
                   // //System.err.println(tempMyMin2);
                    //System.err.println( myMinPath[i][currTarget[i]]);
                    //System.err.println("------------------");
                }
                
            }
            if(loopcnt<3){
                //System.err.println(opponentFlagPosR + " " + opponentFlagPosC);
                for(Minion m: myMinVect){
                    //System.err.println(m);
                }
                //System.err.println("startingHalf: " + startingHalf);
                if(startingHalf>0){
                    initShift = 1;
                    if(myMinVect.size()>2){
                        myMinPath[myMinVect.get(2).id][0] = new Minion(NE); 
                        myMinPath[myMinVect.get(2).id][1] = new Minion(NW); 
                        myMinPath[myMinVect.get(2).id][2] = new Minion(SE); 
                        myMinPath[myMinVect.get(2).id][3] = new Minion(SW); 
                    }
                    if(myMinVect.size()>3){
                        myMinPath[myMinVect.get(3).id][0] = new Minion(SE); 
                        myMinPath[myMinVect.get(3).id][1] = new Minion(SW); 
                        myMinPath[myMinVect.get(3).id][2] = new Minion(NE); 
                        myMinPath[myMinVect.get(3).id][3] = new Minion(NW); 
                    }
                    if(myMinVect.size()>4){
                        myMinPath[myMinVect.get(4).id][0] = new Minion(NW); 
                        myMinPath[myMinVect.get(4).id][1] = new Minion(SW); 
                        myMinPath[myMinVect.get(4).id][2] = new Minion(NE); 
                        myMinPath[myMinVect.get(4).id][3] = new Minion(SE); 
                    }
                }
                else{
                    initShift = -1;
                    if(myMinVect.size()>2){
                        myMinPath[myMinVect.get(2).id][0] = new Minion(NW); 
                        myMinPath[myMinVect.get(2).id][1] = new Minion(NE); 
                        myMinPath[myMinVect.get(2).id][2] = new Minion(SW); 
                        myMinPath[myMinVect.get(2).id][3] = new Minion(SE); 
                    }
                    if(myMinVect.size()>3){
                        myMinPath[myMinVect.get(3).id][0] = new Minion(SW); 
                        myMinPath[myMinVect.get(3).id][1] = new Minion(SE); 
                        myMinPath[myMinVect.get(3).id][2] = new Minion(NW); 
                        myMinPath[myMinVect.get(3).id][3] = new Minion(NE); 
                    }
                    if(myMinVect.size()>4){
                        myMinPath[myMinVect.get(4).id][0] = new Minion(NE); 
                        myMinPath[myMinVect.get(4).id][1] = new Minion(SE); 
                        myMinPath[myMinVect.get(4).id][2] = new Minion(NW); 
                        myMinPath[myMinVect.get(4).id][3] = new Minion(SW); 
                    }
                }
                isStuck = 0;
                
                
            }
            
            int visibleMinionCnt = in.nextInt();
            //System.err.println("Debug messages 3");
            for (int i = 0; i < visibleMinionCnt; i++) {
                int id = in.nextInt();
                int posR = in.nextInt();
                int posC = in.nextInt();
                int health = in.nextInt();
                int timeout = in.nextInt();
                tempOppMin = new Minion(id, posR, posC, health, timeout);
                oppMinMap.put(id,tempOppMin);
                System.err.println(tempOppMin);
            }
            //System.err.println("Debug messages 5");
            int visibleCoinCnt = in.nextInt();
            for (int i = 0; i < visibleCoinCnt; i++) {
                int posR = in.nextInt();
                int posC = in.nextInt();
            }
            


            if(startingHalf>0){
                moveFlag = -1;
            }
            else{
                moveFlag = 1;
            }
            //CARRIERCHANGE
            if(opponentFlagCarrier == -1){
                Vector<Minion> tempVect = new Vector<Minion>();
                for(int i=0;i<myMinVect.size();i++){
                    tempMyMin = myMinMap.get(myMinVect.get(i).id);
                    if(tempMyMin!=null){
                        tempMyMin.d = Player.dist(tempMyMin.posR,tempMyMin.posC, opponentFlagPosR, opponentFlagPosC );
                        tempVect.add(new Minion(tempMyMin));
                        
                    }
                    

                }
                Collections.sort(tempVect);
                for(int i=0;i<myMinVect.size();i++){
                    if(tempVect.get(0).id == myMinVect.get(i).id){
                        nthCarrier = i;
                        break;
                    }
                }

                if(nthCarrier == 1 && tempVect.size()>1){
                    tempVect.remove(0);
                    for(int i=0;i<myMinVect.size();i++){
                        if(tempVect.get(0).id == myMinVect.get(i).id){
                            nthCarrier = i;
                            break;
                        }
                    }
                }
            }


            // Write an action using System.out.println()
            // To debug: //System.err.println("Debug messages...");
            //System.err.println("Debug messages 6");
            if(opponentFlagCarrier == -1){
                System.out.printf("MOVE "+ myMinVect.get(0).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                //System.err.println("Debug messages 7");
            }
            else{
                System.out.printf("MOVE "+ myMinVect.get(0).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                //System.err.println("Debug messages 8");
             }



            //Sabotage their carrier, ID =2
            tempMyMin = myMinMap.get( myMinVect.get(1).id);
            
                if(tempMyMin != null){

                    if(opponentFlagCarrier == tempMyMin.id){
                        System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                        nthCarrier = 1;
                        //System.err.println("Debug messages 8.2");
                    }
                    else if(myAliveMinionCnt == 1 && myMinMap.get(tempMyMin.id)!=null){
                        int fireOK2 = 0;
                        for(int i=0;i<5;i++){
                            tempOppMin = oppMinMap.get(i);
                            if(tempOppMin!=null){
                                fireOK2+= Player.canSee2(tempMyMin, tempOppMin, Map, oppMinMapPrev);
                                // //System.err.println(tempMyMin);
                                // //System.err.println(tempOppMin);
                                // //System.err.println("-----------------"+fireOK );
                            }
                        }
                        if(fireOK2>0 && myScore>=freezePrice && freezefire == 0){
                            System.out.printf("FREEZE "+ myMinVect.get(1).id + " "+" | ");
                            System.err.println("FIRE1 "+ myMinVect.get(1).id + " "+" | ");
                            fireCount++;
                            freezefire++;
                        }
                        else if(fireOK2>0 && myScore>=firePrice && freezefire !=0){
                            System.out.printf("FIRE "+ myMinVect.get(1).id + " "+" | ");
                            System.err.println("FIRE1 "+ myMinVect.get(1).id + " "+" | ");
                            fireCount++;
                            freezefire = (freezefire +1)%4;
                        }
                        else if(opponentFlagCarrier == -1){
                            System.out.printf("MOVE "+ myMinVect.get(1).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            //System.err.println("Debug messages 7.1");
                        }
                        else{
                            System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.err.println("Debug messages 8.2");
                        }
                    }
                    else{
                        if(myFlagCarrier == -1){
                            if(senSet == 0 && tempMyMin.posR==myFlagPosR && tempMyMin.posC== (myFlagPosC+initShift)){
                                    senSet = 1;
                            }
                            if(senSet == 2 && tempMyMin.posR==myFlagPosR && tempMyMin.posC== myFlagPosC){
                                    senSet = 1;
                            }
                            //System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");
                            if(senSet == 0){
                                System.out.printf("MOVE "+ tempMyMin.id + " "+ myFlagPosR +" "+  (myFlagPosC+initShift) + " | ");
                                System.err.println("NOTTTOKKKKKKKKK");
                                // //System.err.println(myMinVect.get(1).posR);
                                // //System.err.println(myFlagPosR);
                                // //System.err.println(myMinVect.get(1).posC);
                                // //System.err.println(myFlagPosC);
                                ////System.err.println("NOTTTOKKKKKKKKK");
                                //System.err.println("NOTTTOKKKKKKKKK");
                                
                                
                            }
                            else if(senSet == 1){
                                //System.err.println("OKKKKKKKKK");
                                int fireOK = 0;
                                for(int i=0;i<5;i++){
                                    tempOppMin = oppMinMap.get(i);
                                    if(tempOppMin!=null){
                                        fireOK+= Player.canSee2(tempMyMin, tempOppMin, Map, oppMinMapPrev);
                                        // //System.err.println(tempMyMin);
                                        // //System.err.println(tempOppMin);
                                        // //System.err.println("-----------------"+fireOK );
                                    }
                                }
                                if(fireOK>0 && myScore>=firePrice){
                                    System.out.printf("FIRE "+ myMinVect.get(1).id + " "+" | ");
                                    System.err.println("FIRE1 "+ myMinVect.get(1).id + " "+" | ");
                                    fireCount++;
                                }
                            }
                            else if(senSet == 2){
                                if(tempMyMin.posR==myFlagPosR && tempMyMin.posC== myFlagPosC){
                                    senSet = 1;
                                }
                                else{
                                    System.out.printf("MOVE "+ tempMyMin.id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");
                                }
                                
                            }
                        }
                        else{
                            senSet = 2;
                            tempOppMin = oppMinMap.get(myFlagCarrier);

                            if(tempOppMin == null){
                                System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");
                                System.err.println("MOVE3 "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");

                            }
                            else{
                                // if(freezefire == 1 && (tempMyMin.posC == tempOppMin.posC || tempMyMin.posR == tempOppMin.posR)){
                                //     if(freezePrice<=myScore){
                                //             System.out.printf("FREEZE "+ myMinVect.get(1).id + " "+" | ");
                                //             //System.err.println("FREEZE "+ myMinVect.get(1).id + " "+" | ");
                                //             freezefire = (freezefire + 1)%4 ;
                                //     }
                                //      else{
                                //          System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");
                                //         //System.err.println("MOVE "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");

                                //      }
                                    
                                // }
                                // else 
                                if(firePrice<=myScore && (tempMyMin.posC == tempOppMin.posC || tempMyMin.posR == tempOppMin.posR)){
                                //else if(freezefire==1 && (tempMyMin.posC == tempOppMin.posC || tempMyMin.posR == tempOppMin.posR)){
                
                                    System.out.printf("FIRE "+ myMinVect.get(1).id + " "+" | ");
                                    System.err.println("FIRE2 "+ myMinVect.get(1).id + " "+" | ");
                                    fireCount++;

                                    freezefire = (freezefire + 1)%4 ;
                                }
                                else{
                                    
                                    System.out.printf("MOVE "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");
                                    System.err.println("MOVE3 "+ myMinVect.get(1).id + " "+ myFlagPosR +" "+  myFlagPosC + " | ");

                                }
                            }
                            
                        }
                    }
                    
                }
            //System.err.println(myMinVect.size());
            tempMyMin = null;
            if(myMinVect.size()>2){      
                tempMyMin = myMinMap.get(myMinVect.get(2).id);
                //System.err.println(tempMyMin);
            }
            if(tempMyMin!=null){
                //System.err.println("Prev Target: " + currTarget[myMinVect.get(2).id]);
                if(tempMyMin.posR == myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posR && tempMyMin.posC == myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posC){
                    currTarget[myMinVect.get(2).id] = (currTarget[myMinVect.get(2).id] +1 ) %4;
                }
                //System.err.println("Next Target: " + currTarget[myMinVect.get(2).id]);
            }
            else{
                //System.err.println("Vect Size: " + myMinVect.size());
            }
            tempMyMin = null;
            if(myMinVect.size()>3){      
                tempMyMin = myMinMap.get(myMinVect.get(3).id);
            }
            if(tempMyMin!=null){
                if(tempMyMin.posR == myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posR && tempMyMin.posC == myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posC){
                    currTarget[myMinVect.get(3).id] = (currTarget[myMinVect.get(3).id] +1 ) %4;
                }
            }
            tempMyMin = null;
            if(myMinVect.size()>4){      
                tempMyMin = myMinMap.get(myMinVect.get(4).id);
            }
            if(tempMyMin!=null){
                if(tempMyMin.posR == myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR && tempMyMin.posC == myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC){
                    currTarget[myMinVect.get(4).id] = (currTarget[myMinVect.get(4).id] +1 ) %4;
                }
            }
            // tempMyMin = myMinMap.get(3);
            // if(tempMyMin!=null){
            //     if(tempMyMin.posR == myMinPath[3][currTarget[3]].posR && tempMyMin.posC == myMinPath[3][currTarget[3]].posC){
            //         currTarget[3] = (currTarget[3] +1 ) %4;
            //     }
            // }
            // tempMyMin = myMinMap.get(4);
            // if(tempMyMin!=null){
            //     if(tempMyMin.posR == myMinPath[4][currTarget[4]].posR && tempMyMin.posC == myMinPath[4][currTarget[4]].posC){
            //         currTarget[4] = (currTarget[4] +1 ) %4;
            //     }
            // }



                        //CARRIERCHANGE
            // if(myMinMap.get(myMinVect.get(0).id) == null && opponentFlagCarrier != myMinVect.get(1).id){
            //     nthCarrier = 2;
            //     //System.err.print("DEAD");
            // }
            // else{
            //     //System.err.print(myMinMap.get(myMinVect.get(0).id));
            //     //System.err.print(myAliveMinionCnt);
            // }




            if(myMinVect.size()>2){
                // if(nthCarrier != 2){
                //     System.out.printf("MOVE "+ myMinVect.get(2).id + " " + myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posR +" "+  myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posC + " | ");
                //     ////System.err.printf("MOVE "+ myMinVect.get(2).id + " " + myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posR +" "+  myMinPath[myMinVect.get(2).id][currTarget[myMinVect.get(2).id]].posC + " | ");
                // }
                // else{
                //     if(opponentFlagCarrier == -1){
                //         System.out.printf("MOVE "+ myMinVect.get(2).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                //         //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                //         //System.err.println("Debug messages 7.1");
                //     }
                //     else{
                //         System.out.printf("MOVE "+ myMinVect.get(2).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                //         //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                //         //System.err.println("Debug messages 8.2");
                //     }
                // }
                if(nthCarrier != 2){
                    //System.out.printf("MOVE "+ myMinVect.get(3).id + " " + myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posR +" "+  myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posC + " | ");
                    tempMyMin = myMinMap.get(myMinVect.get(2).id);
                    if(tempMyMin!=null){int fireOK = 0;
                        for(int i=0;i<5;i++){
                            tempOppMin = oppMinMap.get(i);
                            if(tempOppMin!=null){
                                fireOK+= Player.canSee(tempMyMin, tempOppMin, Map);
                                // //System.err.println(tempMyMin);
                                // //System.err.println(tempOppMin);
                                // //System.err.println("-----------------"+fireOK );
                            }
                        }
                        if(firstReach[tempMyMin.id] != 0){
                            Map[tempMyMin.posR].setCharAt(tempMyMin.posC, '*');
                        }
                        
                        
                        if(opponentFlagCarrier == tempMyMin.id){
                            nthCarrier = tempMyMin.id;
                            System.out.printf("MOVE "+ tempMyMin.id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            ////System.err.println("Debug messages 7.1");
                        }
                        //SCORCH
                        // else if(fireOK>0 && myScore>=firePrice*(fireCount+1)){
                        //     System.out.printf("FIRE "+ tempMyMin.id + " "+" | ");
                        //     System.err.println("FIRE3.1 "+ tempMyMin.id + " "+" | ");
                        //     fireCount++;
                        // }
                        else{
                            System.err.println(tempMyMin);
                            System.err.println(myMinPath[tempMyMin.id][0]);
                            System.err.println(reachDest[tempMyMin.id]);
                            // raeched init pos for first time;
                            if(tempMyMin.posR == myMinPath[tempMyMin.id][0].posR && tempMyMin.posC == myMinPath[tempMyMin.id][0].posC && reachDest[tempMyMin.id] == 0){
                                reachDest[tempMyMin.id] = 1;
                                firstReach[tempMyMin.id] = 1;
                                //EGG
                                Map[tempMyMin.posR].setCharAt(tempMyMin.posC,'*');
                                System.err.println(reachDest[tempMyMin.id]);
                                //q2.add(new Minion(0,myMinVect.get(4).posR,myMinVect.get(4).posC,0,0));
                                
                                
                                
                                
                                
                                
                                
                                
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag) == '.'){
                                    System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag));
                                    q2.add(new Minion(0,tempMyMin.posR,tempMyMin.posC+moveFlag,0,0));
                                }
                                if(Map[tempMyMin.posR-1].charAt(tempMyMin.posC) == '.'){
                                    System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                                    q2.add(new Minion(0,tempMyMin.posR-1,tempMyMin.posC,0,0));
                                }
                                if(Map[tempMyMin.posR+1].charAt(tempMyMin.posC) == '.'){
                                    System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));
                                    q2.add(new Minion(0,tempMyMin.posR+1,tempMyMin.posC,0,0));
                                }
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag) == '.'){
                                    System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag));
                                    q2.add(new Minion(0,tempMyMin.posR,tempMyMin.posC-moveFlag,0,0));
                                }
                            }

                            //
                            System.err.println(moveFlag);
                            System.err.println("Blocks Around 2, ID--" + tempMyMin.id);
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+1));
                            System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-1));
                            System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));


                            System.err.println(q2);
                            if(reachDest[tempMyMin.id] == 1){
                                System.err.println(reachDest[tempMyMin.id]);
                                reachDest[tempMyMin.id] = 0;
                                //if(!q2.isEmpty()){
                                    while(!q2.isEmpty()){
                                        // if(Map[q2.get(0).posR].charAt(q2.get(0).posC) != '.'){
                                        //     q2.remove(0);
                                        // }
                                        if(Map[q2.get(q2.size()-1).posR].charAt(q2.get(q2.size()-1).posC) != '.'){
                                        //    q2.remove(0);
                                            System.err.println(q2.get((q2.size()-1)));
                                            System.err.println(Map[q2.get(q2.size()-1).posR].charAt(q2.get(q2.size()-1).posC));
                                            
                                            q2.remove(q2.size()-1);
                                        }
                                        else{
                                            break;
                                        }
                                    }
                                    // myMinPath[tempMyMin.id][0] = new Minion(q2.get(0));
                                    // q2.remove(0);
                                    if(!q2.isEmpty()){
                                        myMinPath[tempMyMin.id][0] = new Minion(q2.get(q2.size()-1));
                                        q2.remove(q2.size()-1);
                                    }
                                    else{
                                        myMinPath[tempMyMin.id][0] = new Minion(0,opponentFlagBaseR, opponentFlagBaseC + moveFlag,0,0);
                                    }
                                    
                                    
                                //}
                            }
                            System.out.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            System.err.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.err.println("Debug messages 8.2");
                        }
                    // System.out.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    // //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    }
                    //System.err.printf("MOVE "+ myMinVect.get(3).id + " " + myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posR +" "+  myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posC + " | ");
                }
                else{
                    System.err.println("CARRIERRRRR 2");
                    if(opponentFlagCarrier == -1){
                        System.out.printf("MOVE "+ myMinVect.get(2).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.err.println("Debug messages 7.1");
                    }
                    else{
                        System.out.printf("MOVE "+ myMinVect.get(2).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.err.println("Debug messages 8.2");
                    }
                }

            }






            //CARRIERCHANGE
            // if(myMinMap.get(myMinVect.get(2).id) == null && opponentFlagCarrier != myMinVect.get(1).id){
            //     nthCarrier = 3;
            //     //System.err.print("DEAD2");
            // }
            // else{
            //     //System.err.println(myMinMap.get(myMinVect.get(2).id));
            //     //System.err.println(myAliveMinionCnt);
            // }




            //System.err.println("nthCarrier: " + nthCarrier );
            if(myMinVect.size()>3){
                //System.err.println("Check1" );
                if(nthCarrier != 3){
                    //System.out.printf("MOVE "+ myMinVect.get(3).id + " " + myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posR +" "+  myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posC + " | ");
                    tempMyMin = myMinMap.get(myMinVect.get(3).id);
                    if(tempMyMin!=null){int fireOK = 0;
                        for(int i=0;i<5;i++){
                            tempOppMin = oppMinMap.get(i);
                            if(tempOppMin!=null){
                                fireOK+= Player.canSee(tempMyMin, tempOppMin, Map);
                                // //System.err.println(tempMyMin);
                                // //System.err.println(tempOppMin);
                                // //System.err.println("-----------------"+fireOK );
                            }
                        }
                        if(firstReach[tempMyMin.id] != 0){
                            Map[tempMyMin.posR].setCharAt(tempMyMin.posC, '*');
                        }
                        
                        
                        if(opponentFlagCarrier == tempMyMin.id){
                            nthCarrier = tempMyMin.id;
                            System.out.printf("MOVE "+ tempMyMin.id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            ////System.err.println("Debug messages 7.1");
                        }
                        //SCORCH
                        // else if(fireOK>0 && myScore>=firePrice*(fireCount+1)){
                        //     System.out.printf("FIRE "+ tempMyMin.id + " "+" | ");
                        //     System.err.println("FIRE3.1 "+ tempMyMin.id + " "+" | ");
                        //     fireCount++;
                        // }
                        else{
                            System.err.println(tempMyMin);
                            System.err.println(myMinPath[tempMyMin.id][0]);
                            System.err.println(reachDest[tempMyMin.id]);
                            // raeched init pos for first time;
                            if(tempMyMin.posR == myMinPath[tempMyMin.id][0].posR && tempMyMin.posC == myMinPath[tempMyMin.id][0].posC && reachDest[tempMyMin.id] == 0){
                                reachDest[tempMyMin.id] = 1;
                                firstReach[tempMyMin.id] = 1;
                                Map[tempMyMin.posR].setCharAt(tempMyMin.posC,'*');
                                System.err.println(reachDest[tempMyMin.id]);
                                //q3.add(new Minion(0,myMinVect.get(4).posR,myMinVect.get(4).posC,0,0));
                                
                                
                                
                                
                                
                                
                                System.err.println(tempMyMin);
                                System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag));
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag) == '.'){
                                    
                                    q3.add(new Minion(0,tempMyMin.posR,tempMyMin.posC-moveFlag,0,0));
                                }
                                System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                                if(Map[tempMyMin.posR-1].charAt(tempMyMin.posC) == '.'){
                                    
                                    q3.add(new Minion(0,tempMyMin.posR-1,tempMyMin.posC,0,0));
                                }
                                
                                System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag));
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag) == '.'){
                                   
                                    q3.add(new Minion(0,tempMyMin.posR,tempMyMin.posC+moveFlag,0,0));
                                }
                                
                                System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));
                                if(Map[tempMyMin.posR+1].charAt(tempMyMin.posC) == '.'){
                                   
                                    q3.add(new Minion(0,tempMyMin.posR+1,tempMyMin.posC,0,0));
                                }
                                System.err.println(moveFlag);

                            }

                            //
                            System.err.println(moveFlag);
                            System.err.println("Blocks Around 3, ID--" + tempMyMin.id);
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+1));
                            System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-1));
                            System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));


                            System.err.println(q3);
                            if(reachDest[tempMyMin.id] == 1){
                                System.err.println(reachDest[tempMyMin.id]);
                                reachDest[tempMyMin.id] = 0;
                                //if(!q3.isEmpty()){
                                    while(!q3.isEmpty()){
                                        // if(Map[q3.get(0).posR].charAt(q3.get(0).posC) != '.'){
                                        //     q3.remove(0);
                                        // }
                                        if(Map[q3.get(q3.size()-1).posR].charAt(q3.get(q3.size()-1).posC) != '.'){
                                        //    q3.remove(0);
                                            System.err.println(q3.get((q3.size()-1)));
                                            System.err.println(Map[q3.get(q3.size()-1).posR].charAt(q3.get(q3.size()-1).posC));
                                            
                                            q3.remove(q3.size()-1);
                                        }
                                        else{
                                            break;
                                        }
                                    }
                                    // myMinPath[tempMyMin.id][0] = new Minion(q3.get(0));
                                    // q3.remove(0);
                                    if(!q3.isEmpty()){
                                        myMinPath[tempMyMin.id][0] = new Minion(q3.get(q3.size()-1));
                                        q3.remove(q3.size()-1);
                                    }
                                    else{
                                        myMinPath[tempMyMin.id][0] = new Minion(0,opponentFlagBaseR, opponentFlagBaseC + moveFlag,0,0);
                                    }
                                    
                                    
                                //}
                            }
                            System.out.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            System.err.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.err.println("Debug messages 8.2");
                        }
                    // System.out.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    // //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    }
                    //System.err.printf("MOVE "+ myMinVect.get(3).id + " " + myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posR +" "+  myMinPath[myMinVect.get(3).id][currTarget[myMinVect.get(3).id]].posC + " | ");
                }
                else{
                    if(opponentFlagCarrier == -1){
                        System.out.printf("MOVE "+ myMinVect.get(3).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.err.println("Debug messages 7.1");
                    }
                    else{
                        System.out.printf("MOVE "+ myMinVect.get(3).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.err.println("Debug messages 8.2");
                    }
                }
            }
            
            
            //CARRIERCHANGE
            // if(myMinMap.get(myMinVect.get(3).id) == null && opponentFlagCarrier != myMinVect.get(1).id){
            //     nthCarrier = 4;
            //     //System.err.print("DEAD2");
            // }
            // else{
            //     //System.err.println(myMinMap.get(myMinVect.get(2).id));
            //     //System.err.println(myAliveMinionCnt);
            // }



            if(myMinVect.size()>4){
                if(nthCarrier != 4){
                    tempMyMin = myMinMap.get(myMinVect.get(4).id);
                    if(tempMyMin!=null){int fireOK = 0;
                        for(int i=0;i<5;i++){
                            tempOppMin = oppMinMap.get(i);
                            if(tempOppMin!=null){
                                fireOK+= Player.canSee(tempMyMin, tempOppMin, Map);
                                // //System.err.println(tempMyMin);
                                // //System.err.println(tempOppMin);
                                // //System.err.println("-----------------"+fireOK );
                            }
                        }
                        if(firstReach[tempMyMin.id] != 0){
                            Map[tempMyMin.posR].setCharAt(tempMyMin.posC, '*');
                        }
                        
                        
                        if(opponentFlagCarrier == tempMyMin.id){
                            nthCarrier = tempMyMin.id;
                            System.out.printf("MOVE "+ tempMyMin.id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                            ////System.err.println("Debug messages 7.1");
                        }
                        //SCORCH
                        // else if(fireOK>0 && myScore>=firePrice*(fireCount+1)){
                        //     System.out.printf("FIRE "+ tempMyMin.id + " "+" | ");
                        //     System.err.println("FIRE4.1 "+ tempMyMin.id + " "+" | ");
                        //     fireCount++;
                        // }
                        else{
                            System.err.println(tempMyMin);
                            System.err.println(myMinPath[tempMyMin.id][0]);
                            System.err.println(reachDest[tempMyMin.id]);
                            // raeched init pos for first time;
                            if(tempMyMin.posR == myMinPath[tempMyMin.id][0].posR && tempMyMin.posC == myMinPath[tempMyMin.id][0].posC && reachDest[tempMyMin.id] == 0){
                                reachDest[tempMyMin.id] = 1;
                                firstReach[tempMyMin.id] = 1;
                                Map[tempMyMin.posR].setCharAt(tempMyMin.posC,'*');
                                System.err.println(reachDest[tempMyMin.id]);
                                //q4.add(new Minion(0,myMinVect.get(4).posR,myMinVect.get(4).posC,0,0));
                                
                                
                                
                                
                                
                                
                                //UP, RIGHT, DOWN,LEFT
                                System.err.println(tempMyMin.posR + " " + (tempMyMin.posC+moveFlag));
                                System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag));
                                
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC+moveFlag) == '.'){
                                    
                                    q4.add(new Minion(0,tempMyMin.posR,tempMyMin.posC+moveFlag,0,0));
                                    System.err.println(q4.get(q4.size()-1));
                                }
                                
                                System.err.println((tempMyMin.posR+1) + " " + (tempMyMin.posC));
                                System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));
                                if(Map[tempMyMin.posR+1].charAt(tempMyMin.posC) == '.'){
                                    
                                    q4.add(new Minion(0,tempMyMin.posR+1,tempMyMin.posC,0,0));
                                    System.err.println(q4.get(q4.size()-1));
                                }
                                
                                System.err.println((tempMyMin.posR) + " " + (tempMyMin.posC-moveFlag));
                                System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag));
                                if(Map[tempMyMin.posR].charAt(tempMyMin.posC-moveFlag) == '.'){
                                    
                                    q4.add(new Minion(0,tempMyMin.posR,tempMyMin.posC-moveFlag,0,0));
                                    System.err.println(q4.get(q4.size()-1));
                                }
                                
                                System.err.println((tempMyMin.posR-1) + " " + (tempMyMin.posC));
                                System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                                if(Map[tempMyMin.posR-1].charAt(tempMyMin.posC) == '.'){
                                    
                                    q4.add(new Minion(0,tempMyMin.posR-1,tempMyMin.posC,0,0));
                                    System.err.println(q4.get(q4.size()-1));
                                }
                                
                            }

                            //
                            System.err.println(moveFlag);
                            System.err.println("Blocks Around 4, ID--" + tempMyMin.id);
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC+1));
                            System.err.println(Map[tempMyMin.posR-1].charAt(tempMyMin.posC));
                            System.err.println(Map[tempMyMin.posR].charAt(tempMyMin.posC-1));
                            System.err.println(Map[tempMyMin.posR+1].charAt(tempMyMin.posC));


                            System.err.println(q4);
                            if(reachDest[tempMyMin.id] == 1){
                                System.err.println(reachDest[tempMyMin.id]);
                                reachDest[tempMyMin.id] = 0;
                                // if(!q4.isEmpty()){
                                //     while(true){
                                //         // if(Map[q4.get(0).posR].charAt(q4.get(0).posC) != '.'){
                                //         //     q4.remove(0);
                                //         // }
                                //         if(Map[q4.get(q4.size()-1).posR].charAt(q4.get(q4.size()-1).posC) != '.'){
                                //         //    q4.remove(0);
                                //             System.err.println(q4.get((q4.size()-1)));
                                //             System.err.println(Map[q4.get(q4.size()-1).posR].charAt(q4.get(q4.size()-1).posC));
                                            
                                //             q4.remove(q4.size()-1);
                                //         }
                                //         else{
                                //             break;
                                //         }
                                //     }
                                //     // myMinPath[tempMyMin.id][0] = new Minion(q4.get(0));
                                //     // q4.remove(0);
                                //     myMinPath[tempMyMin.id][0] = new Minion(q4.get(q4.size()-1));
                                //     q4.remove(q4.size()-1);
                                    
                                // }
                                while(!q4.isEmpty()){
                                            // if(Map[q4.get(0).posR].charAt(q4.get(0).posC) != '.'){
                                            //     q4.remove(0);
                                            // }
                                            if(Map[q4.get(q4.size()-1).posR].charAt(q4.get(q4.size()-1).posC) != '.'){
                                            //    q4.remove(0);
                                                System.err.println(q4.get((q4.size()-1)));
                                                System.err.println(Map[q4.get(q4.size()-1).posR].charAt(q4.get(q4.size()-1).posC));
                                                
                                                q4.remove(q4.size()-1);
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                        // myMinPath[tempMyMin.id][0] = new Minion(q4.get(0));
                                        // q4.remove(0);
                                        if(!q4.isEmpty()){
                                            myMinPath[tempMyMin.id][0] = new Minion(q4.get(q4.size()-1));
                                            q4.remove(q4.size()-1);
                                        }
                                        else{
                                            myMinPath[tempMyMin.id][0] = new Minion(0,opponentFlagBaseR, opponentFlagBaseC + moveFlag,0,0);
                                        }
                            }
                            System.out.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            System.err.printf("MOVE "+ tempMyMin.id + " " + myMinPath[tempMyMin.id][0].posR +" "+  myMinPath[tempMyMin.id][0].posC + " | ");
                            //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                            //System.out.printf("MOVE "+ myMinVect.get(3).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                            //System.err.println("Debug messages 8.2");
                        }
                    // System.out.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    // //System.err.printf("MOVE "+ myMinVect.get(4).id + " " + myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posR +" "+  myMinPath[myMinVect.get(4).id][currTarget[myMinVect.get(4).id]].posC + " | ");
                    }
                }
                else{
                    if(opponentFlagCarrier == -1){
                        System.out.printf("MOVE "+ myMinVect.get(4).id + " "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.out.printf("MOVE 1 "+opponentFlagPosR +" "+  opponentFlagPosC + " | ");
                        //System.err.println("Debug messages 7.1");
                    }
                    else{
                        System.out.printf("MOVE "+ myMinVect.get(4).id + " "+ myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.out.printf("MOVE 1 "+myFlagBaseR +" "+  myFlagBaseC + " | ");
                        //System.err.println("Debug messages 8.2");
                    }
                }
            }

            //System.out.printf("MOVE 3 "+ myMinPath[3][currTarget[3]].posR +" "+  myMinPath[1][currTarget[3]].posC + " | ");
            //System.out.printf("MOVE 4 "+ myMinPath[4][currTarget[4]].posR +" "+  myMinPath[1][currTarget[4]].posC + " | ");
            System.out.println();
            //System.err.println(nthCarrier);
            prevMap = new HashMap<Integer, Minion>(myMinMap);
            oppMinMapPrev =  new HashMap<Integer, Minion>(oppMinMap);
            init = 1;
            loopcnt++;
        }
    }
}