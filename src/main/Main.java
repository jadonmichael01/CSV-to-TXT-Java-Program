// NAME: JADONMICHAEL DE JESUS
package main;

import java.util.*;
import java.io.*;

public class Main {

    private static final String INPUT = System.getProperty("user.dir") + "/input/";
    private static final String OUTPUT = System.getProperty("user.dir") + "/output/";

    public static void main(String[] args) {
        boolean run = true;
        while (run) {
            Scanner keyboard = new Scanner(System.in);
            String command = "";
            command = keyboard.next();
            switch (command) {
                case "convert":
                    String source = keyboard.next();
                    String destination = keyboard.next();
                    try {
                        converter(INPUT + source, OUTPUT + destination);
                    } catch (Exception e) {
                        System.out.println("Error: " + e);

                    }
                    break;
                case "normalize":
                    String sourceToBeNormalized = keyboard.next();
                    try {
                        normalize(INPUT + sourceToBeNormalized);
                    } catch (Exception e1) {
                        try {
                            normalize(OUTPUT + sourceToBeNormalized);
                        } catch (Exception e2){
                            System.out.println("Error:" + e2);
                        }
                    }
                    break;
                case "quit":
                    run = false;
                    break;
                default:
                    System.out.println("Invalid command! try again!");
            }

        }
    }

    public static void converter(String source, String destination) throws Exception {
        String srcExt = source.substring(source.length() - 3);
        String desExt = destination.substring(destination.length() - 3);
        if (!srcExt.equals("txt") && !srcExt.equals("csv"))
            throw new Exception("bad format");
        else if (!desExt.equals("txt") && !desExt.equals("csv"))
            throw new Exception("bad format");
        else if (source.equals(destination))
            throw new Exception("Same input and output!");
        Scanner in = new Scanner(new File(source));
        PrintWriter out = new PrintWriter(destination);
        if (srcExt.equals(desExt)) { // same format
            if (srcExt.equals("txt")) { // txt - txt
                while (in.hasNextLine()) {
                    Scanner lineScanner = new Scanner(in.nextLine());
                    lineScanner.useDelimiter("\t");
                    while (lineScanner.hasNext())
                        out.print(lineScanner.next() + (lineScanner.hasNext() ? "\t" : in.hasNextLine() ? "\n" : ""));
                    out.flush();
                }

            } else { // csv - csv
                while (in.hasNextLine()) {
                    Scanner lineScanner = new Scanner(in.nextLine());
                    lineScanner.useDelimiter(",");
                    while (lineScanner.hasNext())
                        out.print(lineScanner.next() + (lineScanner.hasNext() ? "," : in.hasNextLine() ? "\n" : ""));
                    out.flush();

                }

            }
        } else {
            if (srcExt.equals("txt")) { //txt to csv
                while (in.hasNextLine()) {
                    Scanner lineScanner = new Scanner(in.nextLine());
                    lineScanner.useDelimiter("\t");
                    while (lineScanner.hasNext())
                        out.print(lineScanner.next() + (lineScanner.hasNext() ? "," : in.hasNextLine() ? "\n" : ""));
                    out.flush();
                }


            } else { // csv to txt
                while (in.hasNextLine()) {
                    Scanner lineScanner = new Scanner(in.nextLine());
                    lineScanner.useDelimiter(",");
                    while (lineScanner.hasNext())
                        out.print(lineScanner.next() + (lineScanner.hasNext() ? "\t" : in.hasNextLine() ? "\n" : ""));
                    out.flush();
                }

            }
        }

    }

    public static void normalize(String fileName) throws Exception {
        String fileExt = fileName.substring(fileName.length() - 3);
        ArrayList<String> content = new ArrayList<>();
        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextLine())
            content.add(in.nextLine());
        in.close();
        PrintWriter out = new PrintWriter(fileName);
        if (fileExt.equals("txt")) {
            for (String line : content) {
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\t");
                int valueInt;
                double valueDouble;
                String valueString;
                while (lineScanner.hasNext()) {
                    valueString = lineScanner.next();
                    if (valueString.length() == 0) {
                        out.print("N/A\t");
                        continue;
                    }
                    try {
                        valueInt = Integer.parseInt(valueString);
                        if (valueInt > 0) {
                            out.printf("%+010d", valueInt);
                        } else {
                            out.printf("%010d", valueInt);
                        }
                    } catch (NumberFormatException e) {
                        try {
                            valueDouble = Double.parseDouble(valueString);
                            // do if statement
                            if (valueDouble > 100.0 || valueDouble < 0.01)
                                out.printf("%e.2", valueDouble);
                            else
                                out.printf("%.2f", valueDouble);
                        } catch (NumberFormatException e2) {
                            if (valueString.length() > 13)
                                out.printf("%.10s...", valueString);
                            else
                                out.printf("%.10s", valueString);
                        }
                    } finally {
                        if(lineScanner.hasNext())
                            out.print("\t");
                    }
                }
                out.flush();
                out.println();
            }
    } else {
            for (String line : content) {
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(",");
                int valueInt;
                double valueDouble;
                String valueString;
                while (lineScanner.hasNext()) {
                    valueString = lineScanner.next();
                    if (valueString.length() == 0) {
                        out.print("N/A,");
                        continue;
                    }
                    try {
                        valueInt = Integer.parseInt(valueString);
                        if (valueInt > 0) {
                            out.printf("%+010d", valueInt);
                        } else {
                            out.printf("%010d", valueInt);
                        }
                    } catch (NumberFormatException e) {
                        try {
                            valueDouble = Double.parseDouble(valueString);
                            // do if statement
                            if (valueDouble > 100.0 || valueDouble < 0.01)
                                out.printf("%e.2", valueDouble);
                            else
                                out.printf("%.2f", valueDouble);
                        } catch (NumberFormatException e2) {
                            if (valueString.length() > 13)
                                out.printf("%.10s...", valueString);
                            else
                                out.printf("%.10s", valueString);
                        }
                    } finally{
                        if(lineScanner.hasNext())
                            out.print(",");
                    }
                }
                out.flush();
                out.println();
            }
        }
        out.close();
    }
}




