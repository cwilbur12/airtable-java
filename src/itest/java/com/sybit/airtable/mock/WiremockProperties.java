package com.sybit.airtable.mock;

import java.util.Properties;

public class WiremockProperties extends Properties {

    private static final String RECORDING = "recording";
    private static final String CLEAN_DIRECTORIES = "cleanDirectories";
    private static final String PROXY_BASE = "proxyBase";
    private static final String PROXY_PORT = "proxyPort";
    private static final String SERVER_PORT = "serverPort";
    private static final String TARGET_URL = "targetUrl";


    /**
     * @return the recording
     */
    public boolean isRecording() {
        return Boolean.parseBoolean(this.getProperty(RECORDING));
    }

    /**
     * @return the cleanDirectorys
     */
    public boolean isCleanDirectories() {
        return Boolean.parseBoolean(this.getProperty(CLEAN_DIRECTORIES));
    }

    /**
     * @return the targetUrl
     */
    public String getTargetUrl() {
        return this.getProperty(TARGET_URL);
    }

    /**
     * @return the proxyBase
     */
    public String getProxyBase() {
        return this.getProperty(PROXY_BASE);
    }

    /**
     * @return the proxyPort
     */
    public int getProxyPort() {
        return Integer.valueOf(this.getProperty(PROXY_PORT));
    }

    /**
     * @return the serverPort
     */
    public int getServerPort() {
        return Integer.valueOf(this.getProperty(SERVER_PORT));
    }

};
