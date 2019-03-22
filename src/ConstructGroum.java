import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class ConstructGroum {

    public Groum constructGroum(String groumPath,Map<String,String> apiMap){
        Groum groum = new Groum();
        try {
            BufferedReader br = new BufferedReader(new FileReader(groumPath));
            String line = null;
            while((line = br.readLine()) != null){
               if(line.endsWith("]")){
                   line = line.replace("[","");
                   line = line.replace("]","");
                   String[] strs = line.split(" ");
                   String id = strs[0];
                   String api = strs[1].replace("label=\"","");
                   api = api.substring(0,api.length() - 1);
                   if(!api.startsWith("C:#Users#")){
                       String originApi = api;
                       if(originApi.contains("<")){
                           if(originApi.contains("<init>")){
                               originApi = originApi.replaceAll("<init>","chenchichenchi");
                           }
                           originApi = originApi.replaceAll("\\<.*?\\>","");
                           originApi = originApi.replaceAll(">","");
                           if(originApi.contains("chenchichenchi")){
                               originApi = originApi.replaceAll("chenchichenchi","<init>");
                           }
                       }

                       if(apiMap.containsKey(api)){
                           api = apiMap.get(api);
                       }else{
                           api = Integer.toString(-1);
                       }
//                       String startLineString = strs[strs.length - 2].replace("startLine=","");
//                       String endLineString = strs[strs.length - 1].replace("endLine=","");
//                       int startLine = Integer.parseInt(startLineString);
//                       int endLine = Integer.parseInt(endLineString);
//                       GroumNode node = new GroumNode(id,api,originApi,startLine,endLine);
                       GroumNode node = new GroumNode(id,api,originApi,-1,-1);
                       groum.addNode(node);
                   }


               }
               else if(line.endsWith(";")){
                   line = line.replace("[label=\"\"];","");
                   line = line.trim();
                   String[] strs = line.split(" -> ");
                   String parentNodeId = strs[0];
                   String childNodeId = strs[1];
                   groum.addEdge(parentNodeId,childNodeId);
               }
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }catch (Error e){
            e.printStackTrace();
        }
        return groum;
    }
}
