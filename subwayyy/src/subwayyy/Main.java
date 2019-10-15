package subwayyy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
			 switch(args[0]) {
			 case "-map": //java Main -map subway.txt
				 		  //���subway.txt����
				 if(args.length==2) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[1];
					 ReadSubwayMessage.readFile();
					 System.out.println("������ȷ");
				 }
				 else {
					 System.out.println("�������");
					 break;
				 }
				break;
			 case "-a"://java Main -a 1���� -map subway.txt -o routine.txt
				 	   //��ѯ������·
				 if(args.length==6) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[3];
					 ReadSubwayMessage.fileOut = System.getProperty("user.dir")+File.separator+"\\"+args[5];
					 ReadSubwayMessage.readFile();
					 ReadSubwayMessage.writeLine(args[1]);
					 ReadSubwayMessage line = new ReadSubwayMessage();
					 line.readLine();
					 
				 }
				 else {
					 System.out.println("�������");
					 break;
				 }
				break;
			 case "-b"://java Main -b ����· �йش� -map subway.txt -o routine.txt
				 	//��ѯ���·��
				 if(args.length==7) {
					 ReadSubwayMessage.fileIn = System.getProperty("user.dir")+File.separator+"\\"+args[4];
					 ReadSubwayMessage.fileOut = System.getProperty("user.dir")+File.separator+"\\"+args[6];
					 ReadSubwayMessage.readFile();
					 Routine path = Dijkstra.shortDistance(new Station(args[1]), new Station(args[2]));
					 ReadSubwayMessage.writeShortestLine(path);
					 System.out.println("·�ߣ�:");
					 Routine line = new Routine();
					 path.readShort();	 
				 }
				 else {
					 System.out.println("�������");
					 break;
				 }
				 break;
				 
			default:
				System.out.println("�������");
			 }
	}
}