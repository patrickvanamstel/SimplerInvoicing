package nl.kaninefatendreef.si.document;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3._2009._02.ws_tra.Resource;



public class PortCloseThread extends Thread {

	Logger _logger = LoggerFactory.getLogger(PortCloseThread.class);
	
	Resource _resource = null;

	public PortCloseThread(Resource resource) {
		_resource = resource;
	}

	public void run() {
		if (_resource == null) {
			return;
		}
		try{
			((com.sun.xml.ws.Closeable) _resource).close();
		}catch (Throwable t){
			_logger.error("While closing resource" , t);
		}
	}

}
