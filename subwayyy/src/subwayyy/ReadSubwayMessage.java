package subwayyy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class ReadSubwayMessage {
	public static LinkedHashSet<List<Station>> lines = new LinkedHashSet<List<Station>>();//地铁线路信息
	public  static HashMap<String, List<Station>> lineMap = new HashMap<>();//地铁线路集合
	
	public static String fileIn;//输入路径
	public static String fileOut;//输出路径
	
	//提取subway.txt
	public static void readFile() {   
		File file = new File(fileIn);//创建新file存储输入文件
		BufferedReader reader = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {//依次读取txt每一行
				List<Station> list = new ArrayList<Station>();//创建数组存储线路站点信息
				String[] lineStationList = line.trim().split(" ");//.trim 除去每行左右空格；.split(" ") 当碰到“ ”时，将前面内容存入数组
				String lineStationName = lineStationList[1];//lineStationName等于每行线路名
				for(int i =2;i<lineStationList.length;i++) {
					Station station = new Station(lineStationList[i],lineStationName);//将站点信息封装传递给station
					list.add(station);//list存储当前线路的站点信息
				}
				lines.add(list);//将每行站点信息传给lines
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//获取线路信息
	public static String getLineStation(Station station) {
		lineMap = new HashMap<>();
		for(List<Station> stations : lines) {
			lineMap.put(stations.get(1).getLineName(), stations);//将从txt中提取出来的地铁线路信息输入lineMap
		}
		String name = station.getName();//name获得输入站点名
		for(Map.Entry<String, List<Station>> entry : lineMap.entrySet()) {//lineMap中每个封装对象赋予entry
			List<Station> stations = entry.getValue();//stations 获取每个封装对象的值
			for (Station station1 : stations) {//遍历lineMap
				if (station1.getName().equals(name)) {//如果输入站点与存储站点相同，返回entry值
					return entry.getKey();
				}
			}
		}
		return "";
	}
	
	//获取线路
	public static ArrayList<Station> getLine(String line1, String line2) {
		ArrayList<Station> line = new ArrayList<Station>();//构建一个新数组
		String[] linelist = line1.split(",");//将输入line1存入linelist
		for (String s : linelist) {//遍历
			line.add(new Station(s, line2));
		}
		return line;
	}
	
	//输出线路
	public static String writeLine(String lineName) throws UnsupportedEncodingException, FileNotFoundException { 
		lineMap = new HashMap<>();
		for(List<Station> stations : lines) {
			lineMap.put(stations.get(0).getLineName(), stations);//将从txt中提取出来的地铁线路信息输入lineMap
		}
		int k = Integer.parseInt(lineName.substring(0, 2));//取txt文件头两个字符，即为线路序号
		List<Station> line = lineMap.get(k);//将输入路线名所对应的路线存入line中
		String line1 = line.stream().map(x -> x.getName()).collect(Collectors.joining(","));//提取line中的线路名提取出存入line1
		try {
			Files.write(Paths.get(fileOut), line1.getBytes());//将line1内容写入fileOut
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line1;//返回line1
	}
	
	//读取线路文件
	public void readLine(){
		try {
			FileReader fr = new FileReader("station.txt");
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while ((s=br.readLine()) != null){
				System.out.println(s);
			}
			br.close();
			fr.close();
		} catch (IOException e){
			System.out.println("文件不存在");
		}
	}
	

	//输出最短路线
	public static void writeShortestLine(Routine routine) { 
		FileWriter f = null;
		BufferedWriter b = null;
		try {
			f = new FileWriter(new File(fileOut), true);
			b = new BufferedWriter(f);
			b.write((routine.getPassedStations().size() + 1) + "\t\n");
			b.write(routine.getStarStation().getName() + "\t\n"); 
			String startLineName = getLineStation(routine.getStarStation());
			String line = startLineName; 
			for (Station station : routine.getPassedStations()) {
				if (!line.equals(station.getLineName())) {
					b.write(station.getLineName() + "号线" + "\t\n"); 
					b.write(station.getName() + "\t\n");
					line = station.getLineName();
				}
				else {
					b.write(station.getName() + "\t\n");
				}
			}
			b.close();
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


