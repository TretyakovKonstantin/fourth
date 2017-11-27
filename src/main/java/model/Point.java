package model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Point implements Serializable {
    @Column(name = "x")
    private Double x = 0D;
    @Column(name = "y")
    private Double y = 0D;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        if (x < -5 || x > 5) return;
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", getX(), getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != null ? !x.equals(point.x) : point.x != null) return false;
        return y != null ? y.equals(point.y) : point.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
