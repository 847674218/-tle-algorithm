package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class GetTraceMatrix {
    // 初始跟踪链接中，哪个需求对应哪些类
    public static Map<String, List<String>> fromRequirementsToClassOrMethod() throws IOException {
        File file = new File("data\\TraceMatrix_Start.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, List<String>> traceMatrix = new HashMap<>();

        String line;
        while((line=br.readLine())!=null) {
            if(line.length()!=0){
                String [] arr = line.split("\\s+");
                if(traceMatrix.containsKey(arr[0])){
                    List<String> temp = traceMatrix.get(arr[0]);
                    temp.add(arr[1]);
                }else{
                    List<String> temp = new ArrayList<String>();
                    temp.add(arr[1]);
                    traceMatrix.put(arr[0],temp);
                }
            }
        }
        br.close();

//        for (Map.Entry<String, List<String>> traceLink : traceMatrix.entrySet()) {
//            String requirement = traceLink.getKey();
//            List<String> className = traceLink.getValue();
//            System.out.println(requirement+" "+className);
//        }

        return traceMatrix;
    }

    // 初始跟踪链接中，哪个类对应哪些需求
    public static Map<String,  List<String>> fromClassOrMethodToRequirements() throws IOException {
        File file = new File("data\\TraceMatrix_Start.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));

        Map<String, List<String>> traceMatrix = new HashMap<>();

        String line;
        while((line=br.readLine())!=null) {
            if(line.length()!=0){
                String [] arr = line.split("\\s+");
                if(traceMatrix.containsKey(arr[1])){
                    List<String> temp = traceMatrix.get(arr[1]);
                    temp.add(arr[0]);
                }else{
                    List<String> temp = new ArrayList<String>();
                    temp.add(arr[0]);
                    traceMatrix.put(arr[1],temp);
                }
            }

        }
        br.close();

        return traceMatrix;
    }
}
