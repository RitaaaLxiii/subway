package subwayyy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import subwayyy.Station;

public class Routine {
	private Station starStation;//��ʼվ
	private Station endStation;//Ŀ��վ
	private int distance;//����
	private Station preStations;  //ǰһ��վ��
    
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
	//��ȡ���·���ļ�
	public void readShort()  {
		try {
			FileReader fr = new FileReader("routine.txt");
			BufferedReader br = new BufferedReader(fr);
			String s = null;
			while ((s = br.readLine()) != null){
				System.out.println(s);// ���һ��
			}
			br.close();
			fr.close(); 
		} catch (IOException e){
			System.out.println("����");
		}
	}
	
	
}

