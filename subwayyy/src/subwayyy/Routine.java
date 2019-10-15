package subwayyy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import subwayyy.Station;

public class Routine {
	private Station starStation;//初始站
	private Station endStation;//目的站
	private int distance;//距离
	private Station preStations;  //前一个站点
    
    private List<Station> passedStations=new ArrayList<>();
    
	public Station getStarStation() {
		return starStation;
	}
	public void setStarStation(Station starStation) {
		this.starStation = starStation;
	}
	public Station getEndStation() {
		return endStation;
	}
	public void setEndStation(Station endStation) {
		this.endStation = endStation;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Station getPreStations() {
		return preStations;
	}
	public void setPreStations(Station preStations) {
		this.preStations = preStations;
	}
	
	public Routine(Station starStation, Station endStation, int distance) {
        this.starStation = starStation;
        this.endStation = endStation;
        this.distance = distance;
    }
	public Routine() {
		
	}
	public List<Station> getPassedStations() {
		return passedStations;
	}
	public void setPassedStations(List<Station> passedStations) {
		this.passedStations = passedStations;
	}
	//读取最短路径文件
	public void readShort()  {
		try {
			FileReader fr = new FileReader("routine.txt");
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while ((s = br.readLine()) != null){
				System.out.println(s);// 输出一行
			}
			br.close();
			fr.close(); 
		} catch (IOException e){
			System.out.println("错误");
		}
	}
	
	
}

