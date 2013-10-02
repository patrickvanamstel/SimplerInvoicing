package nl.kaninefatendreef.si.document;

import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.stereotype.Component;

@Component
public class BlackListService {

	private boolean add2ApBlackListOnTimeout = true;
	private Integer apBlackListEntryKeepTime = 1000 * 60 * 120;
	
	private Map<URL, Long> apBlackList = Collections.synchronizedMap(new LinkedHashMap<URL, Long>());

    public Map<URL, Long> getApBlackList() {
        return apBlackList;
    }

    public  void add2ApBlackList(URL destination, Integer apBlackListEntryKeepTime) {
        if (apBlackListEntryKeepTime == null || apBlackListEntryKeepTime == 0) {
            getApBlackList().put(destination, new Long(0));
        } else {
            getApBlackList().put(destination, System.currentTimeMillis() + apBlackListEntryKeepTime);
        }
    }

    public void removeFromApBlackList(URL destination) {
        getApBlackList().remove(destination);
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

        getApBlackList().remove(destination);
        return false;
    }

    public void clearApBlackList() {
        getApBlackList().clear();
    }

  	public void setApBlackListFromString(Set<String> apBlackList)
            throws Exception {
        synchronized (apBlackList) {
            clearApBlackList();
            for (Iterator<String> iter = apBlackList.iterator(); iter.hasNext(); ) {
                String destination = iter.next();
                if (destination != null && destination.trim().length() > 0) {
                    add2ApBlackList(new URL(destination), 0);
                }
            }
        }
    }

    public Map<String, Date> getApBlackListAsString() {
        Map<String, Date> map = new LinkedHashMap<String, Date>();
        synchronized (apBlackList) {
            for (Iterator<Entry<URL, Long>> iter = apBlackList.entrySet().iterator(); iter.hasNext(); ) {
                Entry<URL, Long> entry = iter.next();
                Date value = null;
                if (entry.getValue() != null && entry.getValue() > 0) {
                    value = new Date(entry.getValue());
                }
                map.put(entry.getKey().toExternalForm(), value);
            }
        }

        return map;
    }
    public boolean isAdd2ApBlackListOnTimeout() {
        return add2ApBlackListOnTimeout;
    }

    public  void setAdd2ApBlackListOnTimeout(boolean add2ApBlackListOnTimeout) {
        this.add2ApBlackListOnTimeout = add2ApBlackListOnTimeout;
    }

    public Integer getApBlackListEntryKeepTime() {
        return apBlackListEntryKeepTime;
    }

    public void setApBlackListEntryKeepTime(Integer apBlackListEntryKeepTime) {
        this.apBlackListEntryKeepTime = apBlackListEntryKeepTime;
    }

	
}
