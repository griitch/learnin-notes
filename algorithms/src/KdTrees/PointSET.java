package KdTrees;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class PointSET {

    private final Set<Point2D> points = new TreeSet<>();

    public boolean isEmpty() {return points.isEmpty();}

    public int size() {return points.size(); }

    public void insert(Point2D p) {
        if(p == null)
            throw new IllegalArgumentException();
        points.add(p);
    }

    public boolean contains(Point2D p) {
        if(p == null)
            throw new IllegalArgumentException();
        return points.contains(p);
    }
    public void draw() {
        points.forEach(Point2D::draw);
    }

    public Iterable<Point2D> range(RectHV rect){
       return points.stream().filter(rect::contains).collect(Collectors.toList());
    }

    public Point2D nearest(Point2D p) {
        if (isEmpty()) return null;
        double nearestPointDistance = Double.MAX_VALUE;
        Point2D nearestPoint = p;
        for (Point2D point : points){
            if (p.distanceTo(point) < nearestPointDistance){
                nearestPointDistance = p.distanceTo(point);
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }

}