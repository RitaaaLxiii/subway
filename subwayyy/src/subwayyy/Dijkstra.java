package subwayyy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Dijkstra {
	private static List<Station> visitedList = new ArrayList<>(); //存储已经过的站点   
    private static HashMap<Station, Routine> resultMap = new HashMap<>();  //存储当前最短路线
	
    //最短路径
	public static Routine shortDistance(Station start,Station end) {
		if(!visitedList.contains(start)) {//当经过的站点没有输入的初始站，将初始站加入
			visitedList.add(start);
		}
		if(resultMap.isEmpty()) {//如果当前最短路线为空
			List<Station> station = getNearStation(start);//查询输入站周围站点
			// 将相邻站点加入结果集
			for (Station station1 : station) { 
				Routine routine = new Routine();
				routine.setStarStation(start);
				routine.setEndStation(end);
				routine.setDistance(1);//默认站点间距离都为1
				routine.getPassedStations().add(station1);
				resultMap.put(station1, routine);
			}
		}
		Station next = getNextStation();//获取下一个站点
		if (next == null) { //当下个站点为空时，初始化
			Routine path = new Routine();
			path.setDistance(0);
			path.setStarStation(start);
			path.setEndStation(end);
			return resultMap.put(end, path);
		}
		if (next.equals(end)) {//如果下一个站点就是终点站，直接输出
			return resultMap.get(next);
		}
		//查询下一站点所有相邻站点
		List<Station> NLinkStation = getNearStation(next);
		for (Station N : NLinkStation) {//遍历下一站点所有相邻站点
			if (visitedList.contains(N)) {//如果经过的站点中包含该站点，继续
				continue;
			}
			int distance = 0;//定义距离为0
			if (next.getName().equals(N.getName())) {//如果下一站点和遍历相同，距离不变
				distance = 0;
			}
			int NDistance = resultMap.get(next).getDistance();
			distance = NDistance + 1;

			List<Station> PrePassStation = resultMap.get(next).getPassedStations();
			Routine NResult = resultMap.get(N);
			if (NResult != null) { // 含有最佳相邻点
				if (NResult.getDistance() > distance) {//如果存储值大于distance，distance替代
					NResult.setDistance(distance);
					NResult.getPassedStations().clear();
					NResult.getPassedStations().addAll(PrePassStation);
					NResult.getPassedStations().add(N);
				}
			} else {
				NResult = new Routine(); // 没有最佳相邻点
				NResult.setDistance(distance);
				NResult.setStarStation(start);
				NResult.setEndStation(N);
				NResult.getPassedStations().addAll(PrePassStation);
				NResult.getPassedStations().add(N);
			}
			resultMap.put(N, NResult);
		}
		visitedList.add(next);
		return shortDistance(start, end);
	}
	
	//获取下个站点
		private static Station getNextStation() {
			Station station = null;
			int min = 10000;
			Set<Station> stations = resultMap.keySet();//stations存resultMap当前
			for(Station station1 : stations) {
				if(visitedList.contains(station1)) {
					continue;
				}
				Routine routine = resultMap.get(station1);
				if(routine.getDistance()<min) {
					min = routine.getDistance();
					station = routine.getEndStation();
				}
			}
			return station;
		}
		
		//获取相邻的所有站点
		public static List<Station> getNearStation(Station station){
			List<Station> nearStation = new ArrayList<Station>();
			for(List<Station> line:ReadSubwayMessage.lines) {//遍历lines
				for(int i = 0;i<line.size();i++) {
					if(station.equals(line.get(i))) {//如果输入的站点和lines中相等
						if(i==0) {//当i==0时，即此站点为起始站，相邻站只有后面一站输入,将后一站放入
							nearStation.add(line.get(i+1));
						}
						else if(i==(line.size())-1) {//如果i==（line.size()）-1,则此站点是末站，相邻站只有前面一站
							nearStation.add(line.get(i - 1)); 
						} else {//位于始末中间的位置，相邻站为前后
							nearStation.add(line.get(i + 1)); 
							nearStation.add(line.get(i - 1));
						}	
					}
				}
			}
			return nearStation;
		}
		
	
}













