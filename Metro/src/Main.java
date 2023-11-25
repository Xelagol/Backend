import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static ArrayList<String> filePathDates = new ArrayList<>();
    static ArrayList<String> filePathDepths = new ArrayList<>();
    static String nameLine = null;


    public static void main(String[] args) throws IOException {

        Map<String, String> date = new HashMap<>();
        Map<String, String> depth = new HashMap<>();
        Map<String, String> stations = new HashMap<>();
        Map<String, String> connections = new HashMap<>();
        Map<String, String> lines = new TreeMap<>();
        ArrayList<String> metro = new ArrayList<>();
        ArrayList<String> metroStations;
        ArrayList<String> metroLines;



        File path = new File("FilesAndNetwork/DataCollector/data");
        File pathMap = new File("FilesAndNetwork/DataCollector/data/map.json");
        parseFolders(path);
        countStation(pathMap.toString());

        lines.putAll(parseLine());                                   //номер и название линии
        connections.putAll(parseConnection());                       //пересадки
        stations.putAll(parseStation());                             //станция и номер линии
        date.putAll(createDates(filePathDates));                     //станция и дата
        depth.putAll(createDepths(filePathDepths));                  //станция и глубина
        metroStations = createStationArray(stations, lines);
        metroLines = createLinesArray(lines);

        stations.forEach((n, l) -> {

            String ques = depth.get(n);
            if (ques == null) {
                ques = "?";
            }

            metro.add("{"
                    + "\"name\" : " + "\"" + n + "\""
                    + ",\"line\" : " + "\"" + lines.get(l) + "\""
                    + (date.get(n) == null ? "" : (",\"date\" : " + "\"" + date.get(n)) + "\"")
                    + (ques.equals("?") ? "" : (",\"depth\" : " + "\"" + depth.get(n)) + "\"")
                    + (connections.get(n) == "true" ? ",\"hasConnection\" : " + "\"" + connections.get(n) + "\"" : "")
                    + "}"
            );
        });
        writeMetroJson(metroStations, metroLines);
        writeStationsJson(metro);

    }


    private static ArrayList<String> createStationArray(Map<String, String> stations, Map<String, String> lines) {
        ArrayList<String> stationsAndLines = new ArrayList<>();

        lines.forEach((num, nam) -> {
                    ArrayList<String> list = new ArrayList<>();

                    stations.forEach((key, value) -> {
                        if (value.equals(num)) {
                            list.add("\"" + key + "\"");
                        }
                    });
                    stationsAndLines.add("{\"" + nam + "\":" + list + "}");
                }
        );
        return stationsAndLines;
    }

    private static ArrayList<String> createLinesArray(Map<String, String> lines) {
        ArrayList<String> linesArray = new ArrayList<>();

        lines.forEach((num, nam) -> {
                    if (num.equals("11A") || num.equals("D1") || num.equals("D2")) {
                        linesArray.add("{\"number\":\"" + num + "\"," + "\"name\":\"" + nam + "\"}");
                    } else {
                        linesArray.add("{\"number\":" + num + "," + "\"name\":\"" + nam + "\"}");
                    }
                }
        );
        return linesArray;
    }

    private static Map<String, String> parseLine() {
        Map<String, String> line = new HashMap<>();
        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io//").get();
            Elements lnMosMetro = doc.select("span.js-metro-line");
            lnMosMetro.forEach(ln -> line.put(ln.attr("data-line"), ln.text()));
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println("Don't reading");
        }
        return line;
    }

    private static Map<String, String> parseConnection() {
        Map<String, String> connectionStation = new HashMap<>();
        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io//").get();
            Elements mosMetro = doc.select(" p");

            mosMetro.forEach(st -> {
                if (!st.lastElementChild().attr("title").isEmpty()) {
                    connectionStation.put(String.valueOf(st.childNode(1).firstChild()), "true");
                } else {
                    connectionStation.put(String.valueOf(st.childNode(1).firstChild()), "false");
                }
            });
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println("Don't reading");
        }
        return connectionStation;
    }

    private static Map<String, String> parseStation() {
        Map<String, String> line = new HashMap<>();
        Map<String, String> lineAndStation = new HashMap<>();


        try {
            Document doc = Jsoup.connect("https://skillbox-java.github.io//").get();
            Elements mosMetro = doc.select("div.js-metro-stations, p");

            mosMetro.forEach(st ->
            {
                if (st.attr("data-line").isEmpty()) {
                    lineAndStation.put(String.valueOf(st.childNode(1).firstChild()), st.parentNode().attr("data-line"));
                }
            });
        } catch (Exception ex) {
            ex.getStackTrace();
            System.out.println("Don't reading");
        }
        return lineAndStation;
    }

    private static Map<String, String> createDates(ArrayList<String> filePathDates) {
        Map<String, String> dates = new HashMap<>();
        filePathDates.forEach(pth ->
        {
            if (pth.matches("(.*)json")) {
                dates.putAll(createDateDataJson(pth));
            }
            if (pth.matches("(.*)csv")) {
                dates.putAll(createDateDataCsv(pth));
            }
        });
        return dates;
    }

    private static Map<String, String> createDepths(ArrayList<String> filePathDepths) {
        Map<String, String> depths = new HashMap<>();

        filePathDepths.forEach(pth ->
        {
            if (pth.matches("(.*)json")) {
                depths.putAll(createDepthsDataJson(pth));
            }
            if (pth.matches("(.*)csv")) {
                depths.putAll(createDepthsDataCsv(pth));
            }
        });
        return depths;
    }

    private static Map<String, String> createDepthsDataCsv(String pth) {
        Map<String, String> dateData = new HashMap<>();

        try {
            List<String> info = Files.readAllLines(Paths.get(pth));
            info.forEach(line -> {
                String[] lines = line.split(",", 2);
                if (lines[1].replaceAll("\"", "").matches(".?\\d+.*")) {
                    if (lines.length == 2) {
                        dateData.put(lines[0], lines[1].replaceAll("\"", ""));
                    }
                }
            });
        } catch (Exception ex) {
            System.out.println("Error \n" + ex.getMessage());
        }
        return dateData;
    }

    private static Map<String, String> createDepthsDataJson(String pth) {
        StringBuilder builder = new StringBuilder();
        Map<String, String> depthsData = new HashMap<>();

        try {
            List<String> info = Files.readAllLines(Paths.get(pth));
            info.forEach(builder::append);

            JSONParser parser = new JSONParser();

            JSONArray jsonData = (JSONArray) parser.parse(builder.toString());
            jsonData.forEach(jobj ->
            {
                JSONObject jsonDepthsData = (JSONObject) jobj;
                if (jsonDepthsData.get("station_name") != null) {
                    depthsData.put(String.valueOf(jsonDepthsData.get("station_name")), String.valueOf(jsonDepthsData.get("depth_meters")));
                }
                if (jsonDepthsData.get("name") != null) {
                    depthsData.put(String.valueOf(jsonDepthsData.get("name")), String.valueOf(jsonDepthsData.get("depth")));
                }
            });
        } catch (Exception ex) {
            System.out.println("Error \n" + ex.getMessage());
        }
        return depthsData;
    }

    private static Map<String, String> createDateDataJson(String pth) {
        StringBuilder builder = new StringBuilder();
        Map<String, String> dateData = new HashMap<>();

        try {
            List<String> info = Files.readAllLines(Paths.get(pth));
            info.forEach(builder::append);

            JSONParser parser = new JSONParser();

            JSONArray jsonData = (JSONArray) parser.parse(builder.toString());
            jsonData.forEach(jobj ->
            {
                JSONObject jsonDateData = (JSONObject) jobj;
                dateData.put(String.valueOf(jsonDateData.get("name")), String.valueOf(jsonDateData.get("date")));
            });
        } catch (Exception ex) {
            System.out.println("Error \n" + ex.getMessage());
        }
        return dateData;
    }

    private static Map<String, String> createDateDataCsv(String pth) {
        Map<String, String> dateData = new HashMap<>();

        try {
            List<String> info = Files.readAllLines(Paths.get(pth));
            info.forEach(line -> {
                String[] lines = line.split(",");
                if (lines.length == 2) {
                    if (lines[1].matches("\\d{2}\\.\\d{2}\\.\\d*")) {
                        dateData.put(lines[0], lines[1]);
                    }
                }

            });
        } catch (Exception ex) {
            System.out.println("Error \n" + ex.getMessage());
        }
        return dateData;
    }

    private static void parseFolders(File path) {
        for (File obj : path.listFiles()) {
            if (obj.isDirectory()) {
                parseFolders(obj);
            } else {
                if (obj.getName().matches("(.*)dates(.*)")) {
                    filePathDates.add(obj.getPath());
                }
                if (obj.getName().matches("(.*)depths(.*)")) {
                    filePathDepths.add(obj.getPath());
                }
            }
        }
    }

    private static void writeMetroJson(ArrayList<String> metroSt, ArrayList<String> metroLn) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            PrintWriter writer = new PrintWriter("FilesAndNetwork/DataCollector/data/metro.json");
