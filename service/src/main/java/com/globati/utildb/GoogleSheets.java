package com.globati.utildb;

import com.globati.dbmodel.FlightBooking;
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


    private static final String UNPERSISTED_FLIGHT_SHEET = "1j1A9tE_okqO2QkJ-ou9yze0M6psM6fRcQJq9GlbuhNw";
    private static final String PERSISTED_FLIGHT_SHEET = "1o3xElRyP_w-s0EwIYg_qDK3yf7uCRagm_kbNBJss27I";
    private static final String RANGE = "A2:J1000";

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
            Arrays.asList(SheetsScopes.SPREADSHEETS);

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


            ValueRange response = service.spreadsheets().values()
                    .get(UNPERSISTED_FLIGHT_SHEET, RANGE)
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

        boolean successfulWrite = writeToPersistedGoogleDoc(flightBookings);

        if(successfulWrite){
            emptyUnpersistedDoc();
        }
        return flightBookings;

    }

    /**
     * Writes the the data to the documnt for data which HAS been persisted.
     * @param rows
     * @return
     * @throws IOException
     */
    public static boolean writeToPersistedGoogleDoc(List<FlightBookingRow> rows) throws IOException {

        String valueInputOption = "USER_ENTERED";
        String insertDataOption = "INSERT_ROWS";

        List<List<Object>> values = new ArrayList<>();

        for(FlightBookingRow row: rows){
            List<Object> list = new ArrayList<>();
            list.add(row.getTimeBooked());
            list.add(row.getPaidStatus());
            list.add(row.getCostOfTicket());
            list.add(row.getGlobatiCommission());
            list.add(row.getFlightPlan());
            list.add(row.getNumberOfPeople().toString());
            list.add(row.getDepartureDate().toString());
            list.add(row.getReturnDate().toString());
            list.add(row.getGlobatiMarker());
            list.add(row.getCompanyBookedWith());
            values.add(list);
        }


        // How the input data should be inserted.
        ValueRange requestBody = new ValueRange();
        requestBody.setValues(values);

        Sheets sheetsService = getSheetsService();
        Sheets.Spreadsheets.Values.Append request =
                sheetsService.spreadsheets().values().append(PERSISTED_FLIGHT_SHEET, RANGE, requestBody);
        request.setValueInputOption(valueInputOption);
        request.setInsertDataOption(insertDataOption);

        AppendValuesResponse response = request.execute();
        System.out.println(response);

        return true;
    }

    /**
     * Clears the document for data that HAS NOT been persisted yet
     * @return
     * @throws IOException
     */
    public static boolean emptyUnpersistedDoc() throws IOException {
        Sheets sheetsService = getSheetsService();
        ClearValuesResponse request =
                sheetsService.spreadsheets().values().clear(UNPERSISTED_FLIGHT_SHEET, RANGE, new ClearValuesRequest()).execute();

        return true;
    }



}