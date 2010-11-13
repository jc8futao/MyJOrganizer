package net.sourceforge.myjorganizer.parser.visitor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import net.sourceforge.myjorganizer.parser.syntaxtree.Node;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeList;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeListOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeOptional;
import net.sourceforge.myjorganizer.parser.syntaxtree.NodeSequence;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommand;
import net.sourceforge.myjorganizer.parser.syntaxtree.TaskCommands;

/**
 * <p>
 * Abstract AbstractDepthFirstVisitor class.
 * </p>
 * 
 * @author Davide Bellettini <dbellettini@users.sourceforge.net>
 * @version $Id: AbstractDepthFirstVisitor.java 127 2010-11-03 23:52:17Z
 *          dbellettini $
 */
public abstract class AbstractDepthFirstVisitor implements Visitor {
    protected DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    /** {@inheritDoc} */
    public void visit(NodeList n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }

    /**
     * <p>
     * visit
     * </p>
     * 
     * @param n
     *            a
     *            {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeListOptional}
     *            object.
     */
    public void visit(NodeListOptional n) {
        if (n.present())
            for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
                e.nextElement().accept(this);
    }

    /**
     * <p>
     * visit
     * </p>
     * 
     * @param n
     *            a
     *            {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeOptional}
     *            object.
     */
    public void visit(NodeOptional n) {
        if (n.present())
            n.node.accept(this);
    }

    /**
     * <p>
     * visit
     * </p>
     * 
     * @param n
     *            a
     *            {@link net.sourceforge.myjorganizer.parser.syntaxtree.NodeSequence}
     *            object.
     */
    public void visit(NodeSequence n) {
        for (Enumeration<Node> e = n.elements(); e.hasMoreElements();)
            e.nextElement().accept(this);
    }

    @Override
    public void visit(TaskCommand n) {
        n.f0.accept(this);
    }

    @Override
    public void visit(TaskCommands n) {
        n.f0.accept(this);
    }
}
