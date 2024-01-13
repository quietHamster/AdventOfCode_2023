import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CountSteps {

    public static void main(String[] args){

        String fileName = "src/main/java/input.txt";

        System.out.println(countSteps(fileName));

    }

    private static String start_node = "AAA"; // part 1
    private static String end_node = "ZZZ"; // part 1

    private static ArrayList<String> start_node_arrayList = new ArrayList<>();

    private static long countSteps(String fileName){
        long steps = 0;

        Map <String, String[]> data = readData(fileName);

        String[] instruction_array = data.get("instructions");

        long numStep = 0;
        boolean keepGoing = true;

        /* part 1
        String left = "";
        String right = "";

        String currentNode = start_node;
        String nextNode = "";
        */

        // NOTE: the minimum num steps must be at least equal the size of the instruction_array array

        // part 2
        ArrayList<Long> stepCount_eachStartToEnd = new ArrayList<>();

        boolean onGoing = true;
        String currentNode = "";
        String lastCheckedNode = "";

        int counter = 0;

        for (int nodeIndex = 0; nodeIndex < start_node_arrayList.size(); nodeIndex++){
            currentNode = start_node_arrayList.get(nodeIndex);
            numStep = 0;
            while(onGoing){

                counter++;

                String instruction = instruction_array[Long.valueOf(numStep % instruction_array.length).intValue()];

                if(numStep > 0){
                    currentNode = lastCheckedNode;
                }

                String left = "";
                String right = "";
                String nextNode = "";

                if("L".equals(instruction)){
                    left = data.get(currentNode)[0];
                    nextNode = left;
                }
                else if("R".equals(instruction)){
                    right = data.get(currentNode)[1];
                    nextNode = right;
                }
                numStep++;

                lastCheckedNode = nextNode;

                if(lastCheckedNode.charAt(2) == 'Z'){
                    stepCount_eachStartToEnd.add(numStep);
                    break;
                }
            }
        }

        Collections.sort(stepCount_eachStartToEnd);
        long commonNumber = stepCount_eachStartToEnd.get(stepCount_eachStartToEnd.size()-1);

        long multipleFactorCount = 1;
        while(keepGoing){
            int qualified_counter = 0;
            for(int  index = stepCount_eachStartToEnd.size()-1; index >=0 ; index--){

                if(commonNumber % stepCount_eachStartToEnd.get(index) == 0){
                    qualified_counter++;
                    continue;
                }
                else{
                    break;
                }
            }
            if(qualified_counter == stepCount_eachStartToEnd.size()){
                numStep = commonNumber;
                break;
            }

            multipleFactorCount++;
            commonNumber = multipleFactorCount * stepCount_eachStartToEnd.get(stepCount_eachStartToEnd.size()-1);
        }

        // part 1
        /*
        while(keepGoing){

            String instruction = instruction_array[Long.valueOf(numStep % instruction_array.length).intValue()];

            if("L".equals(instruction)){
                left = data.get(currentNode)[0];
                nextNode = left;
            }
            else if("R".equals(instruction)){
                right = data.get(currentNode)[1];
                nextNode = right;
            }

            numStep++;
            currentNode = nextNode;

            if(nextNode.equals(end_node) || nextNode.equals(end_node)){
                keepGoing = false;
            }
        }
        */

        return numStep;
    }

    private static Map<String, String[]> readData(String fileName){
        Map<String, String[]> data = new HashMap<>();

        BufferedReader reader;

        String line = null;

        int counter = 0;

        try{
            reader = new BufferedReader(new FileReader(fileName));

            line = reader.readLine();

            while(line != null){
                counter++;
                if(line.trim().length() > 0){
                    if(line.indexOf ("=")!= -1){
                        String node = line.substring(0,3);
                        String left = line.substring(7,10);
                        String right = line.substring(12, 15);
                        String[] tempArray = new String[2];
                        tempArray[0] = left;
                        tempArray[1] = right;
                        data.put(node, tempArray);

                        // part 2
                        if(node.charAt(2) == 'A'){
                            start_node_arrayList.add(node);
                        }

                    }
                    else{
                        String[] temptArray = line.split("");
                        data.put("instructions",temptArray);
                    }
                }

                line = reader.readLine();
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return data;
    }
}
