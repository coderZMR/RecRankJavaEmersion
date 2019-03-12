import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Groum implements Serializable {

    // private GroumNode root = null;
    private Map<String, GroumNode> nodeMap = new HashMap<>();
    private Map<Integer, List<GroumNode>> lineMap = new HashMap<>();
    private List<GroumNode> nodes = new ArrayList<>();
    private List<GroumNode> rootList = new ArrayList<>();

//    public GroumNode getRoot() {
//        return root;
//    }

//    public void setRoot(GroumNode root) {
//        this.root = root;
//    }


    public Map<Integer, List<GroumNode>> getLineMap() {
        return lineMap;
    }

    public void setLineMap(Map<Integer, List<GroumNode>> lineMap) {
        this.lineMap = lineMap;
    }

    public Map<String, GroumNode> getNodeMap() {
        return nodeMap;
    }

    public void setNodeMap(Map<String, GroumNode> nodeMap) {
        this.nodeMap = nodeMap;
    }

    public List<GroumNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<GroumNode> nodes) {
        this.nodes = nodes;
    }

    public void addNode(GroumNode node){
        if (lineMap.containsKey(node.getStartLine())) {
            lineMap.get(node.getStartLine()).add(node);
        } else {
            List<GroumNode> list = new ArrayList<>();
            list.add(node);
            lineMap.put(node.getStartLine(), list);
        }
        if(!nodes.contains(node)) {
            nodes.add(node);
            nodeMap.put(node.getId(), node);
        }
    }

    public void addEdge(String parentNodeId, String childNodeId){
        GroumNode parentNode = nodeMap.get(parentNodeId);
        GroumNode childNode = nodeMap.get(childNodeId);
        if(!parentNode.getChildren().contains(childNode)) {
            parentNode.getChildren().add(childNode);
        }
        if(!childNode.getParents().contains(parentNode)) {
            childNode.getParents().add(parentNode);
        }
    }

//
//    public void setRoot(){
//        for(GroumNode node: nodes){
//            if(node.getParents().size() == 0){
//                root = node;
//                break;
//            }
//        }
//    }

    public List<GroumNode> getRootList(){
        for(GroumNode node: nodes){
            if(node.getParents().size() == 0){
                if(!rootList.contains(node)) {
                    rootList.add(node);
                }
            }
        }
        return rootList;
    }

    public boolean isContainingJDKApi(Map<String,Boolean> jdkMap){
        for(GroumNode node: nodes){
            if(jdkMap.containsKey(node.getOriginalApi())){
                return true;
            }
        }
        return false;
    }

}

