package uk.ac.aston.jonesja1.ers.model;

public class Site {

    private String siteName;

    private Location siteLocation;

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
