package com.sybit.airtable.mock;

import java.util.Properties;

public class WiremockProperties extends Properties {

    private boolean recording;

    private boolean cleanDirectorys;

    private String targetUrl;

    private String proxyBase;

    private int proxyPort;

    private int serverPort;

    /**
     * @return the recording
     */
    public boolean isRecording() {
        return recording;
    }

    /**
     * @param aRecording the recording to set
     */
    public void setRecording(boolean aRecording) {
        recording = aRecording;
    }

    /**
     * @return the cleanDirectorys
     */
    public boolean isCleanDirectorys() {
        return cleanDirectorys;
    }

    /**
     * @param aCleanDirectorys the cleanDirectorys to set
     */
    public void setCleanDirectorys(boolean aCleanDirectorys) {
        cleanDirectorys = aCleanDirectorys;
    }

    /**
     * @return the targetUrl
     */
    public String getTargetUrl() {
        return targetUrl;
    }

    /**
     * @param aTargetUrl the targetUrl to set
     */
    public void setTargetUrl(String aTargetUrl) {
        targetUrl = aTargetUrl;
    }

    /**
     * @return the proxyBase
     */
    public String getProxyBase() {
        return proxyBase;
    }

    /**
     * @param aProxyBase the proxyBase to set
     */
    public void setProxyBase(String aProxyBase) {
        proxyBase = aProxyBase;
    }

    /**
     * @return the proxyPort
     */
    public int getProxyPort() {
        return proxyPort;
    }

    /**
     * @param aProxyPort the proxyPort to set
     */
    public void setProxyPort(int aProxyPort) {
        proxyPort = aProxyPort;
    }

    /**
     * @return the serverPort
     */
    public int getServerPort() {
        return serverPort;
    }

    /**
     * @param aServerPort the serverPort to set
     */
    public void setServerPort(int aServerPort) {
        serverPort = aServerPort;
    }
};
