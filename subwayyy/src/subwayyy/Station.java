package subwayyy;

import java.util.ArrayList;
import java.util.List;


public class Station {
	private String name; //站点名
    private String lineName;//站点路线名
    private List<Station> nearStation = new ArrayList<Station>(); //邻接站点
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Station> getNearStation() {
		return nearStation;
	}
	public void setNearStation(List<Station> nearStation) {
		this.nearStation = nearStation;
	}
	
	public Station(String name, String lineName) {
        this.name = name;
        this.lineName = lineName;
    }
	public Station(String name) {
        this.name = name;
    }
    public Station (){
    }
	public String getLineName() {
		return lineName;
	}
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}
	public boolean equals(Object obj) { 
        if (this == obj) {
            return true;
        } else if (obj instanceof Station) {
            Station station = (Station) obj;
            if (station.getName().equals(this.getName())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
	
}
