package KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private Node root;
    private int size = 0;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {

        /**
         *
         * Search and insert. The algorithms for search and insert are similar to those for BSTs,
         * but at the root we use the x-coordinate (if the point to be inserted has a smaller
         * x-coordinate than the point at the root, go left; otherwise go right);
         *
         * then at the next level, we use the y-coordinate (if the point to be inserted has a smaller
         * y-coordinate than the point in the node, go left; otherwise go right);
         * then at the next level the x-coordinate, and so forth
         *
         * even levels divide the plane vertically
         * odd levels divide the plan horizontally
         */
        Node current = root;
        while (current != null ) {

            if((current.nodeLevel & 1) == 0) {  // % 2 == 0

                if(current.data.x() > p.x() ) {
                    if(current.left == null) {
                        current.left = new Node(p,null,null,current.nodeLevel + 1);
                        break;
                    } else {
                        current = current.left;
                    }
                } else {
                    if(current.right == null ) {
                        current.right = new Node(p,null,null,current.nodeLevel + 1);
                        break;
                    } else {
                        current = current.right;
                    }
                }
            } else {
                if(current.data.y() > p.y()) {
                    if(current.left == null) {
                        current.left = new Node(p,null,null,current.nodeLevel + 1);
                        break;
                    } else {
                        current = current.left;
                    }
                } else{
                    if(current.right == null ) {
                        current.right = new Node(p,null,null,current.nodeLevel + 1);
                        break;
                    } else {
                        current = current.right;
                    }
                }
            }
        }
        size++;
    }

    public boolean contains(Point2D p) {
        return contains(p,0,root);
    }

    public Point2D nearest(Point2D p ) {
        if (p == null || isEmpty()) throw new IllegalArgumentException();
        return nearest(p, root.data, root);
    }

    public void draw() {
        draw(root, new RectHV(0,0, 1, 1));
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> result = new LinkedList<>();
        range(this.root, rect, result);
        return result;
    }

    private Point2D nearest(Point2D p, Point2D currentNearestPoint, Node n){

        if(n == null)
            return currentNearestPoint;


        if ((n.nodeLevel & 1) == 0) {
            if(p.x() > n.data.x()){
                Point2D npr = nearest(p,
                    n.data.distanceTo(p) < currentNearestPoint.distanceTo(p)? n.data : currentNearestPoint,
                    n.right);

                if (npr.distanceTo(p) > Math.abs(n.data.x() - p.x())){
                    Point2D npl = nearest(p, npr, n.left);
                    return npr.distanceTo(p) > npl.distanceTo(p) ? npl : npr;
                } else {
                    return npr;
                }
            } else { // check left sub-tree
                Point2D npl = nearest(p, n.data.distanceTo(p) < currentNearestPoint.distanceTo(p) ? n.data : currentNearestPoint, n.left);
                if (npl.distanceTo(p) > Math.abs(n.data.x() - p.x())) {
                    Point2D npr = nearest(p, npl, n.right);
                    return npr.distanceTo(p) > npl.distanceTo(p) ? npl : npr;
                } else {
                    return npl;
                }
            }

        } else {
            if (p.y() > n.data.y()) { // check up sub-tree
                Point2D npu = nearest(p, n.data.distanceTo(p) < currentNearestPoint.distanceTo(p) ? n.data : currentNearestPoint, n.right);
                if (npu.distanceTo(p) > Math.abs(n.data.y() - p.y())){
                    Point2D npd = nearest(p, npu, n.left);
                    return npu.distanceTo(p) > npd.distanceTo(p) ? npd : npu;
                } else {
                    return npu;
                }
            } else { // check down sub-tree
                Point2D npd = nearest(p, n.data.distanceTo(p) < currentNearestPoint.distanceTo(p) ? n.data : currentNearestPoint, n.left);
                if (npd.distanceTo(p) > Math.abs(n.data.y() - p.y())){
                    Point2D npu = nearest(p, npd, n.right);
                    return npu.distanceTo(p) > npd.distanceTo(p) ? npd : npu;
                } else {
                    return npd;
                }
            }
        }
    }


    private boolean contains(Point2D p, int lvl, Node root) {

        if( p == null)
            return false;

        if( p.equals(root.data))
            return true;

        if((lvl & 1 ) == 0) {
          if(root.data.x() > p.x()) {
              return contains(p,lvl + 1,root.right);
          } else{
              return contains(p,lvl + 1, root.left);
          }
        }  else {
            if(root.data.y() > p.y()) {
                return contains(p,lvl + 1,root.right);
            } else{
                return contains(p,lvl + 1, root.left);
            }

        }
    }

    private void range(Node n, RectHV rect, List<Point2D> acc){
        if (n == null){
            return;
        }

        if (rect.contains(n.data)){
            acc.add(n.data);
        }

        if (n.nodeLevel % 2 == 0) { //Vertical segment
            // the vertical line intersects with query rectangle
            if (rect.xmin() <= n.data.x() && n.data.x() <= rect.xmax()) {
                range(n.left, rect, acc);
                range(n.right, rect, acc);
            } else if (rect.xmin() > n.data.x()){ // Search right
                range(n.right, rect, acc);
            } else { // Search left
                range(n.left, rect, acc);
            }
        } else { // Horizontal segment
            // the horizontal line intersects with query rectangle
            if (rect.ymin() <= n.data.y() && n.data.y() <= rect.ymax()) {
                range(n.left, rect, acc);
                range(n.right, rect, acc);
            } else if (rect.ymin() > n.data.y()){ // Search up
                range(n.right, rect, acc);
            } else { // Search down
                range(n.left, rect, acc);
            }
        }
    }


    private void draw(Node n, RectHV rectHV){
        if (n == null){
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        n.data.draw();

        StdDraw.setPenRadius(0.001);
        if (n.nodeLevel % 2 == 0){
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(n.data.x(), rectHV.ymin(), n.data.x(), rectHV.ymax());
            draw(n.left, new RectHV(rectHV.xmin(), rectHV.ymin(), n.data.x(), rectHV.ymax()));
            draw(n.right, new RectHV(n.data.x(), rectHV.ymin(), rectHV.xmax(), rectHV.ymax()));
        } else {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(rectHV.xmin(), n.data.y(), rectHV.xmax(), n.data.y());
            draw(n.left, new RectHV(rectHV.xmin(), rectHV.ymin(), rectHV.xmax(), n.data.y()));
            draw(n.right, new RectHV(rectHV.xmin(), n.data.y(), rectHV.xmax(), rectHV.ymax()));
        }
    }

}
class Node {
     final Point2D data;
     Node left;
     Node right;
     final int nodeLevel;

    public Node(Point2D data, Node left, Node right, int nodeLevel) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.nodeLevel = nodeLevel;
    }

}