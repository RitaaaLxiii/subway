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
	public static LinkedHashSet<List<Station>> lines = new LinkedHashSet<List<Station>>();//������·��Ϣ
	public  static HashMap<String, List<Station>> lineMap = new HashMap<>();//������·����
	
	public static String fileIn;//����·��
	public static String fileOut;//���·��
	
	//��ȡsubway.txt
	public static void readFile() {   
		File file = new File(fileIn);//������file�洢�����ļ�
		BufferedReader reader = null;
		try {
			InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = reader.readLine()) != null) {//���ζ�ȡtxtÿһ��
				List<Station> list = new ArrayList<Station>();//��������洢��·վ����Ϣ
				String[] lineStationList = line.trim().split(" ");//.trim ��ȥÿ�����ҿո�.split(" ") �������� ��ʱ����ǰ�����ݴ�������
				String lineStationName = lineStationList[1];//lineStationName����ÿ����·��
				for(int i =2;i<lineStationList.length;i++) {
					Station station = new Station(lineStationList[i],lineStationName);//��վ����Ϣ��װ���ݸ�station
					list.add(station);//list�洢��ǰ��·��վ����Ϣ
				}
				lines.add(list);//��ÿ��վ����Ϣ����lines
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//��ȡ��·��Ϣ
	public static String getLineStation(Station station) {
		lineMap = new HashMap<>();
		for(List<Station> stations : lines) {
			lineMap.put(stations.get(1).getLineName(), stations);//����txt����ȡ�����ĵ�����·��Ϣ����lineMap
		}
		String name = station.getName();//name�������վ����
		for(Map.Entry<String, List<Station>> entry : lineMap.entrySet()) {//lineMap��ÿ����װ������entry
			List<Station> stations = entry.getValue();//stations ��ȡÿ����װ�����ֵ
			for (Station station1 : stations) {//����lineMap
				if (station1.getName().equals(name)) {//�������վ����洢վ����ͬ������entryֵ
					return entry.getKey();
				}
			}
		}
		return "";
	}
	
	//��ȡ��·
	public static ArrayList<Station> getLine(String line1, String line2) {
		ArrayList<Station> line = new ArrayList<Station>();//����һ��������
		String[] linelist = line1.split(",");//������line1����linelist
		for (String s : linelist) {//����
			line.add(new Station(s, line2));
		}
		return line;
	}
	
	//�����·
	public static String writeLine(String lineName) throws UnsupportedEncodingException, FileNotFoundException { 
		lineMap = new HashMap<>();
		for(List<Station> stations : lines) {
			lineMap.put(stations.get(0).getLineName(), stations);//����txt����ȡ�����ĵ�����·��Ϣ����lineMap
		}
		int k = Integer.parseInt(lineName.substring(0, 2));//ȡtxt�ļ�ͷ�����ַ�����Ϊ��·���
		List<Station> line = lineMap.get(k);//������·��������Ӧ��·�ߴ���line��
		String line1 = line.stream().map(x -> x.getName()).collect(Collectors.joining(","));//��ȡline�е���·����ȡ������line1
		try {
			Files.write(Paths.get(fileOut), line1.getBytes());//��line1����д��fileOut
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line1;//����line1
	}
	
	//��ȡ��·�ļ�
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
			System.out.println("�ļ�������");
		}
	}
	

	//������·��
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
					b.write(station.getLineName() + "����" + "\t\n"); 
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


