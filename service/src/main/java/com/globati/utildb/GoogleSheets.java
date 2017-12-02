package com.globati.utildb;

import com.globati.google_sheets.FlightBookingRow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.services.sheets.v4.Sheets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleSheets {

    private static final Logger log = LogManager.getLogger(GoogleSheets.class);


    /** Application name. */
    private static final String APPLICATION_NAME =
            "Google Sheets API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
            System.getProperty("user.home"), ".credentials/sheets.googleapis.com-java-quickstart");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
            JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/sheets.googleapis.com-java-quickstart
     */
    private static final List<String> SCOPES =
            Arrays.asList(SheetsScopes.SPREADSHEETS_READONLY);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
                ClassLoader.getSystemClassLoader().getResourceAsStream("google_sheets/client_secret.json");
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
        Credential credential = new AuthorizationCodeInstalledApp(
                flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /**
     * Build and return an authorized Sheets API client service.
     * @return an authorized Sheets API client service
     * @throws IOException
     */
    public static Sheets getSheetsService() throws IOException {
        Credential credential = authorize();
        return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static List<FlightBookingRow> getGoogleDocument() throws Exception {
        List<FlightBookingRow> flightBookings = new ArrayList<>();

        try {
            // Build a new authorized API client service.
            Sheets service = getSheetsService();

            // Prints the names and majors of students in a sample spreadsheet:
            // https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit
            String spreadsheetId = "1j1A9tE_okqO2QkJ-ou9yze0M6psM6fRcQJq9GlbuhNw";
            String range = "A2:J1000";
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            if (values == null || values.size() == 0) {
                System.out.println("No data found.");
            } else {
                for (List row : values) {
                    FlightBookingRow flightBookingRow = new FlightBookingRow(
                            row.get(0).toString(),
                            row.get(1).toString(),
                            row.get(2).toString(),
                            row.get(3).toString(),
                            row.get(4).toString(),
                            row.get(5).toString(),
                            row.get(6).toString(),
                            row.get(7).toString(),
                            row.get(8).toString(),
                            row.get(9).toString()
                    );

                    flightBookings.add(flightBookingRow);
                }
            }

        }catch(Exception e){
            log.warn("** There was an exception when retrieving date from the Globati google.network account. **");
            e.printStackTrace();
            String message = "There was an error when running getGoogleDocument(): This method is called automatically from the server to get data from " +
                    "The globati bookings spread sheets on the google drive. It is possible that a google spreadsheet was deleted, or " +
                    "a different server error ocurred. Therefore it cannot be garunteed that booking data has been persisted " +
                    "properly in the database. Here is the error message : "+"<br>"+"<br>"+"<br>"+
                    org.apache.commons.lang.exception.ExceptionUtils.getStackTrace(e.fillInStackTrace());

            SendMail.sendCustomMailToGlobatiStaff("daniel@globati.com", message);
        }
        return flightBookings;

    }

    public static Integer getEmployeeIdFromMarker(String marker){
        return null;

    }


}