package eu.peppol.start.model;

/**
 * @author Steinar Overbeck Cook
 *         <p/>
 *         Created by
 *         User: steinar
 *         Date: 04.12.11
 *         Time: 18:46
 */
public class ChannelId {

    String _channelId;

    public ChannelId(String channelId) {
        if (channelId == null) {
            _channelId="";
        } else
            this._channelId = channelId;
    }

    public String getChannelId() {
        return _channelId;
    }
    
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
    	builder.append("ChannelId[\n");
    	builder.append("channelId" + _channelId + "\n");
    	builder.append("]\n");
    	
    	
    	return builder.toString();

    }
}
