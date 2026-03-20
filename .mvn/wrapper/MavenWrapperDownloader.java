import java.net.*;
import java.io.*;
import java.util.Properties;

public class MavenWrapperDownloader {

    private static final String WRAPPER_VERSION = "3.2.0";

    /**
     * Default main method for the MavenWrapperDownloader class. This method is the entry point when the class
     * is run as a standalone application.
     *
     * @param args command line arguments
     * @throws Exception if any error occurs during the execution of the function
     */
    public static void main(String args[]) throws Exception {
        System.out.println("- Downloader started");
        File baseDir = new File(args[0]);
        File mavenWrapperJar = new File(baseDir, "maven-wrapper.jar");

        if (mavenWrapperJar.exists()) {
            System.out.println("- Wrapper already downloaded");
            return;
        }

        File wrapperDir = baseDir.getParentFile();
        File wrapperPropertiesFile = new File(wrapperDir, "maven-wrapper.properties");

        String distributionUrl = getDistributionUrl(wrapperPropertiesFile);

        downloadFileFromURL(distributionUrl, mavenWrapperJar);

        System.out.println("- Done");
    }

    private static String getDistributionUrl(File wrapperPropertiesFile) throws IOException {
        Properties wrapperProperties = new Properties();
        try (FileInputStream fis = new FileInputStream(wrapperPropertiesFile)) {
            wrapperProperties.load(fis);
        }
        String distributionUrl = wrapperProperties.getProperty("distributionUrl");
        if (distributionUrl == null) {
            throw new IllegalStateException("distributionUrl property not defined in " + wrapperPropertiesFile.getAbsolutePath());
        }
        return distributionUrl;
    }

    private static void downloadFileFromURL(String distributionUrl, File destination) throws MalformedURLException, IOException {
        System.out.println("- Downloading from: " + distributionUrl);
        URL url = new URL(distributionUrl);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Maven Wrapper Download");
        try (InputStream in = conn.getInputStream();
             FileOutputStream out = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int n = 0;
            while ((n = in.read(buffer)) != -1) {
                out.write(buffer, 0, n);
            }
        }
        System.out.println("- Downloaded to: " + destination.getAbsolutePath());
    }

}