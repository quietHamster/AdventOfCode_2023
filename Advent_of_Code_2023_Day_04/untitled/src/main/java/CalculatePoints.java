import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CalculatePoints {
    public static void main(String[] args){

        String fileName = "src/main/java/input.txt";

        System.out.println(calculatePoints(fileName));
    }

    private static int totalPoints = 0;

    private static ArrayList<int[]> winningStats = new ArrayList<>();

    private static int calculatePoints(String fileName){


        ArrayList<String> allLines = new ArrayList<>();
        String line = null;


        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line!=null){
                allLines.add(line);

                line = reader.readLine();
            }
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }


        for(int lineIndex = 0; lineIndex < allLines.size(); lineIndex++){
            String tempString = allLines.get(lineIndex);
            int indexOfColon = tempString.indexOf(":");

            String winningNumbersString = null;
            String myNumbersString = null;

            tempString = tempString.substring(indexOfColon+1).trim();
//            System.out.println("tempString = "+tempString);

            int indexOfSeperator = tempString.indexOf("|");

            winningNumbersString = tempString.substring(0,indexOfSeperator-1).trim();
//            System.out.println("winningNumbersString = "+winningNumbersString);

            myNumbersString = tempString.substring(indexOfSeperator+2).trim();
//            System.out.println("myNumbersString = "+myNumbersString);


            String[] winningNumbersArray = winningNumbersString.split(" ");
            ArrayList<Integer> winningNumbersArrayList = new ArrayList<>();
            for (int item = 0; item < winningNumbersArray.length; item++){
                if(!winningNumbersArray[item].equals("")){
                    winningNumbersArrayList.add(Integer.parseInt(winningNumbersArray[item]));
                }
            }

            String[] myNumbersArray = myNumbersString.split(" ");
            ArrayList<Integer> myNumbersArrayList = new ArrayList<>();
            for (int item = 0; item < myNumbersArray.length; item++){
                if(!myNumbersArray[item].equals("")){
                    myNumbersArrayList.add(Integer.parseInt(myNumbersArray[item]));
                }
            }

            checkOneCard(winningNumbersArrayList, myNumbersArrayList, lineIndex);

        }



