package subwayyy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
			 switch(args[0]) {
			 case "-map": //java Main -map subway.txt
				 		  //输出subway.txt内容
				 if(args.length==2) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[1];
					 ReadSubwayMessage.readFile();
					 System.out.println("输入正确");
				 }
				 else {
					 System.out.println("输入错误");
					 break;
				 }
				break;
			 case "-a"://java Main -a 1号线 -map subway.txt -o routine.txt
				 	   //查询地铁线路
				 if(args.length==6) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[3];
					 ReadSubwayMessage.fileOut = System.getProperty("user.dir")+File.separator+"\\"+args[5];
					 ReadSubwayMessage.readFile();
					 ReadSubwayMessage.writeLine(args[1]);
					 ReadSubwayMessage line = new ReadSubwayMessage();
					 line.readLine();
					 
				 }
				 else {
					 System.out.println("输入错误");
					 break;
				 }
				break;
			 case "-b"://java Main -b 万松路 中关村 -map subway.txt -o routine.txt
				 	//查询最短路线
				 if(args.length==7) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[4];
					 ReadSubwayMessage.fileOut = System.getProperty("user.dir")+File.separator+"\\"+args[6];
					 ReadSubwayMessage.readFile();
					 Routine path = Dijkstra.shortDistance(new Station(args[1]), new Station(args[2]));
					 ReadSubwayMessage.writeShortestLine(path);
					 System.out.println("路线：:");
					 Routine line = new Routine();
					 path.readShort();	 
				 }
				 else {
					 System.out.println("输入错误");
					 break;
				 }
				 break;
				 
			default:
				System.out.println("输入错误");
			 }
	}
}