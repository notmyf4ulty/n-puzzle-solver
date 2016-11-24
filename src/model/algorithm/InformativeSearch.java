package model.algorithm;

public abstract class InformativeSearch<T extends Node> {

    protected T rootNode;
    protected T targetNode;

    public InformativeSearch(T rootNode, T targetNode) {
        this.rootNode = rootNode;
        this.targetNode = targetNode;
    }

    abstract SearchStat search();

    boolean isTargetConfiguration() {
        return rootNode.equals(targetNode);
    }
}
