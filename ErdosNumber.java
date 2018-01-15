/**
 * ErdosNumber
 * 
 * Input
 * The first line of the input contains the number of scenarios.
 * The input for each scenario consists of a paper database and a list of names. It begins with the line
 * P N
 * where P and N are natural numbers. Following this line are P lines containing descriptions of papers
 * (this is the paper database). A paper appears on a line by itself and is specified in the following way:
 * Smith, M.N., Martin, G., Erdos, P.: Newtonian forms of prime factors matrices
 * Note that umlauts like ‘¨o’ are simply written as ‘o’. After the P papers follow N lines with names.
 * Such a name line has the following format:
 * Martin, G.
 * Output
 * For every scenario you are to print a line containing a string “Scenario i” (where i is the number
 * of the scenario) and the author names together with their Erd¨os number of all authors in the list of
 * names. The authors should appear in the same order as they appear in the list of names. The Erd¨os
 * number is based on the papers in the paper database of this scenario. Authors which do not have any
 * relation to Erd¨os via the papers in the database have Erd¨os number “infinity”.
 * 
 */

import ErdosNumber.Author;
import java.util.*;

public class ErdosNumber {

    static class ErdosInput{
        int scenarioNum;
        int paperNum;
        int authorNum;
        Paper[] papers;
        String[] targetAuthor;
        public ErdosInput(List<String> str){
            this.scenarioNum = Integer.parseInt(str.get(0));
            String[] lineAndAuthor = str.get(1).split(" +");
            this.paperNum = Integer.parseInt(lineAndAuthor[0]);
            this.authorNum = Integer.parseInt(lineAndAuthor[1]);
            this.papers = new Paper[this.paperNum];
            int authorCount = 0;
            this.targetAuthor = new String[authorNum];
            for(int i=0; i < this.paperNum; i++){
                String[] authorAndScenarioName = str.get(i+2).split(":");
                String[] authors = authorAndScenarioName[0].split(",");
                String paperName = authorAndScenarioName[1];
                this.papers[i] = new Paper(authors, paperName);
            }
            for(int j=2+this.paperNum; j<str.size();j++){
                this.targetAuthor[authorCount] = str.get(j);
                authorCount++;
            }
        }
    }

    static class Paper{
        List<Author> authors = new ArrayList<>();
        String paperName;
        public Paper(String[] authorsName, String paperName){
            for(int i=0; i<authorsName.length;i++){
                this.authors.add(new Author(authorsName[i]));
            }
            this.paperName = paperName;
        }
    }

    static class Author{
        String authorName;
        int erdosNum = Integer.MAX_VALUE;
        List<Author> relations = new ArrayList<>();
        public Author(String author){
            this.authorName = author;
            if(this.authorName == "Erdos, P."){
                this.erdosNum = 0;
            }
        }
    }

    public static void main(String[] args) {
        fileIO readFile = new fileIO();
        List<String> strs = readFile.readFile("test2.txt");
        ErdosNumber erdos = new ErdosNumber(strs);
        
    }
    

    public ErdosNumber(List<String> str){
        ErdosInput parsedErdosInput =  new ErdosInput(str);
        System.out.println(parsedErdosInput.targetAuthor[0]);
        System.out.println("Scenario Number: " + parsedErdosInput.scenarioNum);
        for(Paper paper: parsedErdosInput.papers){
            int maxErdos = Integer.MIN_VALUE;
            for(Author author: paper.authors){
                
            }
        }

    }
}