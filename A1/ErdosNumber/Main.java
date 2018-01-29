import java.util.*;


// Working program using BufferedReader
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.util.*;

class Paper{
    List<String> authors = new ArrayList<>();
    String paperName;
    public Paper(String[] authorsName, String paperName){
        for(int i=0; i<authorsName.length;i+=2){
            this.authors.add(authorsName[i] + ", " + authorsName[i + 1].trim());
            
        }
        this.paperName = paperName;
    }
}

class ErdosNumber {

    final String ERDOS_NAME = new String("Erdos, P.");

    static class ErdosInput{
        int scenarioNum;
        int paperNum;
        int authorNum;
        Paper[] papers;
        String[] targetAuthor;
        public ErdosInput(int scenarioNum, Paper[] papers, String[] targetAuthor){
            this.scenarioNum = scenarioNum;
            this.papers = papers;
            this.targetAuthor = targetAuthor;

        }
    }
    

    public ErdosNumber(int scenarioNum, Paper[] papers, String[] targetAuthor){
        Map<String,Set<String>> map = new HashMap<>();
        ErdosInput parsedErdosInput =  new ErdosInput(scenarioNum,papers, targetAuthor);
        

        // Print Scenario
        System.out.println("Scenario " + parsedErdosInput.scenarioNum);
        
        // Draw Graph
        for(Paper paper: parsedErdosInput.papers){
            for(String author: paper.authors){
                if(!map.containsKey(author)) {
                    map.put(author,new HashSet<>());
                }
            }
            
            // !! need add neighbours in the map
            for(int i=0;i<paper.authors.size();i++){
                String currAuthor = paper.authors.get(i);
                for(int j=0;j<paper.authors.size();j++){
                    if(j!=i){
                        Set<String> newSet = map.get(currAuthor);
                        newSet.add(paper.authors.get(j));
                        map.put(currAuthor, newSet);
                    }
                }
            }
           


        }

        // Parse Graph
        Map<String,Integer> res = bfs(map);

        String rtnStr;
        for(int i=0;i< parsedErdosInput.targetAuthor.length; i++){
            if(res.get(parsedErdosInput.targetAuthor[i]) == null){
                rtnStr = new String("infinity");
            }
            else{
                rtnStr = Integer.toString(res.get(parsedErdosInput.targetAuthor[i]));
            }
            System.out.println( parsedErdosInput.targetAuthor[i]+ " " + rtnStr);
        }
        

    }


    private Map<String,Integer> bfs(Map<String,Set<String>> map){
        int count = 1;
        Map<String,Integer> res = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(ERDOS_NAME);
        res.put(ERDOS_NAME,0);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                String curr = queue.poll();
                // 
                for(String key:map.get(curr)){
                    if(res.containsKey(key)){
                        continue;
                    }
                    else{
                        queue.offer(key);
                        res.put(key,count);
                    }
                }
            }
            count++;
            // System.out.println(res.get("Smith, M.N. "));
        }
        return res;
    }
}


class Main{
    public static void main(String[] args) throws IOException {
  
        //  BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
          
          BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
          
          int n = Integer.parseInt(br.readLine());
          int times = 0;
          while (n-- > 0) {
            times ++;
            String next = br.readLine().trim();   
            while(next.equals("")){
                next = br.readLine();
            }
            String s[] = next.split(" ");
            int P = Integer.parseInt(s[0]);
            int N = Integer.parseInt(s[1]);

            Paper[] papers = new Paper[P];
            
            List<String> authors = new ArrayList<>();
            for (int i = 0; i < P; i++) {
                
                String line = br.readLine().trim();
                String[] linePart1 = line.split(":");
                

                String[] temp_list = linePart1[0].split(", ");
                papers[i] = new Paper(temp_list,linePart1[1]);
            }
            
            String[] nameList = new String[N]; 
            for (int i = 0; i < N; i++) {
                nameList[i] = br.readLine().trim();
            }
            ErdosNumber newInput = new ErdosNumber(times,papers, nameList);
           
        }
    }
}