//        return totalPoints; // for part1
        return countCards(); // for part 2
    }

    private static int countCards(){

        int totalCards = 0;

        for (int index = 0; index<winningStats.size();index++){

            int numWinningCards_currentCard = winningStats.get(index)[1];
            int numCopiedCards_currentCard = winningStats.get(index)[2];
            int totalCards_currentCard = winningStats.get(index)[3];

            if(numWinningCards_currentCard>0){

                for (int copiedCardsCounter = 1; copiedCardsCounter<=numWinningCards_currentCard;copiedCardsCounter++){
                    int[] needUpdateItem = winningStats.get(index+copiedCardsCounter);
                    int needUpdateItem_cardNumber = needUpdateItem[0];
                    int needUpdateItem_winning = needUpdateItem[1];
                    int needUpdateItem_copiedCards = needUpdateItem[2];
                    int needUpdateItem_totalCards = needUpdateItem[3];

//                    needUpdateItem_copiedCards++;
                    needUpdateItem_copiedCards += totalCards_currentCard;
                    needUpdateItem_totalCards = needUpdateItem_copiedCards+1;

                    int[] swapItem = new int[4];
                    swapItem[0] = needUpdateItem_cardNumber;
                    swapItem[1] = needUpdateItem_winning;
                    swapItem[2] = needUpdateItem_copiedCards;
                    swapItem[3] = needUpdateItem_totalCards;

                    winningStats.remove(index+copiedCardsCounter);
                    winningStats.add(index+copiedCardsCounter,swapItem);

                }
                // int copiedCards = winningStats.get(index)[2];


                /*
                if(copiedCards>0){
                    int keepGoing = copiedCards;

                    while(keepGoing>0){
                        for(int winCards_index = 0; winCards_index<numWinningCards_currentCard; winCards_index++){

                            int[] item_to_update = winningStats.get(index+winCards_index+1);

                            int item_to_update_cardNumber = item_to_update[0];
                            int item_to_update_winning = item_to_update[1];
                            int item_to_update_copiedCards = item_to_update[2];
                            int item_to_update_totalCards = item_to_update[3];


                            item_to_update_copiedCards = ;
                            item_to_update_totalCards++;

                            int[] swapItem = new int[4];
                            swapItem[0] = item_to_update_cardNumber;
                            swapItem[1] = item_to_update_winning;
                            swapItem[2] = item_to_update_copiedCards;
                            swapItem[3] = item_to_update_totalCards;

                            winningStats.remove(index+winCards_index+1);
                            winningStats.add(index+winCards_index+1,swapItem);
                        }


                        keepGoing--;
                    }
                }
                else{
                    for(int winCards_index = 0; winCards_index<numWinningCards_currentCard; winCards_index++){

                        int[] item_to_update = winningStats.get(index+winCards_index+1);

                        int item_to_update_cardNumber = item_to_update[0];
                        int item_to_update_distribution = item_to_update[1];
                        int item_to_update_multiplier = item_to_update[2];
                        int item_to_update_recieved = item_to_update[3];

                        item_to_update_multiplier++;
                        item_to_update_recieved++;

                        int[] swapItem = new int[4];
                        swapItem[0] = item_to_update_cardNumber;
                        swapItem[1] = item_to_update_distribution;
                        swapItem[2] = item_to_update_multiplier;
                        swapItem[3] = item_to_update_recieved;

                        winningStats.remove(index+winCards_index+1);
                        winningStats.add(index+winCards_index+1,swapItem);
                    }
                }

                */


            }
            else{

            }

            System.out.println("["+winningStats.get(index)[0]  +","+ winningStats.get(index)[1]
            +","+winningStats.get(index)[2]+","+winningStats.get(index)[3]+"]");


        }

        for(int index = 0; index <winningStats.size(); index++){
            int addent = winningStats.get(index)[3];
            totalCards+=addent;
        }

        return totalCards;

    }

    private static void checkOneCard(ArrayList<Integer> winningNumbersArrayList, ArrayList<Integer> myNumbersArrayList,
                                     int cardIndex){

        Collections.sort(winningNumbersArrayList);
        Collections.sort(myNumbersArrayList);

        int cardTotal = 0;

        ArrayList<Integer> tempArrayList = new ArrayList<>();

        for (int myNumIndex = 0; myNumIndex<myNumbersArrayList.size();myNumIndex++){

            if(myNumbersArrayList.get(myNumIndex)<winningNumbersArrayList.get(0)){
                continue;
            }
            else{
                if(winningNumbersArrayList.contains(myNumbersArrayList.get(myNumIndex))){
                    tempArrayList.add(myNumbersArrayList.get(myNumIndex));
                }
            }

        }

        if(tempArrayList.size()>0){


            if(tempArrayList.size()>1){
                String tmpStr =Double.toString(Math.pow(2,tempArrayList.size()-1));
                tmpStr = tmpStr.substring(0,tmpStr.indexOf("."));

                cardTotal = Integer.parseInt(tmpStr);


                totalPoints += cardTotal;
            }
            else {
                totalPoints += 1;
            }

            int[] tempArray = new int[4];
            tempArray[0] = cardIndex;
            tempArray[1] = tempArrayList.size();
            tempArray[2] = 0;
            tempArray[3] = 1; // count for itself

            winningStats.add(tempArray);
        }
        else{
            int[] tempArray = new int[4];
            tempArray[0] = cardIndex;
            tempArray[1] = 0;
            tempArray[2] = 0;
            tempArray[3] = 1; // count for itself

            winningStats.add(tempArray);
        }
    }
}
