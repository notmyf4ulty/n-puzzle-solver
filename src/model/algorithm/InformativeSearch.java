package model.algorithm;

public abstract class InformativeSearch {

    Node rootNode;
    Node targetNode;

    public InformativeSearch(Node rootNode, Node targetNode) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
    }

    abstract public SearchStat search();

    public

    boolean isTargetConfiguration(Node node) {
        return node.equals(targetNode);
    }
}
