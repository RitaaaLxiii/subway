package subwayyy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Dijkstra {
	private static List<Station> visitedList = new ArrayList<>(); //�洢�Ѿ�����վ��   
    private static HashMap<Station, Routine> resultMap = new HashMap<>();  //�洢��ǰ���·��
	
    //���·��
	public static Routine shortDistance(Station start,Station end) {
		if(!visitedList.contains(start)) {//��������վ��û������ĳ�ʼվ������ʼվ����
			visitedList.add(start);
		}
		if(resultMap.isEmpty()) {//�����ǰ���·��Ϊ��
			List<Station> station = getNearStation(start);//��ѯ����վ��Χվ��
			// ������վ���������
			for (Station station1 : station) { 
				Routine routine = new Routine();
				routine.setStarStation(start);
				routine.setEndStation(end);
				routine.setDistance(1);//Ĭ��վ�����붼Ϊ1
				routine.getPassedStations().add(station1);
				resultMap.put(station1, routine);
			}
		}
		Station next = getNextStation();//��ȡ��һ��վ��
		if (next == null) { //���¸�վ��Ϊ��ʱ����ʼ��
			Routine path = new Routine();
			path.setDistance(0);
			path.setStarStation(start);
			path.setEndStation(end);
			return resultMap.put(end, path);
		}
		if (next.equals(end)) {//�����һ��վ������յ�վ��ֱ�����
			return resultMap.get(next);
		}
		//��ѯ��һվ����������վ��
		List<Station> NLinkStation = getNearStation(next);
		for (Station N : NLinkStation) {//������һվ����������վ��
			if (visitedList.contains(N)) {//���������վ���а�����վ�㣬����
				continue;
			}
			int distance = 0;//�������Ϊ0
			if (next.getName().equals(N.getName())) {//�����һվ��ͱ�����ͬ�����벻��
				distance = 0;
			}
			int NDistance = resultMap.get(next).getDistance();
			distance = NDistance + 1;

			List<Station> PrePassStation = resultMap.get(next).getPassedStations();
			Routine NResult = resultMap.get(N);
			if (NResult != null) { // ����������ڵ�
				if (NResult.getDistance() > distance) {//����洢ֵ����distance��distance���
					NResult.setDistance(distance);
					NResult.getPassedStations().clear();
					NResult.getPassedStations().addAll(PrePassStation);
					NResult.getPassedStations().add(N);
				}
			} else {
				NResult = new Routine(); // û��������ڵ�
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
	
	//��ȡ�¸�վ��
		private static Station getNextStation() {
			Station station = null;
			int min = 10000;
			Set<Station> stations = resultMap.keySet();//stations��resultMap��ǰ
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
		
		//��ȡ���ڵ�����վ��
		public static List<Station> getNearStation(Station station){
			List<Station> nearStation = new ArrayList<Station>();
			for(List<Station> line:ReadSubwayMessage.lines) {//����lines
				for(int i = 0;i<line.size();i++) {
					if(station.equals(line.get(i))) {//��������վ���lines�����
						if(i==0) {//��i==0ʱ������վ��Ϊ��ʼվ������վֻ�к���һվ����,����һվ����
							nearStation.add(line.get(i+1));
						}
						else if(i==(line.size())-1) {//���i==��line.size()��-1,���վ����ĩվ������վֻ��ǰ��һվ
							nearStation.add(line.get(i - 1)); 
						} else {//λ��ʼĩ�м��λ�ã�����վΪǰ��
							nearStation.add(line.get(i + 1)); 
							nearStation.add(line.get(i - 1));
						}	
					}
				}
			}
			return nearStation;
		}
		
	
}













