import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FindTotalWinning {
    public static void main(String[] args){

        long startTime = System.nanoTime();

        String fileName = "src/main/java/input.txt";
        System.out.println(findWinning(fileName));

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("totalTime = "+totalTime);

    }

    private static ArrayList<String[]> five_of_a_kind_arrayList = new ArrayList<>();
    private static ArrayList<String[]> four_of_a_kind_arrayList = new ArrayList<>();
    private static ArrayList<String[]> full_house_arrayList = new ArrayList<>();
    private static ArrayList<String[]> three_of_a_kind_arrayList = new ArrayList<>();
    private static ArrayList<String[]> two_pair_arrayList = new ArrayList<>();
    private static ArrayList<String[]> one_pair_arrayList = new ArrayList<>();
    private static ArrayList<String[]> high_card_arrayList = new ArrayList<>();

    private static void insert(String[] item){
        String type = item[2];

        switch(type){
            case "5_of_a_kind":
                if(five_of_a_kind_arrayList.size() == 0){
                    five_of_a_kind_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < five_of_a_kind_arrayList.size(); index++){
                        String[] target_item = five_of_a_kind_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != five_of_a_kind_arrayList.size() - 1){
                                five_of_a_kind_arrayList.remove(index);
                                five_of_a_kind_arrayList.add(index,item);
                                five_of_a_kind_arrayList.add(index+1,temp_item);
                            }
                            else{
                                five_of_a_kind_arrayList.add(temp_item);
                                five_of_a_kind_arrayList.remove(index);
                                five_of_a_kind_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != five_of_a_kind_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                five_of_a_kind_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "4_of_a_kind":
                if(four_of_a_kind_arrayList.size() == 0){
                    four_of_a_kind_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < four_of_a_kind_arrayList.size(); index++){
                        String[] target_item = four_of_a_kind_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != four_of_a_kind_arrayList.size() - 1){
                                four_of_a_kind_arrayList.remove(index);
                                four_of_a_kind_arrayList.add(index,item);
                                four_of_a_kind_arrayList.add(index+1,temp_item);
                            }
                            else{
                                four_of_a_kind_arrayList.add(temp_item);
                                four_of_a_kind_arrayList.remove(index);
                                four_of_a_kind_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != four_of_a_kind_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                four_of_a_kind_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "full_house":
                if(full_house_arrayList.size() == 0){
                    full_house_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < full_house_arrayList.size(); index++){
                        String[] target_item = full_house_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != full_house_arrayList.size() - 1){
                                full_house_arrayList.remove(index);
                                full_house_arrayList.add(index,item);
                                full_house_arrayList.add(index+1,temp_item);
                            }
                            else{
                                full_house_arrayList.add(temp_item);
                                full_house_arrayList.remove(index);
                                full_house_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != full_house_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                full_house_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "3_of_a_kind":
                if(three_of_a_kind_arrayList.size() == 0){
                    three_of_a_kind_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < three_of_a_kind_arrayList.size(); index++){
                        String[] target_item = three_of_a_kind_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != three_of_a_kind_arrayList.size() - 1){
                                three_of_a_kind_arrayList.remove(index);
                                three_of_a_kind_arrayList.add(index,item);
                                three_of_a_kind_arrayList.add(index+1,temp_item);
                            }
                            else{
                                three_of_a_kind_arrayList.add(temp_item);
                                three_of_a_kind_arrayList.remove(index);
                                three_of_a_kind_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != three_of_a_kind_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                three_of_a_kind_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "2_pair":
                if(two_pair_arrayList.size() == 0){
                    two_pair_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < two_pair_arrayList.size(); index++){
                        String[] target_item = two_pair_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != two_pair_arrayList.size() - 1){
                                two_pair_arrayList.remove(index);
                                two_pair_arrayList.add(index,item);
                                two_pair_arrayList.add(index+1,temp_item);
                            }
                            else{
                                two_pair_arrayList.add(temp_item);
                                two_pair_arrayList.remove(index);
                                two_pair_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != two_pair_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                two_pair_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "1_pair":
                if(one_pair_arrayList.size() == 0){
                    one_pair_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < one_pair_arrayList.size(); index++){
                        String[] target_item = one_pair_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != one_pair_arrayList.size() - 1){
                                one_pair_arrayList.remove(index);
                                one_pair_arrayList.add(index,item);
                                one_pair_arrayList.add(index+1,temp_item);
                            }
                            else{
                                one_pair_arrayList.add(temp_item);
                                one_pair_arrayList.remove(index);
                                one_pair_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != one_pair_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                one_pair_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "high_card":
                if(high_card_arrayList.size() == 0){
                    high_card_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < high_card_arrayList.size(); index++){
                        String[] target_item = high_card_arrayList.get(index);
                        boolean needSwapping = compare(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != high_card_arrayList.size() - 1){
                                high_card_arrayList.remove(index);
                                high_card_arrayList.add(index,item);
                                high_card_arrayList.add(index+1,temp_item);
                            }
                            else{
                                high_card_arrayList.add(temp_item);
                                high_card_arrayList.remove(index);
                                high_card_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != high_card_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                high_card_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
        }
    }

    private static int countJoker(String hand){
        int count = 0;
        if(hand.indexOf("J") != -1){

            for(int index = hand.indexOf("J"); index < hand.length(); index++){
                if(Character.toString(hand.charAt(index)).equals("J")){
                    count++;
                }
            }
        }
        return count;
    }

    private static void insert_part2(String[] item){
        String hand = item[0];
        String type = item[2];
        int numJoker = countJoker(hand);

        switch(type){
            case "5_of_a_kind":
                if(five_of_a_kind_arrayList.size() == 0){
                    five_of_a_kind_arrayList.add(item);
                }
                else{
                    for(int index = 0; index < five_of_a_kind_arrayList.size(); index++){
                        String[] target_item = five_of_a_kind_arrayList.get(index);
                        boolean needSwapping = compare_part2(item, target_item);
                        if(needSwapping){
                            String[] temp_item = target_item;
                            if(index != five_of_a_kind_arrayList.size() - 1){
                                five_of_a_kind_arrayList.remove(index);
                                five_of_a_kind_arrayList.add(index,item);
                                five_of_a_kind_arrayList.add(index+1,temp_item);
                            }
                            else{
                                five_of_a_kind_arrayList.add(temp_item);
                                five_of_a_kind_arrayList.remove(index);
                                five_of_a_kind_arrayList.add(index,item);
                            }
                            break;
                        }
                        else{
                            if(index != five_of_a_kind_arrayList.size() - 1){
                                continue;
                            }
                            else{
                                five_of_a_kind_arrayList.add(item);
                                break;
                            }
                        }
                    }
                }
                break;
            case "4_of_a_kind":
                switch(numJoker){
                    case 0:
                        if(four_of_a_kind_arrayList.size() == 0){
                            four_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < four_of_a_kind_arrayList.size(); index++){
                                String[] target_item = four_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                        four_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(temp_item);
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                    case 4:
                        if(five_of_a_kind_arrayList.size() == 0){
                            five_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < five_of_a_kind_arrayList.size(); index++){
                                String[] target_item = five_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != five_of_a_kind_arrayList.size() - 1){
                                        five_of_a_kind_arrayList.remove(index);
                                        five_of_a_kind_arrayList.add(index,item);
                                        five_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        five_of_a_kind_arrayList.add(temp_item);
                                        five_of_a_kind_arrayList.remove(index);
                                        five_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != five_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        five_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case "full_house":
                switch(numJoker){
                    case 0:
                        if(full_house_arrayList.size() == 0){
                            full_house_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < full_house_arrayList.size(); index++){
                                String[] target_item = full_house_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != full_house_arrayList.size() - 1){
                                        full_house_arrayList.remove(index);
                                        full_house_arrayList.add(index,item);
                                        full_house_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        full_house_arrayList.add(temp_item);
                                        full_house_arrayList.remove(index);
                                        full_house_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != full_house_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        full_house_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                    case 3:
                        if(five_of_a_kind_arrayList.size() == 0){
                            five_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < five_of_a_kind_arrayList.size(); index++){
                                String[] target_item = five_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != five_of_a_kind_arrayList.size() - 1){
                                        five_of_a_kind_arrayList.remove(index);
                                        five_of_a_kind_arrayList.add(index,item);
                                        five_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        five_of_a_kind_arrayList.add(temp_item);
                                        five_of_a_kind_arrayList.remove(index);
                                        five_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != five_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        five_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case "3_of_a_kind":
                switch(numJoker){
                    case 0:
                        if(three_of_a_kind_arrayList.size() == 0){
                            three_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < three_of_a_kind_arrayList.size(); index++){
                                String[] target_item = three_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != three_of_a_kind_arrayList.size() - 1){
                                        three_of_a_kind_arrayList.remove(index);
                                        three_of_a_kind_arrayList.add(index,item);
                                        three_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        three_of_a_kind_arrayList.add(temp_item);
                                        three_of_a_kind_arrayList.remove(index);
                                        three_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != three_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        three_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                    case 3:
                        if(four_of_a_kind_arrayList.size() == 0){
                            four_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < four_of_a_kind_arrayList.size(); index++){
                                String[] target_item = four_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                        four_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(temp_item);
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case "2_pair":
                switch(numJoker){
                    case 0:
                        if(two_pair_arrayList.size() == 0){
                            two_pair_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < two_pair_arrayList.size(); index++){
                                String[] target_item = two_pair_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != two_pair_arrayList.size() - 1){
                                        two_pair_arrayList.remove(index);
                                        two_pair_arrayList.add(index,item);
                                        two_pair_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        two_pair_arrayList.add(temp_item);
                                        two_pair_arrayList.remove(index);
                                        two_pair_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != two_pair_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        two_pair_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        if(full_house_arrayList.size() == 0){
                            full_house_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < full_house_arrayList.size(); index++){
                                String[] target_item = full_house_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != full_house_arrayList.size() - 1){
                                        full_house_arrayList.remove(index);
                                        full_house_arrayList.add(index,item);
                                        full_house_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        full_house_arrayList.add(temp_item);
                                        full_house_arrayList.remove(index);
                                        full_house_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != full_house_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        full_house_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 2:
                        if(four_of_a_kind_arrayList.size() == 0){
                            four_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < four_of_a_kind_arrayList.size(); index++){
                                String[] target_item = four_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                        four_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(temp_item);
                                        four_of_a_kind_arrayList.remove(index);
                                        four_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != four_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        four_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case "1_pair":
                switch(numJoker){
                    case 0:
                        if(one_pair_arrayList.size() == 0){
                            one_pair_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < one_pair_arrayList.size(); index++){
                                String[] target_item = one_pair_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != one_pair_arrayList.size() - 1){
                                        one_pair_arrayList.remove(index);
                                        one_pair_arrayList.add(index,item);
                                        one_pair_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        one_pair_arrayList.add(temp_item);
                                        one_pair_arrayList.remove(index);
                                        one_pair_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != one_pair_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        one_pair_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                    case 2:
                        if(three_of_a_kind_arrayList.size() == 0){
                            three_of_a_kind_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < three_of_a_kind_arrayList.size(); index++){
                                String[] target_item = three_of_a_kind_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != three_of_a_kind_arrayList.size() - 1){
                                        three_of_a_kind_arrayList.remove(index);
                                        three_of_a_kind_arrayList.add(index,item);
                                        three_of_a_kind_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        three_of_a_kind_arrayList.add(temp_item);
                                        three_of_a_kind_arrayList.remove(index);
                                        three_of_a_kind_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != three_of_a_kind_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        three_of_a_kind_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
            case "high_card":
                switch(numJoker){
                    case 0:
                        if(high_card_arrayList.size() == 0){
                            high_card_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < high_card_arrayList.size(); index++){
                                String[] target_item = high_card_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != high_card_arrayList.size() - 1){
                                        high_card_arrayList.remove(index);
                                        high_card_arrayList.add(index,item);
                                        high_card_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        high_card_arrayList.add(temp_item);
                                        high_card_arrayList.remove(index);
                                        high_card_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != high_card_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        high_card_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                    case 1:
                        if(one_pair_arrayList.size() == 0){
                            one_pair_arrayList.add(item);
                        }
                        else{
                            for(int index = 0; index < one_pair_arrayList.size(); index++){
                                String[] target_item = one_pair_arrayList.get(index);
                                boolean needSwapping = compare_part2(item, target_item);
                                if(needSwapping){
                                    String[] temp_item = target_item;
                                    if(index != one_pair_arrayList.size() - 1){
                                        one_pair_arrayList.remove(index);
                                        one_pair_arrayList.add(index,item);
                                        one_pair_arrayList.add(index+1,temp_item);
                                    }
                                    else{
                                        one_pair_arrayList.add(temp_item);
                                        one_pair_arrayList.remove(index);
                                        one_pair_arrayList.add(index,item);
                                    }
                                    break;
                                }
                                else{
                                    if(index != one_pair_arrayList.size() - 1){
                                        continue;
                                    }
                                    else{
                                        one_pair_arrayList.add(item);
                                        break;
                                    }
                                }
                            }
                        }
                        break;
                }
                break;
        }
    }

    private static boolean compare(String[] item, String[] target){

        String item_hand = item[0];
        String target_hand = target[0];

        char item_hand_card1 = item_hand.charAt(0);
        char target_hand_card1 = target_hand.charAt(0);

        int item_hand_card1_value = getValue(item_hand_card1);
        int target_hand_card1_value = getValue(target_hand_card1);

        if(item_hand_card1_value == target_hand_card1_value){
            char item_hand_card2 = item_hand.charAt(1);
            char target_hand_card2 = target_hand.charAt(1);

            int item_hand_card2_value = getValue(item_hand_card2);
            int target_hand_card2_value = getValue(target_hand_card2);

            if(item_hand_card2_value == target_hand_card2_value){
                char item_hand_card3 = item_hand.charAt(2);
                char target_hand_card3 = target_hand.charAt(2);

                int item_hand_card3_value = getValue(item_hand_card3);
                int target_hand_card3_value = getValue(target_hand_card3);

                if(item_hand_card3_value == target_hand_card3_value){
                    char item_hand_card4 = item_hand.charAt(3);
                    char target_hand_card4 = target_hand.charAt(3);

                    int item_hand_card4_value = getValue(item_hand_card4);
                    int target_hand_card4_value = getValue(target_hand_card4);

                    if(item_hand_card4_value == target_hand_card4_value){
                        char item_hand_card5 = item_hand.charAt(4);
                        char target_hand_card5 = target_hand.charAt(4);

                        int item_hand_card5_value = getValue(item_hand_card5);
                        int target_hand_card5_value = getValue(target_hand_card5);

                        if(item_hand_card5_value < target_hand_card5_value){
                            return true;
                        }
                    }
                    else{
                        if(item_hand_card4_value < target_hand_card4_value){
                            return true;
                        }
                    }
                }
                else{
                    if(item_hand_card3_value < target_hand_card3_value){
                        return true;
                    }
                }
            }
            else{
                if(item_hand_card2_value < target_hand_card2_value){
                    return true;
                }
            }
        }
        else{
            if(item_hand_card1_value < target_hand_card1_value){
                return true;
            }
        }
        return false;
    }


    private static boolean compare_part2(String[] item, String[] target){

        String item_hand = item[0];
        String target_hand = target[0];

        char item_hand_card1 = item_hand.charAt(0);
        char target_hand_card1 = target_hand.charAt(0);

        int item_hand_card1_value = getValue_part2(item_hand_card1);
        int target_hand_card1_value = getValue_part2(target_hand_card1);

        if(item_hand_card1_value == target_hand_card1_value){
            char item_hand_card2 = item_hand.charAt(1);
            char target_hand_card2 = target_hand.charAt(1);

            int item_hand_card2_value = getValue_part2(item_hand_card2);
            int target_hand_card2_value = getValue_part2(target_hand_card2);

            if(item_hand_card2_value == target_hand_card2_value){
                char item_hand_card3 = item_hand.charAt(2);
                char target_hand_card3 = target_hand.charAt(2);

                int item_hand_card3_value = getValue_part2(item_hand_card3);
                int target_hand_card3_value = getValue_part2(target_hand_card3);

                if(item_hand_card3_value == target_hand_card3_value){
                    char item_hand_card4 = item_hand.charAt(3);
                    char target_hand_card4 = target_hand.charAt(3);

                    int item_hand_card4_value = getValue_part2(item_hand_card4);
                    int target_hand_card4_value = getValue_part2(target_hand_card4);

                    if(item_hand_card4_value == target_hand_card4_value){
                        char item_hand_card5 = item_hand.charAt(4);
                        char target_hand_card5 = target_hand.charAt(4);

                        int item_hand_card5_value = getValue_part2(item_hand_card5);
                        int target_hand_card5_value = getValue_part2(target_hand_card5);

                        if(item_hand_card5_value < target_hand_card5_value){
                            return true;
                        }
                    }
                    else{
                        if(item_hand_card4_value < target_hand_card4_value){
                            return true;
                        }
                    }
                }
                else{
                    if(item_hand_card3_value < target_hand_card3_value){
                        return true;
                    }
                }
            }
            else{
                if(item_hand_card2_value < target_hand_card2_value){
                    return true;
                }
            }
        }
        else{
            if(item_hand_card1_value < target_hand_card1_value){
                return true;
            }
        }
        return false;
    }


    private static long findWinning(String fileName){
        ArrayList<String[]> data = readData(fileName);

        for(int index = 0; index < data.size(); index++){
            String[] item = data.get(index);
            String hand = item[0];
            String bidAmount = item[1];
            String type = checkType(hand);
            String rank = item[3];

            String[] newItem = new String[4];
            newItem[0] = hand;
            newItem[1] = bidAmount;
            newItem[2] = type;
            newItem[3] = rank;

//            insert(newItem);
            insert_part2(newItem);

        }

        long totalWinning = 0;

        long largestRank = five_of_a_kind_arrayList.size() + four_of_a_kind_arrayList.size() +
                full_house_arrayList.size() + three_of_a_kind_arrayList.size() +
                two_pair_arrayList.size() + one_pair_arrayList.size() + high_card_arrayList.size();

        System.out.println("five_of_a_kind");
        for(int index = five_of_a_kind_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(five_of_a_kind_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(five_of_a_kind_arrayList.get(index)[0]);
        }

        System.out.println("four_of_a_kind");
        for(int index = four_of_a_kind_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(four_of_a_kind_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(four_of_a_kind_arrayList.get(index)[0]);
        }

        System.out.println("full_house");
        for(int index = full_house_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(full_house_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(full_house_arrayList.get(index)[0]);
        }

        System.out.println("three_of_a_kind");
        for(int index = three_of_a_kind_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(three_of_a_kind_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(three_of_a_kind_arrayList.get(index)[0]);
        }

        System.out.println("two_pair");
        for(int index = two_pair_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(two_pair_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(two_pair_arrayList.get(index)[0]);
        }

        System.out.println("one_pair");
        for(int index = one_pair_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(one_pair_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(one_pair_arrayList.get(index)[0]);
        }

        System.out.println("high_card");
        for(int index = high_card_arrayList.size()-1; index >= 0; index--){

            long bidAmount = Long.parseLong(high_card_arrayList.get(index)[1]);
            long rank = largestRank;

            long winning = rank * bidAmount;

            totalWinning += winning;

            largestRank--;
            System.out.println(high_card_arrayList.get(index)[0]);
        }

        return totalWinning;
    }

    private static ArrayList<String[]> readData(String fileName){

        ArrayList<String[]> data = new ArrayList<>();

        String line = null;

        BufferedReader reader;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                String[] tempStr_Array = line.split(" ");
                String[] item = new String[4];
                item[0] = tempStr_Array[0];
                item[1] = tempStr_Array[1];
                item[2] = ""; // type
                item[3] = ""; // rank
                data.add(item);

                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }

    private static String checkType(String hand){

        String type = null;

        char card1 = hand.charAt(0);
        char card2 = hand.charAt(1);
        char card3 = hand.charAt(2);
        char card4 = hand.charAt(3);
        char card5 = hand.charAt(4);

        if(card1 == card2 && card2 == card3 && card3 == card4 && card4 == card5){
            type = "5_of_a_kind";
        }
        else if((card1 == card2 && card2 == card3 && card3 == card4)||
                (card1 == card2 && card2 == card3 && card3 == card5)||
                (card1 == card2 && card2 == card4 && card4 == card5)||
                (card1 == card3 && card3 == card4 && card4 == card5)||
                (card2 == card3 && card3 == card4 && card4 == card5)){
            type = "4_of_a_kind";
        }
        else if((card1 == card2 && card2 == card3 && card4 == card5)||
                (card1 == card2 && card2 == card4 && card3 == card5)||
                (card1 == card2 && card2 == card5 && card3 == card4)||
                (card1 == card3 && card3 == card4 && card2 == card5)||
                (card1 == card3 && card3 == card5 && card2 == card4)||
                (card1 == card4 && card4 == card5 && card2 == card3)||
                (card2 == card3 && card3 == card4 && card1 == card5)||
                (card2 == card3 && card3 == card5 && card1 == card4)||
                (card2 == card4 && card4 == card5 && card1 == card3)||
                (card3 == card4 && card4 == card5 && card1 == card2)){
            type = "full_house";
        }
        else if((card1 == card2 && card2 == card3)||
                (card1 == card2 && card2 == card4)||
                (card1 == card2 && card2 == card5)||
                (card1 == card3 && card3 == card4)||
                (card1 == card3 && card3 == card5)||
                (card1 == card4 && card4 == card5)||
                (card2 == card3 && card3 == card4)||
                (card2 == card3 && card3 == card5)||
                (card2 == card4 && card4 == card5)||
                (card3 == card4 && card4 == card5)){
            type = "3_of_a_kind";
        }
        else if((card1 == card2 && card3 == card4)||
                (card1 == card2 && card3 == card5)||
                (card1 == card2 && card4 == card5)||
                (card1 == card3 && card2 == card4)||
                (card1 == card3 && card2 == card5)||
                (card1 == card3 && card4 == card5)||
                (card1 == card4 && card2 == card3)||
                (card1 == card4 && card2 == card5)||
                (card1 == card4 && card3 == card5)||
                (card1 == card5 && card2 == card3)||
                (card1 == card5 && card2 == card4)||
                (card1 == card5 && card3 == card4)||
                (card2 == card5 && card3 == card4)||
                (card2 == card4 && card3 == card5)||
                (card2 == card3 && card4 == card5)
        ){
            type = "2_pair";
        }
        else if((card1 == card2)||
                (card1 == card3)||
                (card1 == card4)||
                (card1 == card5)||
                (card2 == card3)||
                (card2 == card4)||
                (card2 == card5)||
                (card3 == card4)||
                (card3 == card5)||
                (card4 == card5)
        ){
            type = "1_pair";
        }
        else if(card1 != card2 && card2 != card3 && card3 != card4 && card4 != card5){
            type = "high_card";
        }

        return type;
    }

    private static String sortHand(String hand){
        char card1 = hand.charAt(0);
        char card2 = hand.charAt(1);
        char card3 = hand.charAt(2);
        char card4 = hand.charAt(3);
        char card5 = hand.charAt(4);

        String sortedHand = "";

        if(card1 == card2 && card2 == card3 && card3 == card4 && card4 == card5){
            return hand;
        }

        int card1_val = getValue(card1);
        int card2_val = getValue(card2);
        int card3_val = getValue(card3);
        int card4_val = getValue(card4);
        int card5_val = getValue(card5);

        ArrayList<Integer> toSortArrayList = new ArrayList<>();
        toSortArrayList.add(card1_val);
        toSortArrayList.add(card2_val);
        toSortArrayList.add(card3_val);
        toSortArrayList.add(card4_val);
        toSortArrayList.add(card5_val);

//        Collections.sort(toSortArrayList); // no cheat!!!!

        for(int index = 0; index < toSortArrayList.size(); index++){
            if(index != toSortArrayList.size() - 1){
                if(toSortArrayList.get(index)<toSortArrayList.get(index+1)){
                    int temp = toSortArrayList.get(index+1);
                    toSortArrayList.remove(index+1);
                    toSortArrayList.add(index,temp);
                }
            }
            else{
                if(toSortArrayList.get(index-1)<toSortArrayList.get(index)){
                    int temp = toSortArrayList.get(index);
                    toSortArrayList.remove(index);
                    toSortArrayList.add(index-1,temp);
                }
            }
        }

        sortedHand = sortedHand.concat(Character.toString(getLabel(toSortArrayList.get(0))));
        sortedHand = sortedHand.concat(Character.toString(getLabel(toSortArrayList.get(1))));
        sortedHand = sortedHand.concat(Character.toString(getLabel(toSortArrayList.get(2))));
        sortedHand = sortedHand.concat(Character.toString(getLabel(toSortArrayList.get(3))));
        sortedHand = sortedHand.concat(Character.toString(getLabel(toSortArrayList.get(4))));

        return sortedHand;
    }

    private static char getLabel(int cardValue){
        char label = '-';
        switch(cardValue){
            case 14:
                label = 'A';
                break;
            case 13:
                label = 'K';
                break;
            case 12:
                label = 'Q';
                break;
            case 11:
                label = 'J';
                break;
            case 10:
                label = 'T';
                break;
            case 9:
                label = '9';
                break;
            case 8:
                label = '8';
                break;
            case 7:
                label = '7';
                break;
            case 6:
                label = '6';
                break;
            case 5:
                label = '5';
                break;
            case 4:
                label = '4';
                break;
            case 3:
                label = '3';
                break;
            case 2:
                label = '2';
                break;
        }
        return label;
    }

    private static int getValue(char card){
        int value = 0;
        switch(card){
            case 'A':
                value = 14;
                break;
            case 'K':
                value = 13;
                break;
            case 'Q':
                value = 12;
                break;
            case 'J':
                value = 11;
                break;
            case 'T':
                value = 10;
                break;
            case '9':
                value = 9;
                break;
            case '8':
                value = 8;
                break;
            case '7':
                value = 7;
                break;
            case '6':
                value = 6;
                break;
            case '5':
                value = 5;
                break;
            case '4':
                value = 4;
                break;
            case '3':
                value = 3;
                break;
            case '2':
                value = 2;
                break;
        }
        return value;
    }

    private static char getLabel_part2(int cardValue){
        char label = '-';
        switch(cardValue){
            case 14:
                label = 'A';
                break;
            case 13:
                label = 'K';
                break;
            case 12:
                label = 'Q';
                break;
            case 1:
                label = 'J';
                break;
            case 10:
                label = 'T';
                break;
            case 9:
                label = '9';
                break;
            case 8:
                label = '8';
                break;
            case 7:
                label = '7';
                break;
            case 6:
                label = '6';
                break;
            case 5:
                label = '5';
                break;
            case 4:
                label = '4';
                break;
            case 3:
                label = '3';
                break;
            case 2:
                label = '2';
                break;
        }
        return label;
    }

    private static int getValue_part2(char card){
        int value = 0;
        switch(card){
            case 'A':
                value = 14;
                break;
            case 'K':
                value = 13;
                break;
            case 'Q':
                value = 12;
                break;
            case 'J':
                value = 1;
                break;
            case 'T':
                value = 10;
                break;
            case '9':
                value = 9;
                break;
            case '8':
                value = 8;
                break;
            case '7':
                value = 7;
                break;
            case '6':
                value = 6;
                break;
            case '5':
                value = 5;
                break;
            case '4':
                value = 4;
                break;
            case '3':
                value = 3;
                break;
            case '2':
                value = 2;
                break;
        }
        return value;
    }

}
