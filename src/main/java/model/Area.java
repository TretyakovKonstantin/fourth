package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

public class Area implements Serializable {
    private Double radius = 1D;

    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();
        double halfRadius = getRadius() / 2;

        boolean inTriangle =
                (-radius <= x && x <= 0) &&
                        (y >= 0 && y <= radius) &&
                        (y - x) <= radius;

        boolean inCircleQuadrant =
                (x >= 0 && y <= 0) &&
                        (x * x + y * y <= radius * radius);

        boolean inSquare =
                (0 <= x && x <= radius) &&
                        (halfRadius >= y && y >= 0);

        return inTriangle || inCircleQuadrant || inSquare;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;

        return Double.compare(area.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(radius);
        return (int) (temp ^ (temp >>> 32));
    }
}
