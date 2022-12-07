package day07;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode {
    public String name;
    public DirectoryNode parent;
    public List<DirectoryNode> children = new ArrayList<>();
    public Integer value = 0;

    public DirectoryNode(String newDirectoryName) {
        name = newDirectoryName;
    }
}
