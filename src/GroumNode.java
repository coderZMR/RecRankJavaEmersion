import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GroumNode implements Serializable {

    private String id;
    private String api;
    private String originalApi;
    private int startLine;
    private int endLine;
    private List<GroumNode> children = new ArrayList<>();
    private List<GroumNode> parents = new ArrayList<>();

    public GroumNode(String id, String api, String originalApi, int startLine, int endLine){
        this.id = id;
        this.api = api;
        this.originalApi = originalApi;
        this.startLine = startLine;
        this.endLine = endLine;
    }

    public String getOriginalApi() {
        return originalApi;
    }

    public void setOriginalApi(String originalApi) {
        this.originalApi = originalApi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getEndLine() {
        return endLine;
    }

    public void setEndLine(int endLine) {
        this.endLine = endLine;
    }

    public List<GroumNode> getChildren() {
        return children;
    }

    public List<GroumNode> getParents() {
        return parents;
    }
}
