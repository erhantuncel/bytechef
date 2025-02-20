/*
 * Copyright 2023-present ByteChef Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.google.sheets.util;

import static com.bytechef.component.definition.ComponentDsl.bool;
import static com.bytechef.component.definition.ComponentDsl.number;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.INCLUDE_ITEMS_FROM_ALL_DRIVES;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.IS_THE_FIRST_ROW_HEADER;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.ROW;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.SHEET_NAME;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.SPREADSHEET_ID;
import static com.bytechef.component.google.sheets.constant.GoogleSheetsConstants.VALUES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.ComponentDsl.ModifiableArrayProperty;
import com.bytechef.component.definition.ComponentDsl.ModifiableObjectProperty;
import com.bytechef.component.definition.Option;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.definition.PropertiesDataSource.ActionPropertiesFunction;
import com.bytechef.component.definition.Property.ValueProperty;
import com.bytechef.google.commons.GoogleServices;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.SheetProperties;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

/**
 * @author Monika Kušter
 */
class GoogleSheetsUtilsTest {

    private final ArgumentCaptor<Boolean> includeItemsFromAllDrivesArgumentCaptor =
        ArgumentCaptor.forClass(Boolean.class);
    private final ArgumentCaptor<Boolean> supportsAllDrivesArgumentCaptor = ArgumentCaptor.forClass(Boolean.class);
    private final ActionContext mockedContext = mock(ActionContext.class);
    private final Drive mockedDrive = mock(Drive.class);
    private final Drive.Files mockedFiles = mock(Drive.Files.class);
    private final Sheets.Spreadsheets.Get mockedGet = mock(Sheets.Spreadsheets.Get.class);
    private final Drive.Files.List mockedList = mock(Drive.Files.List.class);
    private final Parameters mockedParameters = mock(Parameters.class);
    private final Sheets mockedSheets = mock(Sheets.class);
    private final Spreadsheet mockedSpreadsheet = mock(Spreadsheet.class);
    private final Sheets.Spreadsheets mockedSpreadsheets = mock(Sheets.Spreadsheets.class);
    private final Sheets.Spreadsheets.Values mockedValues = mock(Sheets.Spreadsheets.Values.class);
    private final ArgumentCaptor<String> qArgumentCaptor = ArgumentCaptor.forClass(String.class);
    private final ArgumentCaptor<Integer> rowNumberArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
    private final ArgumentCaptor<String> sheetNameArgumentCaptor = ArgumentCaptor.forClass(String.class);
    private final ArgumentCaptor<String> spreadsheetIdArgumentCaptor = ArgumentCaptor.forClass(String.class);
    private final Sheets.Spreadsheets.Values.Append mockedAppend = mock(Sheets.Spreadsheets.Values.Append.class);

    @Test
    void appendValues() throws IOException {
        when(mockedSheets.spreadsheets())
            .thenReturn(mockedSpreadsheets);
        when(mockedSpreadsheets.values())
            .thenReturn(mockedValues);
        when(mockedValues.append(anyString(), anyString(), any(ValueRange.class)))
            .thenReturn(mockedAppend);
        when(mockedAppend.setValueInputOption(anyString()))
            .thenReturn(mockedAppend);

        ValueRange valueRange = new ValueRange();
        GoogleSheetsUtils.appendValues(mockedSheets, "abc", "range", valueRange, "RAW");

        verify(mockedSheets, times(1)).spreadsheets();
        verify(mockedSpreadsheets, times(1)).values();
        verify(mockedValues, times(1)).append("abc", "range", valueRange);
        verify(mockedAppend, times(1)).execute();
    }

    @Test
    void testCreateRangeForRowNumberNotNull() {
        String actualRange = GoogleSheetsUtils.createRange("sheetName", 5);

        assertEquals("sheetName!5:5", actualRange);
    }

    @Test
    void testCreateRangeForRowNumberNull() {
        String actualRange = GoogleSheetsUtils.createRange("sheetName", null);

        assertEquals("sheetName", actualRange);
    }

