package com.example.financialapplication.ReadingCSV;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class CSVReader {

    InputStream inputStream;

    public CSVReader(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public List readData() {
        List resultList = new ArrayList();
        {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] row = line.split(",");
                    resultList.add(row);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return resultList;
    }


//
//    try {
//
//        br = new BufferedReader(new FileReader(csvFile));
//        while ((line = br.readLine()) != null) {
//
//            // use comma as separator
//            String[] country = line.split(cvsSplitBy);
//
//            System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");
//
//        }
//
//    } catch (
//    FileNotFoundException e) {
//        e.printStackTrace();
//    } catch (
//    IOException e) {
//        e.printStackTrace();
//    } finally {
//        if (br != null) {
//            try {
//                br.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
