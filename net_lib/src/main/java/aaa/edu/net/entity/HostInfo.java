
package aaa.edu.net.entity;

import java.util.ArrayList;
import java.util.List;

public class HostInfo {

    private String host;
    private List<URLInfo> urls = new ArrayList<URLInfo>();

    /**
     * 
     * @return
     *     The host
     */
    public String getHost() {
        return host;
    }

    /**
     * 
     * @param host
     *     The host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public List<URLInfo> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<URLInfo> urls) {
        this.urls = urls;
    }

}