    @Test
    void testCreatePropertiesForNewRowsForOneRowAndWhenFirstRowIsHeader() throws Exception {
        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(true);
        when(mockedParameters.getRequiredString(SPREADSHEET_ID))
            .thenReturn("spreadsheetId");
        when(mockedParameters.getRequiredString(SHEET_NAME))
            .thenReturn("sheetName");

        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getSheets(mockedParameters))
                .thenReturn(mockedSheets);
            try (MockedStatic<GoogleSheetsRowUtils> googleSheetsRowUtilsMockedStatic = mockStatic(
                GoogleSheetsRowUtils.class)) {

                googleSheetsRowUtilsMockedStatic
                    .when(() -> GoogleSheetsRowUtils.getRowValues(
                        any(Sheets.class), anyString(), anyString(), anyInt()))
                    .thenReturn(List.of("header1", "header2", "header3"));

                ActionPropertiesFunction arrayPropertyForRow =
                    GoogleSheetsUtils.createPropertiesForNewRows(true);

                List<? extends ValueProperty<?>> result = arrayPropertyForRow.apply(
                    mockedParameters, mockedParameters, Map.of(), mockedContext);

                assertEquals(1, result.size());

                ModifiableObjectProperty first = (ModifiableObjectProperty) result.getFirst();

                assertEquals(VALUES, first.getName());
                assertEquals("Values", first.getLabel()
                    .get());

                List<? extends ValueProperty<?>> properties = first.getProperties()
                    .get();

                assertEquals(3, properties.size());

                for (int i = 0; i < properties.size(); i++) {
                    assertEquals("header" + (i + 1), properties.get(i)
                        .getName());
                    assertEquals("header" + (i + 1), properties.get(i)
                        .getLabel()
                        .get());
                    assertEquals("", properties.get(i)
                        .getDefaultValue()
                        .get());
                }
            }
        }
    }

    @Test
    void testCreatePropertiesForNewRowsForMultipleRowsAndWhenFirstRowIsHeader() throws Exception {
        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(true);
        when(mockedParameters.getRequiredString(SPREADSHEET_ID))
            .thenReturn("spreadsheetId");
        when(mockedParameters.getRequiredString(SHEET_NAME))
            .thenReturn("sheetName");

        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getSheets(mockedParameters))
                .thenReturn(mockedSheets);
            try (MockedStatic<GoogleSheetsRowUtils> googleSheetsRowUtilsMockedStatic = mockStatic(
                GoogleSheetsRowUtils.class)) {

                googleSheetsRowUtilsMockedStatic
                    .when(() -> GoogleSheetsRowUtils.getRowValues(
                        any(Sheets.class), anyString(), anyString(), anyInt()))
                    .thenReturn(List.of("header1", "header2", "header3"));

                ActionPropertiesFunction propertiesForNewRows =
                    GoogleSheetsUtils.createPropertiesForNewRows(false);

                List<? extends ValueProperty<?>> result = propertiesForNewRows.apply(
                    mockedParameters, mockedParameters, Map.of(), mockedContext);

                assertEquals(1, result.size());

                ModifiableArrayProperty first = (ModifiableArrayProperty) result.getFirst();

                assertEquals(VALUES, first.getName());
                assertEquals("Rows", first.getLabel()
                    .get());
                assertEquals(true, first.getRequired()
                    .get());

                List<? extends ValueProperty<?>> items = first.getItems()
                    .get();

                assertEquals(1, items.size());

                ModifiableObjectProperty first1 = (ModifiableObjectProperty) items.getFirst();

                List<? extends ValueProperty<?>> valueProperties = first1.getProperties()
                    .get();

                for (int i = 0; i < valueProperties.size(); i++) {
                    assertEquals("header" + (i + 1), valueProperties.get(i)
                        .getName());
                    assertEquals("header" + (i + 1), valueProperties.get(i)
                        .getLabel()
                        .get());
                    assertEquals("", valueProperties.get(i)
                        .getDefaultValue()
                        .get());
                }
            }
        }
    }

    @Test
    void testCreatePropertiesForNewRowsForMultipleRowsAndWhenFirstRowIsNotHeader() throws Exception {
        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(false);
        when(mockedParameters.getRequiredString(SPREADSHEET_ID))
            .thenReturn("spreadsheetId");
        when(mockedParameters.getRequiredString(SHEET_NAME))
            .thenReturn("sheetName");

        ActionPropertiesFunction propertiesForNewRows =
            GoogleSheetsUtils.createPropertiesForNewRows(false);

        List<? extends ValueProperty<?>> result = propertiesForNewRows.apply(
            mockedParameters, mockedParameters, Map.of(), mockedContext);

        assertEquals(1, result.size());

        ModifiableArrayProperty first = (ModifiableArrayProperty) result.getFirst();

        assertEquals(VALUES, first.getName());
        assertEquals("Rows", first.getLabel()
            .get());
        assertEquals(true, first.getRequired()
            .get());

        List<? extends ValueProperty<?>> items = first.getItems()
            .get();

        assertEquals(1, items.size());

        ModifiableArrayProperty first1 = (ModifiableArrayProperty) items.getFirst();

        List<? extends ValueProperty<?>> valueProperties = first1.getItems()
            .get();

        assertEquals(3, valueProperties.size());

        assertEquals(List.of(bool(), number(), string()), valueProperties);
    }

    @Test
    void testCreatePropertiesForNewRowsForOneRowAndWhenFirstRowIsNotHeader() throws Exception {
        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(false);
        when(mockedParameters.getRequiredString(SPREADSHEET_ID))
            .thenReturn("spreadsheetId");
        when(mockedParameters.getRequiredString(SHEET_NAME))
            .thenReturn("sheetName");

        ActionPropertiesFunction propertiesForNewRows =
            GoogleSheetsUtils.createPropertiesForNewRows(true);

        List<? extends ValueProperty<?>> result = propertiesForNewRows.apply(
            mockedParameters, mockedParameters, Map.of(), mockedContext);

        assertEquals(1, result.size());

        ValueProperty<?> array = result.getFirst();

        assertEquals(3, ((ModifiableArrayProperty) array).getItems()
            .get()
            .size());

        assertEquals(VALUES, array.getName());
        assertEquals("Values", array.getLabel()
            .get());
        assertEquals(true, array.getRequired()
            .get());
    }

    @Test
    @SuppressWarnings("unchecked")
    void testGetMapOfValuesForRowWhereFirstRowHeaders() throws IOException {
        List<Object> mockedRow = mock(List.class);
        List<Object> mockedFirstRow = mock(List.class);

        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(true);
        when(mockedParameters.getRequiredString(SPREADSHEET_ID))
            .thenReturn("spreadsheetId");
        when(mockedParameters.getRequiredString(SHEET_NAME))
            .thenReturn("sheetName");

        try (MockedStatic<GoogleSheetsRowUtils> sheetsRowUtilsMockedStatic = mockStatic(GoogleSheetsRowUtils.class)) {
            sheetsRowUtilsMockedStatic
                .when(() -> GoogleSheetsRowUtils.getRowValues(
                    any(Sheets.class), spreadsheetIdArgumentCaptor.capture(), sheetNameArgumentCaptor.capture(),
                    rowNumberArgumentCaptor.capture()))
                .thenReturn(mockedFirstRow);

            when(mockedFirstRow.get(anyInt())).thenReturn("header1", "header2", "header3");
            when(mockedRow.size()).thenReturn(3);
            when(mockedRow.get(anyInt())).thenReturn("value1", "value2", "value3");

            Map<String, Object> result = GoogleSheetsUtils.getMapOfValuesForRow(
                mockedParameters, mockedSheets, mockedRow);

            Map<String, Object> expected = new LinkedHashMap<>();

            expected.put("header1", "value1");
            expected.put("header2", "value2");
            expected.put("header3", "value3");

            assertEquals(expected, result);
            assertEquals("spreadsheetId", spreadsheetIdArgumentCaptor.getValue());
            assertEquals("sheetName", sheetNameArgumentCaptor.getValue());
            assertEquals(1, rowNumberArgumentCaptor.getValue());
        }
    }

    @Test
    @SuppressWarnings("unchecked")
    void testGetMapOfValuesForRowWhereFirstRowNotHeaders() throws IOException {
        List<Object> mockedRow = mock(List.class);

        when(mockedParameters.getRequiredBoolean(IS_THE_FIRST_ROW_HEADER))
            .thenReturn(false);
        when(mockedRow.size()).thenReturn(3);
        when(mockedRow.get(anyInt())).thenReturn("value1", "value2", "value3");

        Map<String, Object> result = GoogleSheetsUtils.getMapOfValuesForRow(mockedParameters, mockedSheets, mockedRow);

        Map<String, Object> expected = new LinkedHashMap<>();

        expected.put("column_A", "value1");
        expected.put("column_B", "value2");
        expected.put("column_C", "value3");

        assertEquals(expected, result);
    }

    @Test
    void testGetRowValuesWhereFirstSpreadsheetRowValuesHeaders() {
        Map<String, Object> rowMap = Map.of(VALUES, Map.of("name", "name", "email", "email"));

        when(mockedParameters.get(ROW))
            .thenReturn(rowMap);

        List<Object> rowValues = GoogleSheetsUtils.getRowValues(mockedParameters);

        Assertions.assertThat(List.<Object>of("name", "email"))
            .hasSameElementsAs(rowValues);
    }

    @Test
    void testGetRowValuesWhereFirstSpreadsheetRowValuesNotHeaders() {
        List<Object> rowList = List.of("name", 1233, false);

        Map<String, Object> rowMap = Map.of(VALUES, rowList);

        when(mockedParameters.get(ROW))
            .thenReturn(rowMap);

        List<Object> rowValues = GoogleSheetsUtils.getRowValues(mockedParameters);

        assertEquals(rowList, rowValues);
    }

    @Test
    void testGetSheetIdOptions() throws Exception {
        List<Sheet> sheetsList = getSheetList();

        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getSheets(mockedParameters))
                .thenReturn(mockedSheets);

            when(mockedSheets.spreadsheets())
                .thenReturn(mockedSpreadsheets);
            when(mockedSpreadsheets.get(spreadsheetIdArgumentCaptor.capture()))
                .thenReturn(mockedGet);
            when(mockedGet.execute())
                .thenReturn(mockedSpreadsheet);
            when(mockedSpreadsheet.getSheets())
                .thenReturn(sheetsList);

            List<Option<String>> sheetIdOptions = GoogleSheetsUtils.getSheetIdOptions(
                mockedParameters, mockedParameters, Map.of(), anyString(), mockedContext);

            assertNotNull(sheetIdOptions);
            assertEquals(2, sheetIdOptions.size());

            Option<String> sheetIdOptionsFirst = sheetIdOptions.getFirst();

            assertEquals("Sheet 1", sheetIdOptionsFirst.getLabel());
            assertEquals("1234567890", sheetIdOptionsFirst.getValue());

            Option<String> option = sheetIdOptions.get(1);

            assertEquals("Sheet 2", option.getLabel());
            assertEquals("98765432", option.getValue());
        }
    }

    @Test
    void testGetSheetNameOptions() throws Exception {
        List<Sheet> sheetsList = getSheetList();

        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getSheets(mockedParameters))
                .thenReturn(mockedSheets);

            when(mockedSheets.spreadsheets())
                .thenReturn(mockedSpreadsheets);
            when(mockedSpreadsheets.get(spreadsheetIdArgumentCaptor.capture()))
                .thenReturn(mockedGet);
            when(mockedGet.execute())
                .thenReturn(mockedSpreadsheet);
            when(mockedSpreadsheet.getSheets())
                .thenReturn(sheetsList);

            List<Option<String>> sheetNameOptions = GoogleSheetsUtils.getSheetNameOptions(
                mockedParameters, mockedParameters, Map.of(), anyString(), mockedContext);

            assertNotNull(sheetNameOptions);
            assertEquals(2, sheetNameOptions.size());

            Option<String> sheetNameOptionsFirst = sheetNameOptions.getFirst();

            assertEquals("Sheet 1", sheetNameOptionsFirst.getLabel());
            assertEquals("Sheet 1", sheetNameOptionsFirst.getValue());

            Option<String> option = sheetNameOptions.get(1);

            assertEquals("Sheet 2", option.getLabel());
            assertEquals("Sheet 2", option.getValue());
        }
    }

    @Test
    void testGetSpreadsheetIdOptions() throws IOException {
        File file1 = new File();

        file1.setName("Spreadsheet 1");
        file1.setId("1234567890");

        File file2 = new File();

        file2.setName("Spreadsheet 2");
        file2.setId("0987654321");

        List<File> files = Arrays.asList(file1, file2);

        try (MockedStatic<GoogleServices> googleServicesMockedStatic = mockStatic(GoogleServices.class)) {
            googleServicesMockedStatic
                .when(() -> GoogleServices.getDrive(mockedParameters))
                .thenReturn(mockedDrive);

            when(mockedParameters.getBoolean(INCLUDE_ITEMS_FROM_ALL_DRIVES))
                .thenReturn(true);
            when(mockedDrive.files())
                .thenReturn(mockedFiles);
            when(mockedFiles.list())
                .thenReturn(mockedList);
            when(mockedList.setQ(qArgumentCaptor.capture()))
                .thenReturn(mockedList);
            when(mockedList.setIncludeItemsFromAllDrives(includeItemsFromAllDrivesArgumentCaptor.capture()))
                .thenReturn(mockedList);
            when(mockedList.setSupportsAllDrives(supportsAllDrivesArgumentCaptor.capture()))
                .thenReturn(mockedList);
            when(mockedList.execute())
                .thenReturn(new FileList().setFiles(files));

            List<Option<String>> spreadsheetIdOptions = GoogleSheetsUtils.getSpreadsheetIdOptions(
                mockedParameters, mockedParameters, Map.of(), anyString(), mockedContext);

            assertNotNull(spreadsheetIdOptions);
            assertEquals(2, spreadsheetIdOptions.size());

            Option<String> spreadsheetIdOptionsFirst = spreadsheetIdOptions.getFirst();

            assertEquals("Spreadsheet 1", spreadsheetIdOptionsFirst.getLabel());
            assertEquals("1234567890", spreadsheetIdOptionsFirst.getValue());

            Option<String> option = spreadsheetIdOptions.get(1);

            assertEquals("Spreadsheet 2", option.getLabel());
            assertEquals("0987654321", option.getValue());
            assertEquals("mimeType='application/vnd.google-apps.spreadsheet'", qArgumentCaptor.getValue());
            assertEquals(true, includeItemsFromAllDrivesArgumentCaptor.getValue());
            assertEquals(true, supportsAllDrivesArgumentCaptor.getValue());
        }
    }

    @Test
    void testColumnToLabel() {
        assertEquals("A", GoogleSheetsUtils.columnToLabel(1));
        assertEquals("Z", GoogleSheetsUtils.columnToLabel(26));
        assertEquals("AA", GoogleSheetsUtils.columnToLabel(27));
        assertEquals("AB", GoogleSheetsUtils.columnToLabel(28));
    }

    private static List<Sheet> getSheetList() {
        Sheet sheet1 = createSheet("Sheet 1", 1234567890);
        Sheet sheet2 = createSheet("Sheet 2", 98765432);

        return Arrays.asList(sheet1, sheet2);
    }

    private static Sheet createSheet(String title, Integer sheetId) {
        Sheet sheet = new Sheet();

        SheetProperties sheetProperties1 = new SheetProperties();

        sheetProperties1.setTitle(title);
        sheetProperties1.setSheetId(sheetId);

        sheet.setProperties(sheetProperties1);

        return sheet;
    }
}
