package net.sourceforge.myjorganizer.parser.visitor;

import java.util.Enumeration;

import net.sourceforge.myjorganizer.parser.syntaxtree.Node;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeList;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeListOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeSequence;

public abstract class AbstractDepthFirstVisitor implements Visitor {
    public void visit(NodeList n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }

    public void visit(NodeListOptional n) {
        if (n.present())
            for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
                e.nextElement().accept(this);
    }

    public void visit(NodeOptional n) {
        if (n.present())
            n.node.accept(this);
    }

    public void visit(NodeSequence n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }
}
