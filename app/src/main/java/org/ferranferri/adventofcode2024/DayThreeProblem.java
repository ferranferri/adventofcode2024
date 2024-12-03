package org.ferranferri.adventofcode2024;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DayThreeProblem extends ProblemSolver{

    public DayThreeProblem(String inputFile) {
        super(inputFile);
    }

    public int solveProblemDayThreePartOne() {
        return solveProblemDayThreePartOne(lines);
    }


    private int solveProblemDayThreePartOne(List<String> input) {
        int counter = 0;
        for (String line: input){
            counter += getSum(line);
        }

        return counter;
    }
    public int solveProblemDayThreePartTwo() {
        String singleLine = consolidateLine(lines);
        List<String> cleanLines = cleanString(singleLine);

        return solveProblemDayThreePartOne(cleanLines);
    }

    private List<String> cleanString(String singleLine) {
        // Regular expression to match "do()" or "don't()" and the following text
        String regex = "(do\\(\\)|don't\\(\\))(.*?)((?=do\\(\\)|don't\\(\\))|$)";
        // Compile the regex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(singleLine);
        // Lists to separate do() and don't() parts
        List<String> doParts = new ArrayList<>();


        // Handle initial text before the first match
        int lastMatchEnd = 0;

        // Process matches
        while (matcher.find()) {
            // Initial part until first do or dont is valid
            if (lastMatchEnd == 0 && matcher.start() > 0) {
                doParts.add(singleLine.substring(0, matcher.start()).trim());
            }

            // Extract prefix and content
            String prefix = matcher.group(1);  // "do()" or "don't()"
            String content = matcher.group(2).trim(); // The text after the prefix

            // Assign to respective lists
            if (prefix.equals("do()")) {
                doParts.add(content);
            }

            lastMatchEnd = matcher.end();
        }

        // Add remaining text after the last match (if applicable)
        if (lastMatchEnd < singleLine.length()) {
            doParts.add(singleLine.substring(lastMatchEnd).trim());
        }

        return doParts;
    }

    private String consolidateLine(List<String> lines) {
        // create a single line
        StringBuilder consolidatedLine = new StringBuilder();
        for (String line: lines){
            consolidatedLine.append(line);
        }
        return consolidatedLine.toString();
    }
    private int getSum(String line) {
        // Regular expression to match mul(xxx,yyy) where xxx and yyy are numbers
        String regex = "mul\\((-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)\\)";

        // Compile the regex
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        int count = 0;
        while (matcher.find()) {
            int n = Integer.parseInt(matcher.group(1));
            int m = Integer.parseInt(matcher.group(3));
            count += n * m;

        }
        return count;
    }
}
