/*
 * The MIT License (MIT)
 * Copyright (c) 2017 Sybit GmbH
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 */
package com.sybit.airtable.mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.sybit.airtable.Airtable;
import com.sybit.airtable.exception.AirtableException;
import org.junit.Before;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.recording.SnapshotRecordResult;
import com.sybit.airtable.Base;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.junit.After;

/**
 * Base Class to test using WireMock.
 * <p>
 * Config files for the requests are stored at 'src/test/resources/__files' and
 * 'src/test/resources/mappings'.
 */
public class WireMockBaseTest {

    private static final String LOCALHOST = "127.0.0.1";
    private static final String AIRTABLE_ENDPOINT = "http://localhost:8080";
    private static final String AIRTABLE_BASE = "appLsnybZhB2rXWWB";


    private static final String MAPPING_PATH = "src/test/resources/mappings";
    private static final String FILES_PATH = "src/test/resources/__files";


    private static WireMockServer wireMockServer;
    private static WiremockProperties wiremockProperties;

    protected static Airtable airtable = new Airtable();
    protected static Base base;


    @Before
    public void setUp() throws AirtableException {

        //TODO set up should use props for all configuration
        airtable.configure();
        airtable.setProxy(LOCALHOST);
        airtable.setEndpointUrl(AIRTABLE_ENDPOINT);
        base = airtable.base(AIRTABLE_BASE);

        readWiremockProperties();

        if (isProxySet()) {
            createWiremockServer(wiremockProperties.getServerPort(), wiremockProperties.getProxyBase(), wiremockProperties.getProxyPort());
        } else {
            createWiremockServer(wiremockProperties.getServerPort());
        }

        //start the Wiremock-Server
        startServer();
        //TODO set up should only clean the directory once not for every test run if multiple run

        //check if record
        if (wiremockProperties.isRecording()) {
            //check if cleanDirectorys
            if (wiremockProperties.isCleanDirectorys()) {
                cleanExistingRecords();
                startRecording();
            } else {
                startRecording();
            }
        }
    }

    private boolean isProxySet() {
        return !wiremockProperties.getProxyBase().isEmpty() && wiremockProperties.getProxyPort() != 0;
    }

    private void readWiremockProperties() {
        final String file = "/wiremock.properties";

        InputStream in = null;
        try {
            wiremockProperties = new WiremockProperties();
            in = getClass().getResourceAsStream(file);
            wiremockProperties.load(in);
        } catch (IOException | NullPointerException e) {
            System.err.println("Exception reading Wiremock Properties: " + e);
        } finally {
            org.apache.commons.io.IOUtils.closeQuietly(in);
        }

    }


    @After
    public void tearDown() {

        if (wiremockProperties.isRecording()) {
            stopRecording();
        }

        stopServer();
    }

    public static void startRecording() {

        wireMockServer.startRecording(recordSpec()
                .forTarget(wiremockProperties.getTargetUrl())
                .captureHeader("Accept")
                .captureHeader("Content-Type", true)
                .extractBinaryBodiesOver(0)
                .extractTextBodiesOver(0)
                .makeStubsPersistent(true)
                .transformers("modify-response-header")
                .transformerParameters(Parameters.one("headerValue", "123"))
                .matchRequestBodyWithEqualToJson(false, true));
    }

    public static void stopRecording() {

        SnapshotRecordResult recordedMappings = wireMockServer.stopRecording();
    }

    public static void startServer() {

        wireMockServer.start();
    }

    public static void stopServer() {

        wireMockServer.stop();
    }

    public static void createWiremockServer(int serverPort, String proxyBase, int proxyPort) {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(serverPort).proxyVia(proxyBase, proxyPort));
    }

    public static void createWiremockServer(int serverPort) {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(serverPort));
    }

    public static void cleanExistingRecords() {

        File mappings = new File(MAPPING_PATH);
        File bodyFiles = new File(FILES_PATH);

        try {
            FileUtils.cleanDirectory(mappings);
            FileUtils.cleanDirectory(bodyFiles);
        } catch (IOException ex) {
            System.err.println("Exception deleting Files: " + ex);
        }
    }

}
