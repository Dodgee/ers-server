package uk.ac.aston.jonesja1.ers.model;

import org.springframework.data.annotation.Id;

public class Site {

    @Id
    private String id;

    private String key;

    private String siteName;

    private Location siteLocation;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Location getSiteLocation() {
        return siteLocation;
    }

    public void setSiteLocation(Location siteLocation) {
        this.siteLocation = siteLocation;
    }
}
