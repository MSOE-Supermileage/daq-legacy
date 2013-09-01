import java.io.File;
import java.util.LinkedList;
import java.util.List;

import edu.smv.data.DataNode;
import edu.smv.fileIO.FileIO;


public class Log_Generator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<DataNode> nodes = new LinkedList<DataNode>();
		double speed = 0;
		double rpm = 0;
		double batteryVoltage = 0;
		double latitude = 0;
		double longitude = 0;
		double altitude = 0;
		
		 for (int i = 0; i < 2000; i++){
			speed = (speed <= 120 ? speed + 1 : 0);
			rpm = (rpm <= 10000 ? rpm +100 : 0);;
			batteryVoltage = (batteryVoltage <= 12 ? batteryVoltage + 1 : 0);;
			
			nodes.add( new DataNode(speed/DataNode.MILES_IN_A_METER,  rpm,  batteryVoltage,  latitude,  longitude,  altitude));
		 }

		FileIO.saveDataNodes(new File("TestNodes." + DataNode.FILE_TYPE), nodes);
	}

}