//            System.out.println(metroSt);
            writer.write("{\n\t\"stations\":");

            writer.write(gson.toJson(JsonParser.parseString(metroSt.toString())));

            writer.write(",\n\t\"lines\":");
            writer.write(gson.toJson(JsonParser.parseString(metroLn.toString())));

            writer.write("\n}");
            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private static void writeStationsJson(ArrayList<String> metro) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try {
            PrintWriter writer = new PrintWriter("FilesAndNetwork/DataCollector/data/stations.json");

            writer.write("{\n\t\"stations\":");
            writer.write(gson.toJson(JsonParser.parseString(metro.toString())));

            writer.write("\n}");
            writer.flush();
            writer.close();

        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    private static void countStation(String path) {
        StringBuilder builder = new StringBuilder();
        Map<String, String> lines = new HashMap<>();
        Map<String, String> stations = new HashMap<>();


        try {
            List<String> info = Files.readAllLines(Paths.get(path));
            info.forEach(builder::append);

            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(builder.toString());

            JSONArray linesArray = (JSONArray) jsonData.get("lines");
            linesArray.forEach(obj -> {
                String[] line = obj.toString().replace("{", "").replace("}", "").split(",");
                String number = line[0].substring(9);
                String name = line[2].substring(8).replaceAll("\"", "");
                lines.put(number, name);
            });

            JSONObject stationsObject = (JSONObject) jsonData.get("stations");
            stationsObject.forEach((k, v) -> {
                String[] vV = v.toString().replace("[", "").replace("]", "").split(",");
                System.out.println("На линии " + lines.get(k) + " " + vV.length + " станций");

            });

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}