import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class FindLocation {
    public static void main(String[] args) {

        long startTime = System.nanoTime();

        String fileName = "src/main/java/input.txt";
        System.out.println(findLowestLocation(fileName));

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("totalTime = "+totalTime);
    }

    private static ArrayList<ArrayList<long[]>> seed_soil_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> soil_fertilizer_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> fertilizer_water_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> water_light_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> light_temperature_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> temperature_humidity_map = new ArrayList<>();
    private static ArrayList<ArrayList<long[]>> humidity_location_map = new ArrayList<>();

    private static ArrayList<long[]> locationIntervalArrayList = new ArrayList<>();

    private static void fillMap(int startIndex, int endIndex, String mapName, ArrayList<String> allLines){

        for (int index = startIndex+1; index < endIndex - 1; index++) {

            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);

            long sourceLow = source;
            long sourceHigh = source + length - 1;
            long destinationLow = destination;
            long destinationHigh = destination + length - 1;

            long[] sourceInterval = new long[2];
            long[] destinationInterval = new long[2];

            sourceInterval[0] = sourceLow;
            sourceInterval[1] = sourceHigh;
            destinationInterval[0] = destinationLow;
            destinationInterval[1] = destinationHigh;

            ArrayList<long[]> tempArrayList = new ArrayList<>();
            tempArrayList.add(sourceInterval);
            tempArrayList.add(destinationInterval);

            if("seed_soil".equals(mapName)){
                if(seed_soil_map.size()==0){
                    seed_soil_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(seed_soil_map,tempArrayList);
                }
            }
            else if("soil_fertilizer".equals(mapName)){
                if(soil_fertilizer_map.size()==0){
                    soil_fertilizer_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(soil_fertilizer_map,tempArrayList);
                }
            }
            else if("fertilizer_water".equals(mapName)){
                if(fertilizer_water_map.size()==0){
                    fertilizer_water_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(fertilizer_water_map,tempArrayList);
                }
            }
            else if("water_light".equals(mapName)){
                if(water_light_map.size()==0){
                    water_light_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(water_light_map,tempArrayList);
                }
            }
            else if("light_temperature".equals(mapName)){
                if(light_temperature_map.size()==0){
                    light_temperature_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(light_temperature_map,tempArrayList);
                }
            }
            else if("temperature_humidity".equals(mapName)){
                if(temperature_humidity_map.size()==0){
                    temperature_humidity_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(temperature_humidity_map,tempArrayList);
                }
            }
            else if("humidity_location".equals(mapName)){
                if(humidity_location_map.size()==0){
                    humidity_location_map.add(tempArrayList);
                }
                else {
                    sortThenFillMap(humidity_location_map,tempArrayList);
                }
            }
        }
    }

    private static void sortThenFillMap(ArrayList<ArrayList<long[]>> map, ArrayList<long[]> tempArrayList){
        long[] temp_sourceInterval = tempArrayList.get(0);
//        long[] temp_destinationInterval = tempArrayList.get(1);
        long temp_sourceInterval_low = temp_sourceInterval[0];

        for(int index = 0; index < map.size(); index++){
            ArrayList<long[]> source_destination_pair = map.get(index);
            long[] sourceInterval = source_destination_pair.get(0);
            long sourceInterval_low = sourceInterval[0];
            if(temp_sourceInterval_low < sourceInterval_low){ // swap
                ArrayList<long[]> swapItem = source_destination_pair;
                map.remove(index);
                map.add(index,tempArrayList);
                map.add(index+1,swapItem);
                break;
            }
            else { // NO swap
                if(index != map.size()-1){
                    continue;
                }
                else{
                    map.add(tempArrayList);
                    break;
                }

            }
        }
    }

    private static String[] getSeedRangePairs(String seedsLine) {


        String[] tempArray = seedsLine.split(" ");

        String[] allSeedRangePairs = new String[tempArray.length - 1];

        for (int index = 1; index < tempArray.length; index++) {
            allSeedRangePairs[index - 1] = tempArray[index];
        }

        return allSeedRangePairs;
    }


    private static long getLocation(long seed, int soilToFertilizerHeadLineIndex, int fertilizerToWaterHeadLineIndex,
                                    int waterToLightHeadLineIndex, int lightToTemperatureHeadLineIndex,
                                    int temperatureToHumidityHeadLineIndex, int humidityToLocationHeadLineIndex,
                                    ArrayList<String> allLines) {


//        long seed = seedsArrayList.get(seedIndex);
        long soil = -1;
        long fertilizer = -1;
        long water = -1;
        long light = -1;
        long temperature = -1;
        long humidity = -1;
        long location = -1;

        for (int index = 3; index < soilToFertilizerHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (seed < source) {
                if (index != soilToFertilizerHeadLineIndex - 2) {
                    continue;
                } else {
                    soil = seed;
                }

            } else if (seed == source) {
                soil = destination;
                break;
            } else {
                if (seed > source && seed < source + length) {
                    soil = seed - source + destination;
                    break;
                } else {
                    if (index != soilToFertilizerHeadLineIndex - 2) {
                        continue;
                    }
                    soil = seed;
                    break;
                }
            }

        }

        for (int index = soilToFertilizerHeadLineIndex + 1; index < fertilizerToWaterHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (soil < source) {
                if (index != fertilizerToWaterHeadLineIndex - 2) {
                    continue;
                } else {
                    fertilizer = soil;
                }

            } else if (soil == source) {
                fertilizer = destination;
                break;
            } else {
                if (soil > source && soil < source + length) {
                    fertilizer = soil - source + destination;
                    break;
                } else {
                    if (index != fertilizerToWaterHeadLineIndex - 2) {
                        continue;
                    }
                    fertilizer = soil;
                    break;
                }
            }

        }

        for (int index = fertilizerToWaterHeadLineIndex + 1; index < waterToLightHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (fertilizer < source) {
                if (index != waterToLightHeadLineIndex - 2) {
                    continue;
                } else {
                    water = fertilizer;
                }
            } else if (fertilizer == source) {
                water = destination;
                break;
            } else {
                if (fertilizer > source && fertilizer < source + length) {
                    water = fertilizer - source + destination;
                    break;
                } else {
                    if (index != waterToLightHeadLineIndex - 2) {
                        continue;
                    }
                    water = fertilizer;
                    break;
                }
            }

        }

        for (int index = waterToLightHeadLineIndex + 1; index < lightToTemperatureHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (water < source) {
                if (index != lightToTemperatureHeadLineIndex - 2) {
                    continue;
                } else {
                    light = water;
                }
            } else if (water == source) {
                light = destination;
                break;
            } else {
                if (water > source && water < source + length) {
                    light = water - source + destination;
                    break;
                } else {
                    if (index != lightToTemperatureHeadLineIndex - 2) {
                        continue;
                    }
                    light = water;
                    break;
                }
            }

        }

        for (int index = lightToTemperatureHeadLineIndex + 1; index < temperatureToHumidityHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (light < source) {
                if (index != temperatureToHumidityHeadLineIndex - 2) {
                    continue;
                } else {
                    temperature = light;
                }
            } else if (light == source) {
                temperature = destination;
                break;
            } else {
                if (light > source && light < source + length) {
                    temperature = light - source + destination;
                    break;
                } else {
                    if (index != temperatureToHumidityHeadLineIndex - 2) {
                        continue;
                    }
                    temperature = light;
                    break;
                }
            }

        }

        for (int index = temperatureToHumidityHeadLineIndex + 1; index < humidityToLocationHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (temperature < source) {
                if (index != humidityToLocationHeadLineIndex - 2) {
                    continue;
                } else {
                    humidity = temperature;
                }

            } else if (temperature == source) {
                humidity = destination;
                break;
            } else {
                if (temperature > source && temperature < source + length) {
                    humidity = temperature - source + destination;
                    break;
                } else {
                    if (index != humidityToLocationHeadLineIndex - 2) {
                        continue;
                    }
                    humidity = temperature;
                    break;
                }
            }

        }

        for (int index = humidityToLocationHeadLineIndex + 1; index < allLines.size(); index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (humidity < source) {
                if (humidity > source && humidity < source + length) {
                    continue;
                } else {
                    location = humidity;
                }
            } else if (humidity == source) {
                location = destination;
                break;
            } else {
                if (humidity > source && humidity < source + length) {
                    location = humidity - source + destination;
                    break;
                } else {
                    if (index != allLines.size() - 1) {
                        continue;
                    }
                    location = humidity;
                    break;
                }
            }

        }

        System.out.println("seed = "+ seed+
        " soil = "+soil+
        " fertilizer = "+fertilizer+
        " water = "+water+
        " light = "+light+
        " temperature = "+temperature+
        " humidity = "+humidity+
        " location = "+location);

        return location;

    }

    private static long getLocationByRange(long seed, long range, int soilToFertilizerHeadLineIndex, int fertilizerToWaterHeadLineIndex,
                                    int waterToLightHeadLineIndex, int lightToTemperatureHeadLineIndex,
                                    int temperatureToHumidityHeadLineIndex, int humidityToLocationHeadLineIndex,
                                    ArrayList<String> allLines) {

        long location = -1;

        long[] seedRange = new long[2];

        seedRange[0] = seed;
        seedRange[1] = seed + range -1;

        ArrayList<long[]> soilRangeArrayList = new ArrayList<>();

        for (int index = 3; index < soilToFertilizerHeadLineIndex - 1; index++) {

        }
/*
//        long seed = seedsArrayList.get(seedIndex);
        long soil = -1;
        long fertilizer = -1;
        long water = -1;
        long light = -1;
        long temperature = -1;
        long humidity = -1;
        long location = -1;

        for (int index = 3; index < soilToFertilizerHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);

            //=====================
//            if(seed+range<source){
//                if (index != soilToFertilizerHeadLineIndex - 2) {
//                    continue;
//                } else {
//                    soil = seed;
//                }
//            }
//            else if(seed+range>source){
//                if(seed+range<source+length){
//                    soil = seed + (destination-source);
//                }
//
//            }
//            else{
//
//            }


            //=====================

            if (seed < source) {
                if (index != soilToFertilizerHeadLineIndex - 2) {
                    continue;
                } else {
                    soil = seed;
                }

            } else if (seed == source) {
                soil = destination;
                break;
            } else {
                if (seed > source && seed < source + length) {
                    soil = seed - source + destination;
                    break;
                } else {
                    if (index != soilToFertilizerHeadLineIndex - 2) {
                        continue;
                    }
                    soil = seed;
                    break;
                }
            }

        }

        for (int index = soilToFertilizerHeadLineIndex + 1; index < fertilizerToWaterHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (soil < source) {
                if (index != fertilizerToWaterHeadLineIndex - 2) {
                    continue;
                } else {
                    fertilizer = soil;
                }

            } else if (soil == source) {
                fertilizer = destination;
                break;
            } else {
                if (soil > source && soil < source + length) {
                    fertilizer = soil - source + destination;
                    break;
                } else {
                    if (index != fertilizerToWaterHeadLineIndex - 2) {
                        continue;
                    }
                    fertilizer = soil;
                    break;
                }
            }

        }

        for (int index = fertilizerToWaterHeadLineIndex + 1; index < waterToLightHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (fertilizer < source) {
                if (index != waterToLightHeadLineIndex - 2) {
                    continue;
                } else {
                    water = fertilizer;
                }
            } else if (fertilizer == source) {
                water = destination;
                break;
            } else {
                if (fertilizer > source && fertilizer < source + length) {
                    water = fertilizer - source + destination;
                    break;
                } else {
                    if (index != waterToLightHeadLineIndex - 2) {
                        continue;
                    }
                    water = fertilizer;
                    break;
                }
            }

        }

        for (int index = waterToLightHeadLineIndex + 1; index < lightToTemperatureHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (water < source) {
                if (index != lightToTemperatureHeadLineIndex - 2) {
                    continue;
                } else {
                    light = water;
                }
            } else if (water == source) {
                light = destination;
                break;
            } else {
                if (water > source && water < source + length) {
                    light = water - source + destination;
                    break;
                } else {
                    if (index != lightToTemperatureHeadLineIndex - 2) {
                        continue;
                    }
                    light = water;
                    break;
                }
            }

        }

        for (int index = lightToTemperatureHeadLineIndex + 1; index < temperatureToHumidityHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (light < source) {
                if (index != temperatureToHumidityHeadLineIndex - 2) {
                    continue;
                } else {
                    temperature = light;
                }
            } else if (light == source) {
                temperature = destination;
                break;
            } else {
                if (light > source && light < source + length) {
                    temperature = light - source + destination;
                    break;
                } else {
                    if (index != temperatureToHumidityHeadLineIndex - 2) {
                        continue;
                    }
                    temperature = light;
                    break;
                }
            }

        }

        for (int index = temperatureToHumidityHeadLineIndex + 1; index < humidityToLocationHeadLineIndex - 1; index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (temperature < source) {
                if (index != humidityToLocationHeadLineIndex - 2) {
                    continue;
                } else {
                    humidity = temperature;
                }

            } else if (temperature == source) {
                humidity = destination;
                break;
            } else {
                if (temperature > source && temperature < source + length) {
                    humidity = temperature - source + destination;
                    break;
                } else {
                    if (index != humidityToLocationHeadLineIndex - 2) {
                        continue;
                    }
                    humidity = temperature;
                    break;
                }
            }

        }

        for (int index = humidityToLocationHeadLineIndex + 1; index < allLines.size(); index++) {
            String tempStr = allLines.get(index);
            String[] tempStrArray = tempStr.split(" ");
            long destination = Long.parseLong(tempStrArray[0]);
            long source = Long.parseLong(tempStrArray[1]);
            long length = Long.parseLong(tempStrArray[2]);
            if (humidity < source) {
                if (humidity > source && humidity < source + length) {
                    continue;
                } else {
                    location = humidity;
                }
            } else if (humidity == source) {
                location = destination;
                break;
            } else {
                if (humidity > source && humidity < source + length) {
                    location = humidity - source + destination;
                    break;
                } else {
                    if (index != allLines.size() - 1) {
                        continue;
                    }
                    location = humidity;
                    break;
                }
            }

        }

        System.out.println("seed = "+ seed+
                " soil = "+soil+
                " fertilizer = "+fertilizer+
                " water = "+water+
                " light = "+light+
                " temperature = "+temperature+
                " humidity = "+humidity+
                " location = "+location);
*/



        return location;

    }



    private static long findLowestLocation(String fileName){

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


        long lowestLocation = 0;

        String seedsLine = allLines.get(0);
//        String[] seedsArray = seedsLine.split(" ");// part 1

        int seedToSoilHeadLineIndex = 2;
        int soilToFertilizerHeadLineIndex = -1;
        int fertilizerToWaterHeadLineIndex = -1;
        int waterToLightHeadLineIndex = -1;
        int lightToTemperatureHeadLineIndex = -1;
        int temperatureToHumidityHeadLineIndex = -1;
        int humidityToLocationHeadLineIndex = -1;

        for (int index = 4; index < allLines.size(); index++){
            if("soil-to-fertilizer map:".equals(allLines.get(index))){
                soilToFertilizerHeadLineIndex = index;
            }
            if("fertilizer-to-water map:".equals(allLines.get(index))){
                fertilizerToWaterHeadLineIndex = index;
            }
            if("water-to-light map:".equals(allLines.get(index))){
                waterToLightHeadLineIndex = index;
            }
            if("light-to-temperature map:".equals(allLines.get(index))){
                lightToTemperatureHeadLineIndex = index;
            }
            if("temperature-to-humidity map:".equals(allLines.get(index))){
                temperatureToHumidityHeadLineIndex = index;
            }
            if("humidity-to-location map:".equals(allLines.get(index))){
                humidityToLocationHeadLineIndex = index;
            }
        }

        String[] seedRangePairsArray = getSeedRangePairs(seedsLine); /***PART 2 START***/

        /***PART 2 ***/
        fillMap(seedToSoilHeadLineIndex,soilToFertilizerHeadLineIndex,"seed_soil",allLines);
        fillMap(soilToFertilizerHeadLineIndex,fertilizerToWaterHeadLineIndex,"soil_fertilizer",allLines);
        fillMap(fertilizerToWaterHeadLineIndex,waterToLightHeadLineIndex,"fertilizer_water",allLines);
        fillMap(waterToLightHeadLineIndex,lightToTemperatureHeadLineIndex,"water_light",allLines);
        fillMap(lightToTemperatureHeadLineIndex,temperatureToHumidityHeadLineIndex,"light_temperature",allLines);
        fillMap(temperatureToHumidityHeadLineIndex,humidityToLocationHeadLineIndex,"temperature_humidity",allLines);
        fillMap(humidityToLocationHeadLineIndex, allLines.size()+1,"humidity_location",allLines);


//        ArrayList<long[]> locationIntervalArrayList = new ArrayList<>();

        /***PART 2 START***/
        for(int pairIndex = 0; pairIndex<seedRangePairsArray.length-1; pairIndex+=2) {

            long starting = Long.parseLong(seedRangePairsArray[pairIndex]);
            long range = Long.parseLong(seedRangePairsArray[pairIndex + 1]);
            System.out.println("seed = " + starting + " range = " + range);

            long[] interval = new long[2];
            interval[0] = starting;
            interval[1] = starting + range -1;

            ArrayList<long[]> seedIntervalArrayList = new ArrayList<>();
            seedIntervalArrayList.add(interval);

            ArrayList<long[]> soilIntervalArrayList = mappingNextMap(seed_soil_map,seedIntervalArrayList);
            System.out.println("soilIntervalArrayList");
            for(int index = 0; index<soilIntervalArrayList.size();index++){
                System.out.println("["+soilIntervalArrayList.get(index)[0]+","+soilIntervalArrayList.get(index)[1]+"]");
            }
            ArrayList<long[]> fertilizerIntervalArrayList = mappingNextMap(soil_fertilizer_map,soilIntervalArrayList);
            System.out.println("fertilizerIntervalArrayList");
            for(int index = 0; index<fertilizerIntervalArrayList.size();index++){
                System.out.println("["+fertilizerIntervalArrayList.get(index)[0]+","+fertilizerIntervalArrayList.get(index)[1]+"]");
            }
            ArrayList<long[]> waterIntervalArrayList = mappingNextMap(fertilizer_water_map,fertilizerIntervalArrayList);
            System.out.println("waterIntervalArrayList");
            for(int index = 0; index<waterIntervalArrayList.size();index++){
                System.out.println("["+waterIntervalArrayList.get(index)[0]+","+waterIntervalArrayList.get(index)[1]+"]");
            }
            ArrayList<long[]> lightIntervalArrayList = mappingNextMap(water_light_map,waterIntervalArrayList);
            System.out.println("lightIntervalArrayList");
            for(int index = 0; index<lightIntervalArrayList.size();index++){
                System.out.println("["+lightIntervalArrayList.get(index)[0]+","+lightIntervalArrayList.get(index)[1]+"]");
            }
            ArrayList<long[]> temperatureIntervalArrayList = mappingNextMap(light_temperature_map,lightIntervalArrayList);
            System.out.println("temperatureIntervalArrayList");
            for(int index = 0; index<temperatureIntervalArrayList.size();index++){
                System.out.println("["+temperatureIntervalArrayList.get(index)[0]+","+temperatureIntervalArrayList.get(index)[1]+"]");
            }
            ArrayList<long[]> humidityIntervalArrayList = mappingNextMap(temperature_humidity_map,temperatureIntervalArrayList);
            System.out.println("humidityIntervalArrayList");
            for(int index = 0; index<humidityIntervalArrayList.size();index++){
                System.out.println("["+humidityIntervalArrayList.get(index)[0]+","+humidityIntervalArrayList.get(index)[1]+"]");
            }
            mappingLocationMap(humidityIntervalArrayList);
            System.out.println("locationIntervalArrayList");
            for(int index = 0; index<locationIntervalArrayList.size();index++){
                System.out.println("["+locationIntervalArrayList.get(index)[0]+","+locationIntervalArrayList.get(index)[1]+"]");
            }

            /*
            for(int index = 0; index<seed_soil_map.size();index++){
                long[] sourceInterval = seed_soil_map.get(index).get(0);
                long[] destinationInterval = seed_soil_map.get(index).get(1);

                long sourceLow = sourceInterval[0];
                long sourceHigh = sourceInterval[1];

                long destinationLow = destinationInterval[0];
                long destinationHigh = destinationInterval[1];

                if(seedHigh < sourceLow){
                    if(index != seed_soil_map.size() -1){
                        continue;
                    }
                    else{
                        soilLow = seedLow;
                        soilHigh = seedHigh;
                        long[] interval = new long[2];
                        interval[0] = soilLow;
                        interval[1] = soilHigh;
                        soilIntervals.add(interval);
                    }

                }
                else if(seedHigh > sourceLow){
                    if(seedLow > sourceLow && seedHigh < sourceHigh){
                        long difference = seedLow - sourceLow;
                        soilLow = destinationLow + difference;
                        soilHigh = soilLow + (seedHigh - seedLow);
                        long[] interval = new long[2];
                        interval[0] = soilLow;
                        interval[1] = soilHigh;
                        soilIntervals.add(interval);
                    }
                    else if(seedLow > sourceHigh) {
                        if (index != seed_soil_map.size() - 1) {
                            continue;
                        } else {
                            soilLow = seedLow;
                            soilHigh = seedHigh;
                            long[] interval = new long[2];
                            interval[0] = soilLow;
                            interval[1] = soilHigh;
                            soilIntervals.add(interval);
                        }
                    } else if(seedLow < sourceLow){
                        soilLow = destinationLow;
                        // break the interval
                        soilHigh = soilLow + (seedHigh - seedLow);
                        long[] interval = new long[2];
                        interval[0] = soilLow;
                        interval[1] = soilHigh;
                        soilIntervals.add(interval);
                    }
                    else if(seedLow < sourceHigh){
                        long difference = seedLow - sourceLow;
                        soilLow = destinationLow + difference;
                        // break the interval
                        soilHigh = destinationHigh;
                        long[] interval = new long[2];
                        interval[0] = soilLow;
                        interval[1] = soilHigh;
                        soilIntervals.add(interval);

//                        long remain =
                    }
                }
                else if(seedHigh == sourceLow){
                    soilLow = destinationLow;
                    soilHigh = destinationLow;
                    long[] interval = new long[2];
                    interval[0] = soilLow;
                    interval[1] = soilHigh;
                    soilIntervals.add(interval);
                }
            }

            System.out.println("soil intervals");
            for(int index = 0; index<soilIntervals.size();index++){
                System.out.println("["+soilIntervals.get(index)[0]+","+soilIntervals.get(index)[1]+"]");
            }

            // check soil_fertilizer
            ArrayList<long[]> fertilizerIntervals = new ArrayList<>();
            long fertilizerLow = -1;
            long fertilizerHigh = -1;

            for(int soilIndex = 0; soilIndex < soilIntervals.size(); soilIndex++){
                soilLow = soilIntervals.get(soilIndex)[0];
                soilHigh = soilIntervals.get(soilIndex)[1];

                for(int index = 0; index<soil_fertilizer_map.size();index++){
                    long[] sourceInterval = soil_fertilizer_map.get(index).get(0);
                    long[] destinationInterval = soil_fertilizer_map.get(index).get(1);

                    long sourceLow = sourceInterval[0];
                    long sourceHigh = sourceInterval[1];

                    long destinationLow = destinationInterval[0];
                    long destinationHigh = destinationInterval[1];

                    if(soilHigh < sourceLow){
                        if(index != soil_fertilizer_map.size()-1){
                            continue;
                        }
                        else{
                            fertilizerLow = soilLow;
                            fertilizerHigh = soilHigh;
                            long[] interval = new long[2];
                            interval[0] = fertilizerLow;
                            interval[1] = fertilizerHigh;
                            fertilizerIntervals.add(interval);
                        }
                    }
                    else if(soilHigh > sourceLow){
                        if(soilLow > sourceLow && soilHigh < sourceHigh){
                            long difference = soilLow - sourceLow;
                            fertilizerLow = destinationLow + difference;
                            fertilizerHigh = fertilizerLow + (soilHigh - soilLow);
                            long[] interval = new long[2];
                            interval[0] = fertilizerLow;
                            interval[1] = fertilizerHigh;
                            fertilizerIntervals.add(interval);
                        }
                        else if(soilLow > sourceHigh) {
                            if (index != soil_fertilizer_map.size() - 1) {
                                continue;
                            } else {
                                fertilizerLow = soilLow;
                                fertilizerHigh = soilHigh;
                                long[] interval = new long[2];
                                interval[0] = fertilizerLow;
                                interval[1] = fertilizerHigh;
                                fertilizerIntervals.add(interval);
                            }
                        } else if(soilLow < sourceLow){
                            fertilizerLow = destinationLow;
                            // break the interval
                            fertilizerHigh = fertilizerLow + (soilHigh - soilLow);
                            long[] interval = new long[2];
                            interval[0] = fertilizerLow;
                            interval[1] = fertilizerHigh;
                            fertilizerIntervals.add(interval);
                        }
                        else if(soilLow < sourceHigh){
                            long difference = soilLow - sourceLow;
                            fertilizerLow = destinationLow + difference;
                            // break the interval
                            fertilizerHigh = destinationHigh;
                            long[] interval = new long[2];
                            interval[0] = fertilizerLow;
                            interval[1] = fertilizerHigh;
                            fertilizerIntervals.add(interval);

//                        long remain =
                        }
                    }
                    else if(soilHigh == sourceLow){
                        fertilizerLow = destinationLow;
                        fertilizerHigh = destinationLow;
                        long[] interval = new long[2];
                        interval[0] = fertilizerLow;
                        interval[1] = fertilizerHigh;
                        fertilizerIntervals.add(interval);
                    }
                }

                System.out.println("fertilizer intervals");
                for(int index = 0; index<fertilizerIntervals.size();index++){
                    System.out.println("["+fertilizerIntervals.get(index)[0]+","+fertilizerIntervals.get(index)[1]+"]");
                }

                // check fertilizer_water
                ArrayList<long[]> waterIntervals = new ArrayList<>();
                long waterLow = -1;
                long waterHigh = -1;

                for(int fertilizerIndex = 0; fertilizerIndex < fertilizerIntervals.size(); fertilizerIndex++) {
                    fertilizerLow = fertilizerIntervals.get(fertilizerIndex)[0];
                    fertilizerHigh = fertilizerIntervals.get(fertilizerIndex)[1];

                    for (int index = 0; index < fertilizer_water_map.size(); index++) {
                        long[] sourceInterval = fertilizer_water_map.get(index).get(0);
                        long[] destinationInterval = fertilizer_water_map.get(index).get(1);

                        long sourceLow = sourceInterval[0];
                        long sourceHigh = sourceInterval[1];

                        long destinationLow = destinationInterval[0];
                        long destinationHigh = destinationInterval[1];

                        if (fertilizerHigh < sourceLow) {
                            if (index != fertilizer_water_map.size() - 1) {
                                continue;
                            } else {
                                waterLow = fertilizerLow;
                                waterHigh = fertilizerHigh;
                                long[] interval = new long[2];
                                interval[0] = waterLow;
                                interval[1] = waterHigh;
                                waterIntervals.add(interval);
                            }
                        } else if (fertilizerHigh > sourceLow) {
                            if (fertilizerLow > sourceLow && fertilizerHigh < sourceHigh) {
                                long difference = fertilizerLow - sourceLow;
                                waterLow = destinationLow + difference;
                                waterHigh = waterLow + (fertilizerHigh - fertilizerLow);
                                long[] interval = new long[2];
                                interval[0] = waterLow;
                                interval[1] = waterHigh;
                                waterIntervals.add(interval);
                            } else if (fertilizerLow > sourceHigh) {
                                if (index != fertilizer_water_map.size() - 1) {
                                    continue;
                                } else {
                                    waterLow = fertilizerLow;
                                    waterHigh = fertilizerHigh;
                                    long[] interval = new long[2];
                                    interval[0] = waterLow;
                                    interval[1] = waterHigh;
                                    waterIntervals.add(interval);
                                }
                            } else if(fertilizerLow < sourceLow){
                                waterLow = destinationLow;
                                // break the interval
                                waterHigh = waterLow + (fertilizerHigh - fertilizerLow);
                                long[] interval = new long[2];
                                interval[0] = waterLow;
                                interval[1] = waterHigh;
                                waterIntervals.add(interval);
                            } else if (fertilizerLow < sourceHigh) {
                                long difference = fertilizerLow - sourceLow;
                                waterLow = destinationLow + difference;
                                // break the interval
                                waterHigh = destinationHigh;
                                long[] interval = new long[2];
                                interval[0] = waterLow;
                                interval[1] = waterHigh;
                                waterIntervals.add(interval);

//                        long remain =
                            }
                        } else if (fertilizerHigh == sourceLow) {
                            waterLow = destinationLow;
                            waterHigh = destinationLow;
                            long[] interval = new long[2];
                            interval[0] = waterLow;
                            interval[1] = waterHigh;
                            waterIntervals.add(interval);
                        }
                    }
                }

                System.out.println("water intervals");
                for(int index = 0; index<waterIntervals.size();index++){
                    System.out.println("["+waterIntervals.get(index)[0]+","+waterIntervals.get(index)[1]+"]");
                }

                // check water_light
                ArrayList<long[]> lightIntervals = new ArrayList<>();
                long lightLow = -1;
                long lightHigh = -1;

                for(int waterIndex = 0; waterIndex < waterIntervals.size(); waterIndex++) {
                    waterLow = waterIntervals.get(waterIndex)[0];
                    waterHigh = waterIntervals.get(waterIndex)[1];

                    for (int index = 0; index < water_light_map.size(); index++) {
                        long[] sourceInterval = water_light_map.get(index).get(0);
                        long[] destinationInterval = water_light_map.get(index).get(1);

                        long sourceLow = sourceInterval[0];
                        long sourceHigh = sourceInterval[1];

                        long destinationLow = destinationInterval[0];
                        long destinationHigh = destinationInterval[1];

                        if (waterHigh < sourceLow) {
                            if (index != water_light_map.size() - 1) {
                                continue;
                            } else {
                                lightLow = waterLow;
                                lightHigh = waterHigh;
                                long[] interval = new long[2];
                                interval[0] = lightLow;
                                interval[1] = lightHigh;
                                lightIntervals.add(interval);
                            }
                        } else if (waterHigh > sourceLow) {
                            if (waterLow > sourceLow && waterHigh < sourceHigh) {
                                long difference = waterLow - sourceLow;
                                lightLow = destinationLow + difference;
                                lightHigh = lightLow + (waterHigh - waterLow);
                                long[] interval = new long[2];
                                interval[0] = lightLow;
                                interval[1] = lightHigh;
                                lightIntervals.add(interval);
                            } else if (waterLow > sourceHigh) {
                                if (index != water_light_map.size() - 1) {
                                    continue;
                                } else {
                                    lightLow = waterLow;
                                    lightHigh = waterHigh;
                                    long[] interval = new long[2];
                                    interval[0] = lightLow;
                                    interval[1] = lightHigh;
                                    lightIntervals.add(interval);
                                }
                            } else if(waterLow < sourceLow){
                                lightLow = destinationLow;
                                // break the interval
                                lightHigh = lightLow + (waterHigh - waterLow);
                                long[] interval = new long[2];
                                interval[0] = lightLow;
                                interval[1] = lightHigh;
                                lightIntervals.add(interval);
                            } else if (waterLow < sourceHigh) {
                                long difference = waterLow - sourceLow;
                                lightLow = destinationLow + difference;
                                // break the interval
                                lightHigh = destinationHigh;
                                long[] interval = new long[2];
                                interval[0] = lightLow;
                                interval[1] = lightHigh;
                                lightIntervals.add(interval);

//                        long remain =
                            }
                        } else if (waterHigh == sourceLow) {
                            lightLow = destinationLow;
                            lightHigh = destinationLow;
                            long[] interval = new long[2];
                            interval[0] = lightLow;
                            interval[1] = lightHigh;
                            lightIntervals.add(interval);
                        }
                    }
                }

                System.out.println("light intervals");
                for(int index = 0; index<lightIntervals.size();index++){
                    System.out.println("["+lightIntervals.get(index)[0]+","+lightIntervals.get(index)[1]+"]");
                }

                // check light_temperature
                ArrayList<long[]> temperatureIntervals = new ArrayList<>();
                long temperatureLow = -1;
                long temperatureHigh = -1;


                for(int lightIndex = 0; lightIndex < lightIntervals.size(); lightIndex++) {
                    lightLow = lightIntervals.get(lightIndex)[0];
                    lightHigh = lightIntervals.get(lightIndex)[1];

                    for (int index = 0; index < light_temperature_map.size(); index++) {
                        long[] sourceInterval = light_temperature_map.get(index).get(0);
                        long[] destinationInterval = light_temperature_map.get(index).get(1);

                        long sourceLow = sourceInterval[0];
                        long sourceHigh = sourceInterval[1];

                        long destinationLow = destinationInterval[0];
                        long destinationHigh = destinationInterval[1];

                        if (lightHigh < sourceLow) {
                            if (index != light_temperature_map.size() - 1) {
                                continue;
                            } else {
                                temperatureLow = lightLow;
                                temperatureHigh = lightHigh;
                                long[] interval = new long[2];
                                interval[0] = temperatureLow;
                                interval[1] = temperatureHigh;
                                temperatureIntervals.add(interval);
                            }
                        } else if (lightHigh > sourceLow) {
                            if (lightLow > sourceLow && lightHigh < sourceHigh) {
                                long difference = lightLow - sourceLow;
                                temperatureLow = destinationLow + difference;
                                temperatureHigh = temperatureLow + (lightHigh - lightLow);
                                long[] interval = new long[2];
                                interval[0] = temperatureLow;
                                interval[1] = temperatureHigh;
                                temperatureIntervals.add(interval);
                            } else if (lightLow > sourceHigh) {
                                if (index != light_temperature_map.size() - 1) {
                                    continue;
                                } else {
                                    temperatureLow = lightLow;
                                    temperatureHigh = lightHigh;
                                    long[] interval = new long[2];
                                    interval[0] = temperatureLow;
                                    interval[1] = temperatureHigh;
                                    temperatureIntervals.add(interval);
                                }
                            } else if(lightLow < sourceLow){
                                temperatureLow = destinationLow;
                                // break the interval
                                temperatureHigh = temperatureLow + (lightHigh - lightLow);
                                long[] interval = new long[2];
                                interval[0] = temperatureLow;
                                interval[1] = temperatureHigh;
                                temperatureIntervals.add(interval);
                            } else if (lightLow < sourceHigh) {
                                long difference = lightLow - sourceLow;
                                temperatureLow = destinationLow + difference;
                                // break the interval
                                temperatureHigh = destinationHigh;
                                long[] interval = new long[2];
                                interval[0] = temperatureLow;
                                interval[1] = temperatureHigh;
                                temperatureIntervals.add(interval);

//                        long remain =
                            }

                        } else if (lightHigh == sourceLow) {
                            temperatureLow = destinationLow;
                            temperatureHigh = destinationLow;
                            long[] interval = new long[2];
                            interval[0] = temperatureLow;
                            interval[1] = temperatureHigh;
                            temperatureIntervals.add(interval);
                        }
                    }
                }

                System.out.println("temperature intervals");
                for(int index = 0; index<temperatureIntervals.size();index++){
                    System.out.println("["+temperatureIntervals.get(index)[0]+","+temperatureIntervals.get(index)[1]+"]");
                }


                // check temperature_humidity
                ArrayList<long[]> humidityIntervals = new ArrayList<>();
                long humidityLow = -1;
                long humidityHigh = -1;

                for(int temperatureIndex = 0; temperatureIndex < temperatureIntervals.size(); temperatureIndex++) {
                    temperatureLow = temperatureIntervals.get(temperatureIndex)[0];
                    temperatureHigh = temperatureIntervals.get(temperatureIndex)[1];

                    for (int index = 0; index < temperature_humidity_map.size(); index++) {
                        long[] sourceInterval = temperature_humidity_map.get(index).get(0);
                        long[] destinationInterval = temperature_humidity_map.get(index).get(1);

                        long sourceLow = sourceInterval[0];
                        long sourceHigh = sourceInterval[1];

                        long destinationLow = destinationInterval[0];
                        long destinationHigh = destinationInterval[1];

                        if (temperatureHigh < sourceLow) {
                            if (index != temperature_humidity_map.size() - 1) {
                                continue;
                            } else {
                                humidityLow = temperatureLow;
                                humidityHigh = temperatureHigh;
                                long[] interval = new long[2];
                                interval[0] = humidityLow;
                                interval[1] = humidityHigh;
                                humidityIntervals.add(interval);
                            }
                        } else if (temperatureHigh > sourceLow) {
                            if (temperatureLow > sourceLow && temperatureHigh < sourceHigh) {
                                long difference = temperatureLow - sourceLow;
                                humidityLow = destinationLow + difference;
                                humidityHigh = humidityLow + (temperatureHigh - temperatureLow);
                                long[] interval = new long[2];
                                interval[0] = humidityLow;
                                interval[1] = humidityHigh;
                                humidityIntervals.add(interval);
                            } else if (temperatureLow > sourceHigh) {
                                if (index != temperature_humidity_map.size() - 1) {
                                    continue;
                                } else {
                                    humidityLow = temperatureLow;
                                    humidityHigh = temperatureHigh;
                                    long[] interval = new long[2];
                                    interval[0] = humidityLow;
                                    interval[1] = humidityHigh;
                                    humidityIntervals.add(interval);
                                }
                            } else if(temperatureLow < sourceLow){
                                humidityLow = destinationLow;
                                // break the interval
                                humidityHigh = humidityLow + (temperatureHigh - temperatureLow);
                                long[] interval = new long[2];
                                interval[0] = humidityLow;
                                interval[1] = humidityHigh;
                                humidityIntervals.add(interval);
                            } else if (temperatureLow < sourceHigh) {
                                long difference = temperatureLow - sourceLow;
                                humidityLow = destinationLow + difference;
                                // break the interval
                                humidityHigh = destinationHigh;
                                long[] interval = new long[2];
                                interval[0] = humidityLow;
                                interval[1] = humidityHigh;
                                humidityIntervals.add(interval);

//                        long remain =
                            }
                        } else if (temperatureHigh == sourceLow) {
                            humidityLow = destinationLow;
                            humidityHigh = destinationLow;
                            long[] interval = new long[2];
                            interval[0] = humidityLow;
                            interval[1] = humidityHigh;
                            humidityIntervals.add(interval);
                        }
                    }
                }

                System.out.println("humidity intervals");
                for(int index = 0; index<humidityIntervals.size();index++){
                    System.out.println("["+humidityIntervals.get(index)[0]+","+humidityIntervals.get(index)[1]+"]");
                }

                // check humidity_location
                ArrayList<long[]> locationIntervals = new ArrayList<>();
                long locationLow = -1;
                long locationHigh = -1;


                for(int humidityIndex = 0; humidityIndex < humidityIntervals.size(); humidityIndex++) {
                    humidityLow = humidityIntervals.get(humidityIndex)[0];
                    humidityHigh = humidityIntervals.get(humidityIndex)[1];

                    for (int index = 0; index < humidity_location_map.size(); index++) {
                        long[] sourceInterval = humidity_location_map.get(index).get(0);
                        long[] destinationInterval = humidity_location_map.get(index).get(1);

                        long sourceLow = sourceInterval[0];
                        long sourceHigh = sourceInterval[1];

                        long destinationLow = destinationInterval[0];
                        long destinationHigh = destinationInterval[1];

                        if (humidityHigh < sourceLow) {
                            if (index != humidity_location_map.size() - 1) {
                                continue;
                            } else {
                                locationLow = humidityLow;
                                locationHigh = humidityHigh;
                                long[] interval = new long[2];
                                interval[0] = locationLow;
                                interval[1] = locationHigh;
                                locationIntervals.add(interval);
                            }
                        } else if (humidityHigh > sourceLow) {
                            if (humidityLow > sourceLow && humidityHigh < sourceHigh) {
                                long difference = humidityLow - sourceLow;
                                locationLow = destinationLow + difference;
                                locationHigh = locationLow + (humidityHigh - humidityLow);
                                long[] interval = new long[2];
                                interval[0] = locationLow;
                                interval[1] = locationHigh;
                                locationIntervals.add(interval);
                            } else if (humidityLow > sourceHigh) {
                                if (index != humidity_location_map.size() - 1) {
                                    continue;
                                } else {
                                    locationLow = humidityLow;
                                    locationHigh = humidityHigh;
                                    long[] interval = new long[2];
                                    interval[0] = locationLow;
                                    interval[1] = locationHigh;
                                    locationIntervals.add(interval);
                                }
                            } else if(humidityLow < sourceLow){
                                locationLow = destinationLow;
                                // break the interval
                                locationHigh = locationLow + (humidityHigh - humidityLow);
                                long[] interval = new long[2];
                                interval[0] = locationLow;
                                interval[1] = locationHigh;
                                locationIntervals.add(interval);
                            } else if (humidityLow < sourceHigh) {
                                long difference = humidityLow - sourceLow;
                                locationLow = destinationLow + difference;
                                // break the interval
                                locationHigh = destinationHigh;
                                long[] interval = new long[2];
                                interval[0] = locationLow;
                                interval[1] = locationHigh;
                                locationIntervals.add(interval);

//                        long remain =
                            }
                        } else if (humidityHigh == sourceLow) {
                            locationLow = destinationLow;
                            locationHigh = destinationLow;
                            long[] interval = new long[2];
                            interval[0] = locationLow;
                            interval[1] = locationHigh;
                            locationIntervals.add(interval);
                        }
                    }
                }

//                System.out.println("location intervals");
//                for(int index = 0; index<locationIntervals.size();index++){
//                    System.out.println("["+locationIntervals.get(index)[0]+","+locationIntervals.get(index)[1]+"]");
//                }

                // find the lowest location
                for(int index = 0; index<locationIntervals.size(); index++){

                    System.out.println("["+locationIntervals.get(index)[0]+","+locationIntervals.get(index)[1]+"]");
                    long lowLocation = locationIntervals.get(index)[0];
                    if(lowestLocation==0){
                        lowestLocation = lowLocation;
                    }
                    else if(lowLocation < lowestLocation){
                        lowestLocation = lowLocation;
                    }
//                    System.out.println("lowestLocation = "+lowestLocation+" lowLocation = "+lowLocation);
                }




                */

            }

        for (int item = 0; item < locationIntervalArrayList.size(); item++) {

            long location = locationIntervalArrayList.get(item)[0];

            if(lowestLocation==0){
                lowestLocation = location;
            }
            else if(location < lowestLocation){
                lowestLocation = location;
            }
//            System.out.println("lowestLocation = "+lowestLocation+" location = "+location);
        }

            /***PART 1 START***/
/*
            for(long seedItem = starting; seedItem<range+starting ; seedItem++){

                long location = getLocation(seedItem,soilToFertilizerHeadLineIndex,fertilizerToWaterHeadLineIndex,
                        waterToLightHeadLineIndex, lightToTemperatureHeadLineIndex, temperatureToHumidityHeadLineIndex,
                        humidityToLocationHeadLineIndex, allLines);

                if(lowestLocation==0){
                    lowestLocation = location;
                }
                else if(location < lowestLocation){
                    lowestLocation = location;
                }
//                System.out.println("lowestLocation = "+lowestLocation+" location = "+location);
            }
        }
*/
            /***PART 1 END***/

//            System.out.println("seed = "+ seed+
//                    " soil = "+soil+
//                    " fertilizer = "+fertilizer+
//                    " water = "+water+
//                    " light = "+light+
//                    " temperature = "+temperature+
//                    " humidity = "+humidity+
//                    " location = "+location);

        /***PART 2 END***/

        return lowestLocation;
    }

    private static void mappingLocationMap(ArrayList<long[]> inputArray){

//        ArrayList<long[]> output = new ArrayList<>();

        for(int index = 0; index<inputArray.size();index++) {
            long[] item = inputArray.get(index);
            long itemLow = item[0];
            long itemHigh = item[1];

            for (int targetIndex = 0; targetIndex < humidity_location_map.size(); targetIndex++) {
                ArrayList<long[]> targetArray = humidity_location_map.get(targetIndex);
                long[] sourceInterval = targetArray.get(0);
                long[] destinationInterval = targetArray.get(1);

                long sourceIntervalLow = sourceInterval[0];
                long sourceIntervalHigh = sourceInterval[1];
                long destinationLow = destinationInterval[0];
                long destinationHigh = destinationInterval[1];

                if (itemLow > sourceIntervalLow && itemHigh < sourceIntervalHigh) {
                    long[] tempArray = new long[2];
                    long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                    long tempArrayHigh = tempArrayLow + (itemHigh - itemLow);
                    tempArray[0] = tempArrayLow;
                    tempArray[1] = tempArrayHigh;
                    locationIntervalArrayList.add(tempArray);
                    break;
                } else if (itemLow > sourceIntervalHigh || itemHigh < sourceIntervalLow) {
                    if (targetIndex != humidity_location_map.size() - 1) {
                        continue;
                    } else {
                        long[] tempArray = new long[2];
                        long tempArrayLow = itemLow;
                        long tempArrayHigh = itemHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);
                        break;
                    }
                } else if (itemLow < sourceIntervalHigh && itemLow != sourceIntervalLow) {
                    if (itemHigh == sourceIntervalHigh) {
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);
                        break;
                    } else if (itemHigh > sourceIntervalHigh) {
                        // break interval
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);

                        itemLow = sourceIntervalHigh + 1;
                        continue;
                    }
                } else if (itemLow == sourceIntervalLow) {
                    if (itemHigh < sourceIntervalHigh) {
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = tempArrayLow + (itemHigh - itemLow);
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);
                        break;
                    } else if (itemHigh > sourceIntervalHigh) {
                        // break interval
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);

                        itemLow = sourceIntervalHigh + 1;
                        continue;
                    } else if (itemHigh == sourceIntervalHigh) {
                        long[] tempArray = new long[2];
                        long tempArrayLow = destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        locationIntervalArrayList.add(tempArray);
                        break;
                    }
                }


            }
        }

    }

    private static ArrayList<long[]> mappingNextMap (ArrayList<ArrayList<long[]>> targetArrayList,
                                                                ArrayList<long[]> inputArray){
        ArrayList<long[]> output = new ArrayList<>();

        for(int index = 0; index<inputArray.size();index++){
            long[] item = inputArray.get(index);
            long itemLow = item[0];
            long itemHigh = item[1];

            for(int targetIndex = 0; targetIndex<targetArrayList.size(); targetIndex++){
                ArrayList<long[]> targetArray = targetArrayList.get(targetIndex);
                long[] sourceInterval = targetArray.get(0);
                long[] destinationInterval = targetArray.get(1);

                long sourceIntervalLow = sourceInterval[0];
                long sourceIntervalHigh = sourceInterval[1];
                long destinationLow = destinationInterval[0];
                long destinationHigh = destinationInterval[1];

                if(itemLow > sourceIntervalLow && itemHigh < sourceIntervalHigh){
                    long[] tempArray = new long[2];
                    long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                    long tempArrayHigh = tempArrayLow + (itemHigh - itemLow);
                    tempArray[0] = tempArrayLow;
                    tempArray[1] = tempArrayHigh;
                    output.add(tempArray);
                    break;
                }
                else if(itemLow > sourceIntervalHigh || itemHigh < sourceIntervalLow){
                    if(targetIndex != targetArrayList.size()-1){
                        continue;
                    }
                    else{
                        long[] tempArray = new long[2];
                        long tempArrayLow = itemLow;
                        long tempArrayHigh = itemHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);
                        break;
                    }
                }
                else if(itemLow < sourceIntervalHigh && itemLow != sourceIntervalLow){
                    if(itemHigh == sourceIntervalHigh){
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);
                        break;
                    }
                    else if(itemHigh > sourceIntervalHigh){
                        // break interval
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);

                        itemLow = sourceIntervalHigh+1;
                        continue;
                    }
                }
                else if(itemLow == sourceIntervalLow){
                    if(itemHigh < sourceIntervalHigh){
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = tempArrayLow + (itemHigh - itemLow);
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);
                        break;
                    }
                    else if(itemHigh > sourceIntervalHigh){
                        // break interval
                        long[] tempArray = new long[2];
                        long tempArrayLow = (itemLow - sourceIntervalLow) + destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);

                        itemLow = sourceIntervalHigh+1;
                        continue;
                    }
                    else if(itemHigh == sourceIntervalHigh){
                        long[] tempArray = new long[2];
                        long tempArrayLow = destinationLow;
                        long tempArrayHigh = destinationHigh;
                        tempArray[0] = tempArrayLow;
                        tempArray[1] = tempArrayHigh;
                        output.add(tempArray);
                        break;
                    }
                }



            }
        }




        return output;
    }

}
