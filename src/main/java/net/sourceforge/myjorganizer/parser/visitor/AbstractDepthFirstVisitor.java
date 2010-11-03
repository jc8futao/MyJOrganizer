package net.sourceforge.myjorganizer.parser.visitor;

import java.util.Enumeration;

import net.sourceforge.myjorganizer.parser.syntaxtree.Node;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeList;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeListOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeSequence;

/**
 * <p>Abstract AbstractDepthFirstVisitor class.</p>
 *
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id$
 */
public abstract class AbstractDepthFirstVisitor implements Visitor {
    /** {@inheritDoc} */
    public void visit(NodeList n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }

    /**
     * <p>visit</p>
     *
     * @param n a {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeListOptional} object.
     */
    public void visit(NodeListOptional n) {
        if (n.present())
            for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
                e.nextElement().accept(this);
    }

    /**
     * <p>visit</p>
     *
     * @param n a {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeOptional} object.
     */
    public void visit(NodeOptional n) {
        if (n.present())
            n.node.accept(this);
    }

    /**
     * <p>visit</p>
     *
     * @param n a {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeSequence} object.
     */
    public void visit(NodeSequence n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }
}
