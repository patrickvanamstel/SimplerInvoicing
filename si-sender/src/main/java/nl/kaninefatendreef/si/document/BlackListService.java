package nl.kaninefatendreef.si.document;

import java.net.URL;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BlackListService {

	Logger _logger = LoggerFactory.getLogger(BlackListService.class);
	
	private boolean _add2ApBlackListOnTimeout = true;
	private Integer _apBlackListEntryKeepTime = 1000 * 60 * 5; // 5 minutes by default
	
	private Map<URL, Long> _apBlackList = Collections.synchronizedMap(new LinkedHashMap<URL, Long>());


    public  void add2ApBlackList(URL destination, Integer apBlackListEntryKeepTime) {
    	
    	_logger.info("Adding " +  destination + " for " + apBlackListEntryKeepTime + " ms ");
    	
        if (apBlackListEntryKeepTime == null || apBlackListEntryKeepTime == 0) {
            getApBlackList().put(destination, new Long(0));
        } else {
            getApBlackList().put(destination, System.currentTimeMillis() + apBlackListEntryKeepTime);
        }
    }

    public boolean existInApBlackList(URL destination) {
        if (!getApBlackList().containsKey(destination)) {
            return false;
        }
        Long keepTime = getApBlackList().get(destination);
        if (keepTime == null || keepTime == 0) {
            return true;
        } else if (System.currentTimeMillis() < keepTime) {
            return true;
        }

        _logger.info("Removing " + destination + " from blacklist.");
        getApBlackList().remove(destination);

        return false;
    }

    
    // Getters and Setters
    
    public boolean isAdd2ApBlackListOnTimeout() {
        return _add2ApBlackListOnTimeout;
    }

    public  void setAdd2ApBlackListOnTimeout(boolean add2ApBlackListOnTimeout) {
        _add2ApBlackListOnTimeout = add2ApBlackListOnTimeout;
    }

    public Integer getApBlackListEntryKeepTime() {
        return _apBlackListEntryKeepTime;
    }

    public void setApBlackListEntryKeepTime(Integer apBlackListEntryKeepTime) {
        _apBlackListEntryKeepTime = apBlackListEntryKeepTime;
    }

    private Map<URL, Long> getApBlackList() {
        return _apBlackList;
    }

	
}
